package contes.atosspringbootlocadora.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import contes.atosspringbootlocadora.Models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    List<Cliente> findByCpf(Long cpf);

    List<Cliente> findByCnh(String cnh);

    List<Cliente> findByNome(String nome);
}
