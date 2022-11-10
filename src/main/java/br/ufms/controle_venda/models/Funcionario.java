package br.ufms.controle_venda.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Venda> venda = new ArrayList<>();

    public void adicionarVenda(Venda venda){
        this.venda.add(venda);
    }
}
