package com.example.demo.repository;

import com.example.demo.model.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

    Collection<Reserva> findReservasByCliente_Id(Integer id);
}
