package br.ufms.controle_venda.controllers;

import br.ufms.controle_venda.dtos.FuncionarioDto;
import br.ufms.controle_venda.models.Funcionario;
import br.ufms.controle_venda.services.FuncionarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Service
@RequestMapping("/funcionario")
public class FuncionarioControllers {
    private final FuncionarioService funcionarioService;

    public FuncionarioControllers(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    @PostMapping
    public ResponseEntity<Void> novoFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto){
        try{
            this.funcionarioService.novoFuncionario(funcionarioDto);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<Page<Funcionario>> findAll(Pageable page){
        try{
            return ResponseEntity.ok().body(this.funcionarioService.findAll(page));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
