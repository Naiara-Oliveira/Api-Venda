package br.ufms.controle_venda.repositories;

import br.ufms.controle_venda.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}