package contes.atosspringbootlocadora.Service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Message;
import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Cliente;

public class DestroyCliente implements Strategy {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;
    int counter = 0;


    public DestroyCliente(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
    }

    public void execute() {

        System.out.println("REMOVER UM CLIENTE DA BASE DE DADOS");
        List<Cliente> clientes = entityManager.createQuery("Select a from Cliente a", Cliente.class)
                .getResultList();

        clientes.forEach(cliente -> {
            if (cliente.isAtivo()) {
                counter++;
                System.out.println(cliente);
            }
        });

        if (counter != 0 ) {
            System.out.print("\nDigite o numero do CPF do cliente que deseja remover: ");
            long cpf = scanner.nextLong();
            scanner.nextLine();
            try {
                Cliente cliente = entityManager.find(Cliente.class, cpf);
                cliente.setAtivo(false);
                entityManager.getTransaction().begin();
                entityManager.merge(cliente);
                entityManager.getTransaction().commit();
                entityManager.close();

                Message.printEnd("\nCliente removido com sucesso...", scanner);
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                Message.printEnd("\nCliente inexistente na base de dados...", scanner);
            } finally {
                // scanner.nextLine();
            }
        } else {
            Message.printEnd("\nNao ha cliente cadastrado...", scanner);
        }
    }
}
