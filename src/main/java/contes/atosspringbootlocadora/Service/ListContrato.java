package contes.atosspringbootlocadora.Service;

// import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Message;
import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Contrato;

public class ListContrato implements Strategy {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;
    int counter = 0;

    public ListContrato(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
    }

    public void execute() {

        System.out.println("LISTAR TODOS OS CONTRATOS ATIVOS!");
        
        List<Contrato> contratos = entityManager.createQuery("SELECT c.nome, c.endereco, a.modelo, a.marca, ct.data, ct.periodo FROM contrato ct INNER JOIN clientes c ON ct.cliente_cpf = c.cpf INNER JOIN automovel a ON ct.automovel_placa = a.placa", Contrato.class)
        .getResultList();
        
        // contratos.forEach(item -> {
        //     if (item.getData().plusDays(item.getPeriodo()).isAfter(LocalDate.now())) {
        //         counter++;
        //         System.out.println(item);
        //     }
        // });

        System.out.println(contratos);

        if (counter != 0) {
            Message.printEnd("\nPressione qualuer tecla para continuar...", scanner);
        } else {
            Message.printEnd("\nNao ha cliente cadastrado...", scanner);
        }
    }

}
