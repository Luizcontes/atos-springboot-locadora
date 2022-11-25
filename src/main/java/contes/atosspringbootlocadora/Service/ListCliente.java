package contes.atosspringbootlocadora.Service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Message;
import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Cliente;

public class ListCliente implements Strategy {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;
    int counter = 0;

    public ListCliente(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
    }

    public void execute() {

        System.out.println("LISTAR TODOS OS CLIENTES ATIVOS!");
        
        List<Cliente> clientes = entityManager.createQuery("Select a from Cliente a", Cliente.class)
        .getResultList();
        
        clientes.forEach(item -> {
            if (item.isAtivo()) {
                counter++;
                System.out.println(item);
            }
        });

        if (counter != 0) {
            Message.printEnd("\nPressione qualuer tecla para continuar...", scanner);
        } else {
            Message.printEnd("\nNao ha cliente cadastrado...", scanner);
        }
    }

}
