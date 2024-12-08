package com.springapp.springbootapp.cliente.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.springbootapp.cliente.model.entity.Cliente;
import com.springapp.springbootapp.cliente.service.ICliente;


@RestController // Define a la clase como un controlador
@RequestMapping("/api/v1")// Se indica que es un recurso que va a ser consumido y va a trabajar mediante peticiones
public class ClienteController {
    
    @Autowired
    private ICliente clienteService;

    /** Metodos */

    /** Asocia este metodo (funcion dentro del controller) con el método
     * http. En este caso lo define como un 'post'. Y recibe por parametro el
     * fragmento de url que concatena con "RequestMapping"
     */
    @PostMapping("cliente") 
    public Cliente create(@RequestBody Cliente cliente){
        /** "@RequestBody" Indica que va a recibir un body cuando reciba el post */
        return clienteService.save(cliente);
    }

    @PutMapping("cliente")
    public Cliente update(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @DeleteMapping("cliente/{id}")
    public void delete(@PathVariable Integer id){
        Cliente clienteToDelete = clienteService.findById(id);
        clienteService.delete(clienteToDelete);
    }

   /*  @GetMapping("cliente/{id}")
    public Cliente showById(@PathVariable Integer id){
        return clienteService.findById(id);
    } */
    @GetMapping("cliente")
    public String showById(){
        return "hola";
    }

}
