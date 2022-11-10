package br.ufms.controle_venda.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link br.ufms.controle_venda.models.Venda} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDto implements Serializable {
    private BigDecimal comissao;
}