package com.springapp.springbootapp.cliente.model.entity;

import jakarta.persistence.*; // jpa
import lombok.*;

import java.io.Serializable;
//import java.io.Serializable;
import java.util.Date;

@Data // Muestra la data (usa lambok)
@AllArgsConstructor // Muestra los constructores (usa lambok)
@NoArgsConstructor // Permite un constructor vacío (usa lambok)
@ToString // Tambien genera un toString() (usa lambok)
@Builder
@Entity // Define a la clase como una entidad (usa jpa)
@Table(name = "clientes") // Ya definida la clase como una entidad, "Table" asocia esta clase con la tabla con name "clientes"
public class Cliente implements Serializable { // Con 'implements Serializable' se serializa la clase
    
    @Id // Define a la propiedad "idCliente" como 'id' en la tabla de la bd
    @Column(name = "id_cliente") // Asocia la propiedad "idCliente" con el campo "id_cliente" en la tabla de la bd
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hace que el campo "idCliente sea autoincrementable. Para otra BD se usa "strategy=AUTO"
    private Integer idCliente;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;
}
