package contes.atosspringbootlocadora.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import contes.atosspringbootlocadora.Repository.Cliente;
import contes.atosspringbootlocadora.Service.CreateCliente;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    

    @GetMapping(value = "")
    public String getClientes() {
        
        // GetCliente gCliente = new GetCliente(null);
        return "Get Clientes";
    }

    @PostMapping(value = "")
    public String CreateCliente(@RequestBody Cliente cliente){
        
        CreateCliente createCliente = new CreateCliente(cliente);
        createCliente.execute();

        return "Post Cliente";
    }
}
