package com.thoughtworks.parkinglot.repository;

import com.thoughtworks.parkinglot.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "select * from ticket where ticket.id = ?1", nativeQuery = true)
    Ticket findTicketByTicketId(Long ticketId);
}
