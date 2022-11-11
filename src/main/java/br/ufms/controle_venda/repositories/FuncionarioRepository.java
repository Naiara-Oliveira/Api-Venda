package br.ufms.controle_venda.repositories;

import br.ufms.controle_venda.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Override
    Optional<Funcionario> findById(Long id);
}