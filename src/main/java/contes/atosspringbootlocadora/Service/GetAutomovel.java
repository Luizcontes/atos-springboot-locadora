package contes.atosspringbootlocadora.Service;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Message;
import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Automovel;

public class GetAutomovel implements Strategy{

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;

    public GetAutomovel(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
    }

    public void execute() {
        
        System.out.println("BUSCAR AUTOMOVEL");
        
        System.out.print("\nDigite a placa do automovel que deseja buscar: ");
            String placa = scanner.nextLine();
            // scanner.nextLine();
        
        Automovel automovel = entityManager.find(Automovel.class, placa);

        if (automovel == null) {
            Message.printEnd("\nNa ha automovel cadastrado com esta placa...", scanner);;
        } else {
            System.out.println(automovel);
            Message.printEnd("\nPressione qualquer tecla para voltar ao menu anterior...", scanner);
        }

    }
}
