package com.springapp.springbootapp.Jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /**
     * Extendemos de la clase abstracta "OncePerRequestFilter" que
     * nos permite crear filtros personalizados. Esta clase garantiza que
     * el filtro se ejecute solo una vez por cada peticion http.
     * Incluso si hay multiples filtros dentro de la cadena de filtros
     */

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * "doFilterInternal" es el metodo que va a realizar todos los filtros
     * relacionados al token
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Primero obtenemos el token
        final String token = getTokenFromRequest(request);
        final String username;

        if (token == null) {
            // Si no hay token, se le devuelve el control a la cadena de filtro
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        /**
         * Se le pasa por parametro el request para que "getTokenFromRequest" saque del
         * encabezado el token
         */
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            // "StringUtils.hasText(authHeader)" valida que el texto exista en la variable
            // authHeader
            // "authHeader.startsWith("Bearer ")" valida que el texto comience con 'Barer' y
            // el espacio :D
            return authHeader.substring(7);
        }
        return null;
    }

}
