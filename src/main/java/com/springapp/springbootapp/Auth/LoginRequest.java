package com.springapp.springbootapp.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Esta clase es la que recibe las credenciales.
 * 
 */

@Data // Permite crear los getters y setters de manera automatica
@Builder // Permite construir los objetos
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String username;
    String password; 
}
