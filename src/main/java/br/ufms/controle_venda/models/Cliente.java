package br.ufms.controle_venda.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String cpf;
    @JsonIgnore
    @OneToOne(mappedBy = "cliente")
    private Pedido pedido;
}
