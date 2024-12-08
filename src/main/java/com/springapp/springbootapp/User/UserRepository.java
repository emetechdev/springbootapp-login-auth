package com.springapp.springbootapp.User;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    /** UserRepository extiende de JpaRepository para que se pueda crear el repositorio. 
     * JPA en general proporciona las instrucciones basicas para operar con la BD
     */
    Optional<User> findByUsername(String username); 
}
