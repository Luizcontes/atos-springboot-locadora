package contes.atosspringbootlocadora.Repository;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// @Entity
// @Table(name = "contrato")
public class Contrato {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nContrato;
    // @ManyToOne
    private Cliente cliente;
    // @ManyToOne
    private Automovel automovel;
    private double preco;
    private LocalDate data;
    private int periodo;

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
