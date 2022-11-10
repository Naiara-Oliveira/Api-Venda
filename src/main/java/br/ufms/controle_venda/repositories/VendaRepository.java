package br.ufms.controle_venda.repositories;

import br.ufms.controle_venda.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}