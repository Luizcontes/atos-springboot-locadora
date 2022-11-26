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
import contes.atosspringbootlocadora.Models.Cliente;
import contes.atosspringbootlocadora.Repository.ClienteRepository;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping(value = "")
    public ResponseEntity<List<Cliente>> getAllClientes(@RequestParam(required = false) String nome) {

        List<Cliente> clientes = new ArrayList<Cliente>();

        if (nome == null)
            clienteRepository.findAll().forEach(clientes::add);
        else 
            clienteRepository.findByNome(nome).forEach(clientes::add);

        if (clientes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado na base de dados"));

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente _cliente = clienteRepository.save(new Cliente(cliente.getCpf(), cliente.getNome(), cliente.getCnh(), cliente.getEndereco()));
        return new ResponseEntity<Cliente>(_cliente, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        Cliente _cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado na base de dados"));

        _cliente.setNome(cliente.getNome());
        _cliente.setEndereco(cliente.getEndereco());
        _cliente.setCpf(cliente.getCpf());
        _cliente.setCnh(cliente.getCnh());

        return new ResponseEntity<Cliente>(clienteRepository.save(_cliente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id) {
        clienteRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
