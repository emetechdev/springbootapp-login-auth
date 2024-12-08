package com.springapp.springbootapp.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/** El login y el registro deben ser public */

@RestController // Defino la clase como controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    /** Controlador de autenticacion que expone login y registro. */
    
    private final AuthService authService;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        /** "ResponseEntity" representa toda la respuesta http, incluye los
         * codigos de estado, los encabezados y las respuestas. Esta clase nos
         * da la flexibilidad de configurar y personalizar la respuesta http.
         * Con "<AuthResponse>" definimos que la respuesta va a ser de tipo "AuthResponse"
         */
        return ResponseEntity.ok(authService.login(request)); // Aca ya estoy asumiendo que esta todo ok :D como la seguridad de windows (?)
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
