package contes.atosspringbootlocadora.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "automovel")
public class Automovel {

    @Id
    private String placa;
    private String marca;
    private String modelo;
    private String versao;
    private int ano;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean ativo;
    
    public Automovel() {
        this.ativo = true;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getVersao() {
        return versao;
    }
    public void setVersao(String versao) {
        this.versao = versao;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        String automovel =  "\nMARCA: " + marca +
            "\nMODELO: " + modelo +
            "\nVERSAO: " + versao +
            "\nPLACA: " + placa +
            "\nANO: " + ano;
            
            return automovel;
    }
    
}
