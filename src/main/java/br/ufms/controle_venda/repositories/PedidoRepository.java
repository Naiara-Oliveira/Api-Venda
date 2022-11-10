package br.ufms.controle_venda.repositories;

import br.ufms.controle_venda.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}