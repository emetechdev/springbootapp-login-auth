package com.springapp.springbootapp.User;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // Mapea el "User" como una entidad para la bd
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements UserDetails {
    /** Campos para la bd. Implementa "UserDetails" que viene en el framework. */
    @Id
    @GeneratedValue
    Integer id;
    
    @Basic
    @Column(nullable = false)
    String username;
    
    @Column(nullable = false)
    String lastname;
    
    String firstname;
    
    String country;
    
    String password;
    
    @Enumerated(EnumType.STRING)
    Rol rol;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /** Retorno una lista con un unico objeto que devuelve la autoridad otorgada
         * por eso se pasa por parametro el rol.
         */
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Hrdcodeo true porque no agrego expiracion
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Hrdcodeo true porque no valido bloqueo
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Hrdcodeo true porque no valido credenciales vencidas
    }

    @Override
    public boolean isEnabled() {
        return true; // Hrdcodeo true porque no valido si esta habilitado
    }
}
