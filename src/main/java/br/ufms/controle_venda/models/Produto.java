package br.ufms.controle_venda.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String marca;

    @Column(nullable = false, length = 15)
    private String cor;

    @Column(nullable = false, length = 10)
    private String tamanho;

    @Column(nullable = false)
    private BigDecimal preco;
    @JsonIgnore
    @OneToOne
    private Pedido pedido;

}
