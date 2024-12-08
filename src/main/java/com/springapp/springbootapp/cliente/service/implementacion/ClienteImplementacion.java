package com.springapp.springbootapp.cliente.service.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springapp.springbootapp.cliente.model.dao.ClienteDao;
import com.springapp.springbootapp.cliente.model.entity.Cliente;
import com.springapp.springbootapp.cliente.service.ICliente;




@Service // Indica que la clase trabaja como un servicio dentro de la logica de negocio
/** Implementacion de la interfaz "ICliente" */
public class ClienteImplementacion implements ICliente {
    
    @Autowired // Esta es una forma de inyectar dependencia
    private ClienteDao clienteDao;

    /** "@Transactional" hace que las operaciones (ej: insert, update o delet)
     * se manejen como transacciones en la BD, cosa que que si la operacion se cae
     * por algun motivo se haga un rollback.
     * Para los ejemplos de solo consulta se agrega un "readOnly=true"
     */


    @Transactional
    @Override
    public Cliente save(Cliente cliente){
        return clienteDao.save(cliente);
    }

    /** Aclaracion: "readOnly = true" viene del springframework.
     * Cuando se importa de jakarta (JEE) no lo reconoce
     */
    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id){
        // ".orElse(null)" es un método que ofrece JPA para devolver 
        //(en el ejemplo un null) otro valor en caso de que no se pueda obtener
        // el cliente por id
        return clienteDao.findById(id).orElse(null); 
    }

    @Transactional
    @Override
    public void delete(Cliente cliente){
        clienteDao.delete(cliente);
    }
}
