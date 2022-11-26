package contes.atosspringbootlocadora.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import contes.atosspringbootlocadora.Models.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long>{
    
    List<Contrato> findByClienteId(Long clienteId);
}
