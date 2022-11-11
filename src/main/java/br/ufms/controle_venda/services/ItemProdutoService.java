package br.ufms.controle_venda.services;

import br.ufms.controle_venda.dtos.ItemProdutoDto;
import br.ufms.controle_venda.models.ItemProduto;
import br.ufms.controle_venda.repositories.ItemProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ItemProdutoService {
    private final ItemProdutoRepository itemProdutoRepository;

    public ItemProdutoService(ItemProdutoRepository itemProdutoRepository) {
        this.itemProdutoRepository = itemProdutoRepository;

    }

    @Transactional
    public void novoItemPedido(ItemProduto itemProduto){
        itemProdutoRepository.save(itemProduto);
    }


    @Transactional
    public ItemProduto atualizar(Long id, ItemProdutoDto itemProdutoDto) {
        ItemProduto itemProduto = itemProdutoRepository.findById(id).get();
        BeanUtils.copyProperties(itemProdutoDto, itemProduto);
        return itemProduto;
    }
    @Transactional
    public void deletar(Long id) {
        itemProdutoRepository.deleteById(id);
    }
    public Page<ItemProduto> findAll(Pageable page) {
        return itemProdutoRepository.findAll(page);
    }
    public Optional<ItemProduto> findById(Long id) {
        return itemProdutoRepository.findById(id);
    }


    public Page<ItemProduto> findAllByPedidoId(Long id, Pageable pageable) {
return itemProdutoRepository.findAllByPedidoId(id, pageable);
    }
}
