package br.ufms.controle_venda.repositories;

import br.ufms.controle_venda.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}