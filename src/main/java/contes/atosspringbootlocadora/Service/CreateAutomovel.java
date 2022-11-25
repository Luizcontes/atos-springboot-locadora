package contes.atosspringbootlocadora.Service;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Message;
import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Automovel;

public class CreateAutomovel implements Strategy {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;

    public CreateAutomovel(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
    }

    public void execute() {

        Automovel automovel = new Automovel();
        automovel.setAtivo(true);

        System.out.println("");
        System.out.println("CADASTRAR NOVO AUTOMOVEL");
        System.out.println("");
        System.out.print("MARCA: ");
        automovel.setMarca(scanner.nextLine());
        System.out.print("MODELO: ");
        automovel.setModelo(scanner.nextLine());
        System.out.print("VERSAO: ");
        automovel.setVersao(scanner.nextLine());
        System.out.print("PLACA: ");
        automovel.setPlaca(scanner.nextLine());

        while (true) {
            try {
                System.out.print("ANO: ");
                automovel.setAno(scanner.nextInt());
                break;
            } catch (Exception e) {
                System.out.print("ANO inserido com formato invalido...");
                scanner.nextLine();
            } finally {
                scanner.nextLine();
            }
        }

        try {

            Automovel tmp = entityManager.find(Automovel.class, automovel.getPlaca());
            // System.out.println(tmp);

            if (tmp == null) {
                entityManager.getTransaction().begin();
                entityManager.persist(automovel);
                entityManager.getTransaction().commit();
                entityManager.close();
                Message.printEnd("\nAutomovel cadastrado com sucesso...", scanner);
            } else {
                tmp.setAtivo(true);
                entityManager.getTransaction().begin();
                entityManager.merge(tmp);
                entityManager.getTransaction().commit();
                entityManager.close();
                Message.printEnd("\nAutomovel ja existe na base de dados, automovel reativado...", scanner);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
