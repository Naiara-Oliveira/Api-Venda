package br.ufms.controle_venda.controllers;

import br.ufms.controle_venda.dtos.ClienteDto;
import br.ufms.controle_venda.dtos.ItemProdutoDto;
import br.ufms.controle_venda.dtos.PedidoDto;
import br.ufms.controle_venda.dtos.ProdutoDto;
import br.ufms.controle_venda.models.Cliente;
import br.ufms.controle_venda.models.Pedido;
import br.ufms.controle_venda.services.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoControllers {
    private final PedidoService pedidoService;

    public PedidoControllers(PedidoService pedidoService) {

        this.pedidoService = pedidoService;
    }
    @PostMapping
    public ResponseEntity<Void> novoPedido(@RequestBody  PedidoDto pedidoDto){

         this.pedidoService.novoPedido(pedidoDto);
         return ResponseEntity.ok().build();
    }
    @GetMapping
        public ResponseEntity<Page<Pedido>> findAll(Pageable page){
            try{
                return ResponseEntity.ok().body(this.pedidoService.findAll(page));
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }

}
