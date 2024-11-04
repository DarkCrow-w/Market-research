package com.market.research.ticket.repository;

import com.market.research.ticket.entity.IndustrialParty;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustrialPartyRepository extends JpaRepository<IndustrialParty, Long> {

    Optional<IndustrialParty> findByTicketName(String ticketName);

    @Transactional
    void deleteByTicketName(String ticketName);

    @Transactional
    @Query("UPDATE IndustrialParty ip SET ip = :industrialParty WHERE ip.ticketName = :ticketName")
    IndustrialParty updateByTicketName(String ticketName, IndustrialParty industrialParty);

    boolean existsByTicketName(String name);

}