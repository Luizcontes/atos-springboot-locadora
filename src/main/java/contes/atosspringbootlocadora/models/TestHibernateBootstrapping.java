package contes.atosspringbootlocadora.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import contes.atosspringbootlocadora.Repository.Cliente;

public class TestHibernateBootstrapping {
    

    public void bootstrapping(){


        ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();

        SessionFactory sessionFactory = new MetadataSources(standardRegistry)
            .addAnnotatedClass(Cliente.class).buildMetadata()
            .buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Cliente c = new Cliente(123, "Luiz", "abcd", "Rua Miguel 8");
        session.persist(c);

        session.getTransaction().commit();
        session.close();
    }
}
