package com.springapp.springbootapp.cliente.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.springapp.springbootapp.cliente.model.entity.Cliente;


// Es una interfaz
public interface ClienteDao extends CrudRepository<Cliente, Integer> {
    /** "CrudRepository<Cliente, Integer>" lo que hace es crear un repositorio y recibe como parametros
     * un "Cliente" (por eso usamos la clase "Cliente") y un entero que sirve para identificar al cliente. 
     * El CrudRepository se usa solo cuando se hace un CRUD. Para consultas inmensas no se usa "CrudRepository"
     * sino que se usa "PagingAndSortingRepository" que sirve para paginar miles de datos. */ 
    
}
