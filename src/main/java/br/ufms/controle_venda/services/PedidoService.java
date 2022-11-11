package br.ufms.controle_venda.services;

import br.ufms.controle_venda.dtos.ItemProdutoDto;
import br.ufms.controle_venda.dtos.PedidoDto;
import br.ufms.controle_venda.models.*;
import br.ufms.controle_venda.repositories.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;
    private final ItemProdutoService itemProdutoService;
    private final VendaService vendaService;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService, ClienteService clienteService, FuncionarioService funcionarioService, ItemProdutoService itemProdutoService, VendaService vendaService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
        this.itemProdutoService = itemProdutoService;
        this.vendaService = vendaService;
    }

    @Transactional
    public void novoPedido(PedidoDto pedidoDto) {

        Optional<Cliente> cliente = clienteService.findById(pedidoDto.getIdCliente());
        if (cliente.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Pedido pedidoRealizado = realizarPedido(pedidoDto, cliente.get());

        persistirItens(pedidoDto, pedidoRealizado);

        if (pedidoDto.getTipoPagamento().equals(TipoPagamento.DINHEIRO)) {
            pedidoRealizado.setValorDesconto(BigDecimal.valueOf(0.05));
            pedidoRealizado.setValorDesconto(pedidoRealizado.getValorTotal().multiply(pedidoRealizado.getValorDesconto()));
        }
        pedidoRealizado.setValorTotalComDesconto(pedidoRealizado.getValorTotal().subtract(pedidoRealizado.getValorDesconto()));

        realizarVenda(pedidoRealizado);

    }

    private void realizarVenda(Pedido pedidoRealizado) {
        Funcionario funcionario = funcionarioService.findAny();

        Venda venda = new Venda();
        venda.setPedido(pedidoRealizado);
        venda.setFuncionario(funcionario);
        venda.setComissao(pedidoRealizado.getValorTotal().multiply(valueOf(0.02)));
        vendaService.novaVenda(venda);
    }

    private void persistirItens(PedidoDto pedidoDto, Pedido pedidoRealizado) {
        BigDecimal valorTotalPedido  = new BigDecimal(0);
        for (ItemProdutoDto itemProdutoDto : pedidoDto.getItemProduto()) {
            ItemProduto item = new ItemProduto();
            Optional<Produto> produto = produtoService.findByNomeAndDescricao(itemProdutoDto.getNome(), itemProdutoDto.getDescricao());
            if (produto.isEmpty()) {
                throw new RuntimeException("Produto não encontrado");
            }


            item.setProduto(produto.get());
            item.setQuantidade(itemProdutoDto.getQuantidade());
            item.setValorUnitario(produto.get().getPreco());
            item.setValorTotal(item.getValorUnitario().multiply(valueOf(item.getQuantidade())));

            item.setPedido(pedidoRealizado);

            valorTotalPedido = valorTotalPedido.add(item.getValorTotal());

            pedidoRealizado.adicionarItem(item);

            this.itemProdutoService.novoItemPedido(item);

        }
        pedidoRealizado.setValorTotal(new BigDecimal(String.valueOf(valorTotalPedido)));
    }

    private Pedido realizarPedido(PedidoDto pedidoDto, Cliente cliente) {
        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setData(LocalDateTime.now());
        pedido.setTipoPagamento(pedidoDto.getTipoPagamento());
        pedido.setValorTotal(new BigDecimal(0));
        pedido.setValorTotalComDesconto(new BigDecimal(0));

        return this.pedidoRepository.save(pedido);
    }

    public Page<ItemProduto> findAllByPedidoId(Long id, Pageable pageable) {
        return itemProdutoService.findAllByPedidoId(id, pageable);
    }


    @Transactional
    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Page<Pedido> findAll(Pageable page) {
        return pedidoRepository.findAll(page);
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

}