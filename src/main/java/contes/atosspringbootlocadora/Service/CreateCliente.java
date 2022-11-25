package contes.atosspringbootlocadora.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contes.atosspringbootlocadora.Strategy;
import contes.atosspringbootlocadora.Repository.Cliente;

public class CreateCliente implements Strategy {

    EntityManagerFactory entityFactory;
    EntityManager entityManager;

    Cliente cliente;

    public CreateCliente(Cliente cliente) {
        entityFactory = Persistence.createEntityManagerFactory("teste");
        entityManager = entityFactory.createEntityManager();
        this.cliente = cliente;
    }

    public void execute() {

        try {

            Cliente tmp = entityManager.find(Cliente.class, cliente.getId());

            if (tmp == null) {
                entityManager.getTransaction().begin();
                entityManager.persist(cliente);
                entityManager.getTransaction().commit();
                entityManager.close();
                System.out.println("\nCliente cadastrado com sucesso...");
            } else {
                tmp.setAtivo(true);
                entityManager.getTransaction().begin();
                entityManager.merge(tmp);
                entityManager.getTransaction().commit();
                entityManager.close();
                System.out.println("Cliente ja existe na base de dados, cliente reativado...");
            }
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
