package contes.atosspringbootlocadora.Models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "contrato")
public class Contrato implements Serializable{

    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nContrato;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private Cliente cliente;
    
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "automovel_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private Automovel automovel;

    private double preco;

    private LocalDate data;

    private int periodo;

    public Contrato() {

    }

    public Contrato(Cliente cliente, Automovel automovel, double preco, LocalDate data, int periodo) {
        this.cliente = cliente;
        this.automovel = automovel;
        this.preco = preco;
        this.data = data;
        this.periodo = periodo;
    }

    public Long getNContrato() {
        return nContrato;
    }

    public void setNContrato(Long nContrato) {
        this.nContrato = nContrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        String contrato = "\nNome: " + cliente.getNome() +
                "\nAutomovel: " + automovel.getMarca() +
                "\nData: " + data +
                "\nPeriodo: " + periodo +
                "\nPreco total: " + (periodo * preco) +
                "\nData da devolucao: " + (data.plusDays(periodo));

        return contrato;
    }

}
