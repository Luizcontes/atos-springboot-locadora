package contes.atosspringbootlocadora.Models;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cpf")
    private Long cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cnh")
    private String cnh;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "ativo", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean ativo;

    public Cliente() {
        this.ativo = true;
    }
    
    public Cliente(Long cpf, String nome, String cnh, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.cnh = cnh;
        this.endereco = endereco;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public String toString() {

        String cliente = "\nNome: " + nome +
                "\nEndereco: " + endereco +
                "\nCPF: " + cpf +
                "\nCNH: " + cnh;

        return cliente;
    }
}
