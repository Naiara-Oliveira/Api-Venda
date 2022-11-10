package br.ufms.controle_venda.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link br.ufms.controle_venda.models.ItemProduto} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProdutoDto implements Serializable {
    private String nome;
    private Integer quantidade;
    private String descricao;
}