package br.ufms.controle_venda.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link br.ufms.controle_venda.models.Produto} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto implements Serializable {
    private String nome;
    private String descricao;
    private String marca;
    private String cor;
    private String tamanho;
    private BigDecimal preco;
}