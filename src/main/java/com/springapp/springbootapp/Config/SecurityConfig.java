package com.springapp.springbootapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springapp.springbootapp.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

/** 
 * Aclaracion, ya tenemos "SecurityConfig" por defecto agregado a todas las rutas.
 * Asi que creamos un paquete con configuraciones iniciales que van a ser los filtros
 * por los cuales van a pasar las peticiones.
 * 
 * Por ello contiene toda la cadena de filtros que se van a ir ejecutando.
 * Primero se trabajan los endpoints que se dejan publicos y despues el que esta protegido
 *  */

/** "@Configuration" define que hay metodos que van a estar marcados con la anotation "Bean",
 * los cuales se van a utilizar para configurar y crear los objetos que vamos a requerir en la aplicacion.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    /** Metodo para restringir el acceso a nuestras rutas */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http  // Aca empieza la cadena de filtros
           // El framework ya agrega a los post una autenticacion basada en un token csrf valido
           // por eso se desabilita (para facilitar la prueba)
            .csrf(csrf -> 
                csrf
                .disable())
            // authorizeHttpRequests: filtra las rutas protegidas y las publicas
            .authorizeHttpRequests(authRequest ->
              authRequest
                // Valida si el request matchea o no con la ruta "/auth/ **cualquier cosa"
                // para determinar si lo deja pasar o no
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                )
            .sessionManagement(sessionManager->
                sessionManager 
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
            
            
    }

}
