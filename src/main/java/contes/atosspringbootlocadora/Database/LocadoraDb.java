package contes.atosspringbootlocadora.Database;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Repository.Automovel;
import contes.atosspringbootlocadora.Repository.Cliente;
import contes.atosspringbootlocadora.Repository.Contrato;

public class LocadoraDb {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Scanner scanner;

    public LocadoraDb(Scanner scanner) {
        this.scanner = scanner;
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    // public LocadoraDb() {
    //     entityFactory = Persistence.createEntityManagerFactory("teste");
    //     entityManager = entityFactory.createEntityManager();
    //     entityManager.getTransaction().begin();
    // }

    public void persist(Contrato contrato) {
        entityManager.persist(contrato);
        entityManager.getTransaction().commit();
    }

    public void persist(Automovel automovel) {
        entityManager.persist(automovel);
        entityManager.getTransaction().commit();
    }

    public void persist(Cliente cliente) {
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }

    public Cliente findCliente(long cpf) {
        Cliente cliente = entityManager.find(Cliente.class, cpf);

        if (cliente == null) {
            return null;
        }

        return cliente;
    }

    public void update(Cliente cliente) {
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
    }

    public List<Cliente> getAllCliente() {

        List<Cliente> clientes = entityManager.createQuery("Select a from Cliente a", Cliente.class)
                .getResultList();

        clientes.forEach(item -> {
            if (item.isAtivo()) {
                System.out.println(item);
            }
        });

        return clientes;
    }

    public void close() {
        entityManager.close();
    }
}
