package com.example.demo.repository;


import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    @Query(value = "SELECT * FROM CLIENTE WHERE nombre = ?1 AND email = ?2 LIMIT 1", nativeQuery = true)
    Optional<Cliente> getClienteByNombreContainsAndEmailContains (String nombre, String email);
}
