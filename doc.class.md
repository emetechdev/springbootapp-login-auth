# Metodo HTTPS - Urls

Notas de clase

## Forma INCORRECTA de estructurar urls de apis

### Get incorrecto

- /api/listarClientes
- /api/cliente-eliminar/14
- /api/cliente/guardar/25

### Post incorrecto

- /api/listarClientes
- /api/cliente/eliminar/14
- /api/cliente/actualizar?id=30
  
### Put incorrecto

- /api/cliente/actalizar?id=98&nombre=fulano&apellido=detal&edad=20

## Forma CORRECTA de estructurar urls de apis

### Get correcto

- /api/v1/clientes
- /api/v1/cliente?id=12
- /api/v1/cliente/2/telefonos
- /api/v1/cliente?page=2&size=10&order=desc

### Post correcto

- /api/v1/cliente
- /api/v1/usuario/12/restablecer-clave
  
### Put correcto

- /api/v1/cliente
- /api/v1/cliente/1
- /api/v1/cliente?id=3

### Delete correcto

- /api/v1/cliente?id=3

# Relacion entre clases

Si en la BD tengo un DER donde hay una tabla *Usuario* y otra *Rol*, donde un *Usuario* puede tener un solo *Rol* y donde un *Rol* puede estar en muchos *Usuarios*.
Lo que se puede hacer a nivel clases es definir la **Clase Rol**  con un campo **@OneToMany** y a la **Clase Usuario** marcarle el campo **fk_rol** con un **@ManyToOne** para relacionar ambas clases mediante la **fk**.
