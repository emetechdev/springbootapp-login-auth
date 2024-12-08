package com.springapp.springbootapp.Jwt;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service // Defino la clase como un servicio
public class JwtService {

    private static final String SECRET_KEY="clave-supersecreta";

    /** "getToken es un metodo que genera un token y lo devuelve como un choclo de string" */
    public String getToken(UserDetails user) {
        /** uso el metodo "getToken" al cual le paso un "HashMap" que es una clase de colecciones
         * que se usa para almacenar pares de clave:valor y aca lo vamos a usar para pasar info
         * adicional en el token
         */
        return getToken(new HashMap<>(), user);
    }

    /** El "getToken" que retorno en la linea 30 es este que declaro acá.
     * @param extraClaims Aca recibe el HashMap, que se tipa en éste caso de la siguiente 
     * forma: Map<String, Object>. Asi es como se tipa un parametro de clave:valor
     * @param user El usuario que traigo del request con la autorizacion y todas esas cosas
     * @return Devuelve el token
     */
    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
        return Jwts // retorno un token usando la libreria "Jwts"
            .builder() // uso un builder para crear el objeto
            .setClaims(extraClaims) // las cosas con clave:valor
            .setSubject(user.getUsername()) //el usuario (usernae)
            .setIssuedAt(new Date(System.currentTimeMillis())) // fecha de creacion (como fecha se pasa por parametro la del sistema)
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24)) // Expiracion (la calcula a partir de la fecha de creacion)
            .signWith(getKey(), SignatureAlgorithm.HS256) // Se le pasa la firma
            .compact(); // "compact" crea el objeto y lo serializa
    }

    /** Convierte la clave a base 64 para pasarla como clave al token */
    private Key getKey() {
       byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
    
}
