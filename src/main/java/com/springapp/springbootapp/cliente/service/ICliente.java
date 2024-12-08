package com.springapp.springbootapp.cliente.service;

import com.springapp.springbootapp.cliente.model.entity.Cliente;

/** ICliente es la interfaz que me va a proveer los métodos
 * que me sirven para manipular el CRUD.
 */
public interface ICliente {
    
    Cliente save(Cliente cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);
}
