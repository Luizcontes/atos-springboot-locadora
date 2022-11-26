package contes.atosspringbootlocadora.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import contes.atosspringbootlocadora.Models.Contrato;
import contes.atosspringbootlocadora.Repository.ContratoRepository;

@RestController
@RequestMapping("/contrato")
public class ContratoController {
    

    @Autowired
    private ContratoRepository contratoRepository;

    @GetMapping("")
    public List<Contrato> getAllContratosByClienteId() {
        return contratoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Contrato> getContratoById(@PathVariable("id") Long contrato_id) {
        
        return contratoRepository.findById(contrato_id);
    }

    @PostMapping("")
    public void createContrato(@RequestBody Contrato contratoRequest) {

        contratoRepository.save(contratoRequest);
    }
}
