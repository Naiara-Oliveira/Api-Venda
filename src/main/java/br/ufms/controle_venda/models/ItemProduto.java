package br.ufms.controle_venda.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor

public class ItemProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private Integer quantidade;
    @Column(nullable = false, length = 1000)
    private BigDecimal valorUnitario;
    @Column(nullable = false, length = 100)
    private BigDecimal valorTotal;
    @JsonIgnore
    @ManyToOne
    private Pedido pedido;
    @JsonIgnore
    @OneToOne
    private Produto produto;
}
