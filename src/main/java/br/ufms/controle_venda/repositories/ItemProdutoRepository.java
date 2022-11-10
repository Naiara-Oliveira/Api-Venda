package br.ufms.controle_venda.repositories;

import br.ufms.controle_venda.models.ItemProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemProdutoRepository extends JpaRepository<ItemProduto, Long> {

}