package br.ufms.controle_venda.controllers;

import br.ufms.controle_venda.dtos.ClienteDto;
import br.ufms.controle_venda.models.Cliente;
import br.ufms.controle_venda.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteControllers {

    private final ClienteService clienteService;

    @Autowired
    public ClienteControllers(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @PostMapping
    public ResponseEntity<Void> newCliente(@RequestBody @Valid ClienteDto clienteDto){
        try{
            this.clienteService.newCliente(clienteDto);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<Page<Cliente>> findAll(Pageable page){
        try{
            return ResponseEntity.ok().body(this.clienteService.findAll(page));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
