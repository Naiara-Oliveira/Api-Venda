package br.ufms.controle_venda.repositories;

import br.ufms.controle_venda.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNomeAndDescricao(String nome, String descricao);

}