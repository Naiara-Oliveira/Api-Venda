package br.ufms.controle_venda.services;

import br.ufms.controle_venda.dtos.ClienteDto;
import br.ufms.controle_venda.dtos.FuncionarioDto;
import br.ufms.controle_venda.models.Cliente;
import br.ufms.controle_venda.models.Funcionario;
import br.ufms.controle_venda.repositories.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
public class FuncionarioService {
private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    @Transactional
    public void novoFuncionario(FuncionarioDto funcionarioDto){
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        funcionarioRepository.save(funcionario);
    }

    public Funcionario findAny(){
        Random randon = new Random();
        Long id = randon.nextLong(funcionarioRepository.count());
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.orElseGet(this::findAny);
    }

    @Transactional
    public Optional<Funcionario> atualizar(Long id, FuncionarioDto funcionarioDto) {

        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
        funcionario.ifPresent(atualizandoFuncionario -> {
            BeanUtils.copyProperties(funcionarioDto, atualizandoFuncionario);
        });
        return funcionario;
    }

    public Page<Funcionario> findAll(Pageable page){
        return funcionarioRepository.findAll(page);
    }


    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }
}
