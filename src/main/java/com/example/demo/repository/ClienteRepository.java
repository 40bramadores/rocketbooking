package com.example.demo.repository;


import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    Optional<Cliente> findByNombreAndEmailEquals (String nombre, String email);
}
