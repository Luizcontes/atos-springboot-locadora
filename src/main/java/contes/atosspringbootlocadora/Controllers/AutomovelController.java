package contes.atosspringbootlocadora.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import contes.atosspringbootlocadora.Exception.ResourceNotFoundException;
import contes.atosspringbootlocadora.Models.Automovel;
import contes.atosspringbootlocadora.Repository.AutomovelRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/automovel")
@CrossOrigin(origins = "*")
public class AutomovelController {

    @Autowired
    AutomovelRepository automovelRepository;

    @GetMapping(value = "")
    public ResponseEntity<List<Automovel>> getAllAutomoveis(@RequestParam(required = false) String placa) {

        List<Automovel> automoveis = new ArrayList<Automovel>();

        if (placa == null)
            automovelRepository.findAll().forEach(automoveis::add);
        else
            automovelRepository.findByPlaca(placa).forEach(automoveis::add);

        if (automoveis.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        log.info("Retorna todos os automoveis cadastrados");

        return new ResponseEntity<>(automoveis, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Automovel> getAutomovelById(@PathVariable("id") Long id) {
        Automovel automovel = automovelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Automovel nao encontrado na base de dados"));

        
        log.info("Retorna os dados de um automovel especifico");
                return new ResponseEntity<Automovel>(automovel, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Automovel> createAutomovel(@RequestBody Automovel automovel) {
        Automovel _automovel = automovelRepository.save(new Automovel(automovel.getAno(), automovel.getMarca(),
                automovel.getModelo(), automovel.getPlaca(), automovel.getVersao()));
        
        log.info("Salva um automovel na base de dados");
                return new ResponseEntity<Automovel>(_automovel, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Automovel> updateAutomovel(@PathVariable("id") Long id, @RequestBody Automovel automovel) {
        Automovel _automovel = automovelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado na base de dados"));

        _automovel.setAno(automovel.getAno());
        _automovel.setMarca(automovel.getMarca());
        _automovel.setModelo(automovel.getModelo());
        _automovel.setPlaca(automovel.getPlaca());
        _automovel.setVersao(automovel.getVersao());


        log.info("Altera os dados de um automovel");
        return new ResponseEntity<Automovel>(automovelRepository.save(_automovel), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteAutomovel(@PathVariable("id") long id) {
        automovelRepository.deleteById(id);


        log.info("Apaga os dados de um automovel");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
