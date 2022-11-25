package contes.atosspringbootlocadora.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "cliente")
public class Cliente {
 
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cnh;
    private String endereco;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean ativo;
    
    public Cliente() {
        this.ativo = true;
    }
    
    public Cliente(int id, String nome, String cnh, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cnh = cnh;
        this.endereco = endereco;
        this.ativo = true;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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

        String cliente =  "\nNome: " + nome +
            "\nEndereco: " + endereco +
            "\nCPF: " + id +
            "\nCNH: " + cnh;
            
            return cliente;
        }
    }
    
    
    