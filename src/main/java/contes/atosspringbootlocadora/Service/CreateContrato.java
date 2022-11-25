package contes.atosspringbootlocadora.Service;

import java.time.LocalDate;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Context;
import contes.atosspringbootlocadora.Message;
import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Cliente;
import contes.atosspringbootlocadora.Repository.Contrato;
import contes.atosspringbootlocadora.Repository.Automovel;

public class CreateContrato implements Strategy {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;
    long cpf = 0;
    char opcao;

    Context context = new Context(new Default(scanner));

    public CreateContrato(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
    }

    public void execute() {

        Contrato contrato = new Contrato();
        contrato.setData(LocalDate.now());

        System.out.println("");
        System.out.println("INICIAR NOVO CONTRATO");
        System.out.println("");

        while (true) {
            try {
                System.out.print("CPF do cliente: ");
                cpf = scanner.nextLong();
                contrato.setCliente(entityManager.find(Cliente.class, cpf));
                break;
            } catch (Exception e) {
                System.out.print("\nCPF inserido com formato invalido...");
                scanner.nextLine();
            } finally {
                scanner.nextLine();
            }
        }

        if (contrato.getCliente() == null) {
            System.out.println("\nCliente nao cadastrado na base de dados...");
            System.out.print("Deseja cadastra-lo? sim(s) nao(n): ");
            opcao = scanner.nextLine().charAt(0);
            // System.out.println(opcao);
            if (opcao == 's') {
                Message.printEnd();
                // context = new Context(new CreateCliente(scanner));
                context.executeStrategy();
                contrato.setCliente(entityManager.find(Cliente.class, cpf));
            }
        }

        if (contrato.getCliente() != null) {
            System.out.print("Placa do automovel: ");
            String placa = scanner.nextLine();

            contrato.setAutomovel(entityManager.find(Automovel.class, placa));

            if (contrato.getAutomovel() == null) {
                Message.printEnd("\nNa ha automovel cadastrado com esta placa...", scanner);
                System.out.print("Deseja cadastra-lo? sim(s) nao(n): ");
                opcao = scanner.nextLine().charAt(0);
                // System.out.println(opcao);

                if (opcao != 's') {
                    Message.printEnd("Contrato nao inicializado...", scanner);
                } else {
                    Message.printEnd();
                    context = new Context(new CreateAutomovel(scanner));
                    context.executeStrategy();
                    contrato.setAutomovel(entityManager.find(Automovel.class, placa));
                }
            }

            if (contrato.getAutomovel() != null) {
                System.out.print("Valor da diaria: ");
                contrato.setPreco(scanner.nextDouble());
                scanner.nextLine();

                System.out.print("Quantidade de dias: ");
                contrato.setPeriodo(scanner.nextInt());
                scanner.nextLine();

                System.out.println("\nDADOS DA RESERVA");
                System.out.println(contrato);

                entityManager.getTransaction().begin();
                entityManager.persist(contrato);
                entityManager.getTransaction().commit();
                entityManager.close();
        
                Message.printEnd("\nContrato inicializado com sucesso...", scanner);
            }
        }

        Message.printEnd();

    }
}
