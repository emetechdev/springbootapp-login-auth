package com.springapp.springbootapp.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springapp.springbootapp.Jwt.JwtService;
import com.springapp.springbootapp.User.Rol;
import com.springapp.springbootapp.User.User;
import com.springapp.springbootapp.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    /** "userRepository" se usa para crear el objeto y guardarlo en la bd */
    private final UserRepository userRepository;
    /** jwtService invoca el servicio */
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    /* "RegisterRequest" esta clase nos permite acceder a los getters y setters del request */
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder() // Se usa el patron de construccion de objetos "builder"
                .username(request.getUsername()) // Se le va cargando cada atributo que se saca del request
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.lastname)
                .country(request.getCountry())
                .rol(Rol.USER) // Se asigna directamente el rol de usuario
                .build(); // guarda

        userRepository.save(user); // Se guara el usuario en la bd

        // Aca tambien se usa el patron de diseño "builder" para crear el objeto "AuthResponse"
        // y cargarle el token
        return AuthResponse.builder() 
                .token(jwtService.getToken(user))
                .build();

    }

}
