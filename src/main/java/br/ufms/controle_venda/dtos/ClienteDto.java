package br.ufms.controle_venda.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link br.ufms.controle_venda.models.Cliente} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto implements Serializable {
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
}