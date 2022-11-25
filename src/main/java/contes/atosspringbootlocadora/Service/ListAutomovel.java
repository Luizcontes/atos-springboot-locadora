package contes.atosspringbootlocadora.Service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Message;
import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Automovel;

public class ListAutomovel implements Strategy {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;
    int counter = 0;

    public ListAutomovel(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
    }

    public void execute() {
        System.out.println("LISTAR TODOS OS AUTOMOVEIS ATIVOS!");
        
        List<Automovel> automoveis = entityManager.createQuery("Select a from Automovel a", Automovel.class)
                .getResultList();

        automoveis.forEach(item -> {
            if (item.isAtivo()) {
                counter++;
                System.out.println(item);
            }
        });

        if(counter != 0) {
            Message.printEnd("\nPressione qualuer tecla para continuar...", scanner);
        } else {
            Message.printEnd("\nNao ha automovel cadastrado...", scanner);
        }
    }

}
