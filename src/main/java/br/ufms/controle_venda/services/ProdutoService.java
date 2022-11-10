package br.ufms.controle_venda.services;

import br.ufms.controle_venda.dtos.ProdutoDto;
import br.ufms.controle_venda.models.Produto;
import br.ufms.controle_venda.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public Page<Produto> findAll(Pageable page){
        return produtoRepository.findAll(page);
    }
    @Transactional
    public void newProduto(ProdutoDto produtoDto){
        Produto novoProduto = new Produto();
        BeanUtils.copyProperties(produtoDto, novoProduto);
        produtoRepository.save(novoProduto);
    }
    @Transactional
    public void alterProduto(Long id, ProdutoDto produtoDto){
        Optional<Produto> produto = this.produtoRepository.findById(id);
        produto.ifPresent(atualizandoProduto -> {
            BeanUtils.copyProperties(produtoDto, atualizandoProduto);
        });
    }
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    public void alterPrice(Long id, BigDecimal newPrice){
        Optional<Produto> produto = this.produtoRepository.findById(id);
        produto.ifPresent(atualizandoProduto -> {
            atualizandoProduto.setPreco(newPrice);
        });
    }
    public Optional<Produto> findByNomeAndDescricao(String nome, String descricao){
        return produtoRepository.findByNomeAndDescricao(nome, descricao);
    }

}
