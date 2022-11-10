package br.ufms.controle_venda.controllers;

import br.ufms.controle_venda.dtos.ProdutoDto;
import br.ufms.controle_venda.models.Produto;
import br.ufms.controle_venda.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoControllers {
    private final ProdutoService produtoService;

    public ProdutoControllers(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Void> novoProduto(@RequestBody @Valid ProdutoDto produtoDto){
        try{
            produtoService.newProduto(produtoDto);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<Page<Produto>> findAll(Pageable page){
        try{
            return ResponseEntity.ok().body(produtoService.findAll(page));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    //@PutMapping("/{id}")
}
