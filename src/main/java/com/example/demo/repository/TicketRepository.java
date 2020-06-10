package com.example.demo.repository;

import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    @Query(value = "SELECT * FROM TICKET WHERE reserva_id is NULL AND  evento_id = ?1", nativeQuery = true)
    Collection<Ticket> findByReservaIsNull(Integer idEvento);

    @Query(value = "SELECT * FROM TICKET WHERE reserva_id is NULL AND  evento_id = ?1 LIMIT ?2", nativeQuery = true)
    Collection<Ticket> findXByReservaIsNull(Integer id, Integer cantidad);


}
