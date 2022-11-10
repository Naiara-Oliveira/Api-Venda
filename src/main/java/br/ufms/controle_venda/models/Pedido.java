package br.ufms.controle_venda.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    private BigDecimal valorTotal;
    private BigDecimal valorDesconto;
    private BigDecimal valorTotalComDesconto;
    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<ItemProduto> itemProduto = new ArrayList<>();
    @JsonIgnore
    @OneToOne
    private Venda venda;
    @JsonIgnore
    @OneToOne
    private Cliente cliente;

    public void adicionarItem(ItemProduto item){
        this.itemProduto.add(item);
    }
}
