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
        Pedido pedido = new Pedido();

        for (ItemProdutoDto itemProdutoDto : pedidoDto.getItemProduto()) {
            ItemProduto item = new ItemProduto();
            Optional<Produto> byNome = produtoService.findByNomeAndDescricao(itemProdutoDto.getNome(), itemProdutoDto.getDescricao());
            if (byNome.isEmpty()) {
                throw new RuntimeException("Produto não encontrado");
            }

            item.setProduto(byNome.get());
            item.setQuantidade(itemProdutoDto.getQuantidade());
            item.setValorUnitario(byNome.get().getPreco());
            item.setValorTotal(item.getValorUnitario().multiply(valueOf(item.getQuantidade())));
            item.setPedido(pedido);
            pedido.adicionarItem(item);
        }
        pedido.setCliente(cliente.get());
        pedido.setData(LocalDateTime.now());
        pedido.setTipoPagamento(pedidoDto.getTipoPagamento());
       double contador = 0;
        for (ItemProduto itemProduto : pedido.getItemProduto()) {
            contador += itemProduto.getValorTotal().doubleValue();
        }
        pedido.setValorTotal(valueOf(contador));

        if (pedidoDto.getTipoPagamento().equals(TipoPagamento.DINHEIRO)) {
            pedido.setValorDesconto(BigDecimal.valueOf(0.05));
            pedido.setValorDesconto(pedido.getValorTotal().multiply(pedido.getValorDesconto()));
        }

        pedido.setValorTotalComDesconto(pedido.getValorTotal().subtract(pedido.getValorDesconto()));


        Funcionario funcionario = funcionarioService.findAny();
        this.pedidoRepository.save(pedido);
        Venda venda = new Venda();
        venda.setPedido(pedido);
        venda.setFuncionario(funcionario);
        venda.setComissao(pedido.getValorTotal().multiply(valueOf(0.02)));


        vendaService.novaVenda(venda);


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
