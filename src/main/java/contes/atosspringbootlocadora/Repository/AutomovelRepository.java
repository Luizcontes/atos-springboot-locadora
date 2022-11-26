package contes.atosspringbootlocadora.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import contes.atosspringbootlocadora.Models.Automovel;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
 
    List<Automovel> findByPlaca(String placa);

    List<Automovel> findByAno(int ano);

    List<Automovel> findByMarca(String marca);
}
