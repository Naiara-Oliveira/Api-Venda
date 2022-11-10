package br.ufms.controle_venda.dtos;

import br.ufms.controle_venda.models.ItemProduto;
import br.ufms.controle_venda.models.TipoPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link br.ufms.controle_venda.models.Pedido} entity
 */
@Data
@NoArgsConstructor

public class PedidoDto implements Serializable {
    private Long idCliente;
    private TipoPagamento tipoPagamento;
    private List<ItemProdutoDto> itemProduto = new ArrayList<>();

}
