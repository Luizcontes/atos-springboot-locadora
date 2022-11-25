package contes.atosspringbootlocadora.Service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Message;
import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Automovel;

public class DestroyAutomovel implements Strategy {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;
    int counter = 0;


    public DestroyAutomovel(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
    }

    public void execute() {

        System.out.println("REMOVER UM AUTOMOVEL DA BASE DE DADOS");
        List<Automovel> automoveis = entityManager.createQuery("Select a from Automovel a", Automovel.class)
                .getResultList();

        automoveis.forEach(automovel -> {
            if (automovel.isAtivo()) {
                counter++;
                System.out.println(automovel);
            }
        });

        if (counter != 0 ) {
            System.out.print("\nDigite a placa do automovel que deseja remover: ");
            String placa = scanner.nextLine();
            // scanner.nextLine();
            try {
                Automovel automovel = entityManager.find(Automovel.class, placa);
                automovel.setAtivo(false);
                entityManager.getTransaction().begin();
                entityManager.merge(automovel);
                entityManager.getTransaction().commit();
                entityManager.close();

                Message.printEnd("\nAutomovel removido com sucesso...", scanner);
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                Message.printEnd("\nAutomovel inexistente na base de dados...", scanner);
            } finally {
                // scanner.nextLine();
            }
        } else {
            Message.printEnd("\nNao ha automovel cadastrado...", scanner);
        }
    }
}
