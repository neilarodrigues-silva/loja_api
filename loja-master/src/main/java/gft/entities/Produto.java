package gft.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String unidade;


    public Produto() {
    }


    public Produto(Long id) {
        this.id = id;

    }


    public Produto(Long id, String nome, String descricao, String unidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.unidade = unidade;
    }




}
