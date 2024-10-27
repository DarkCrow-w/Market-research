package com.market.research.ticket.service;

import java.util.List;
import java.util.Optional;

import com.market.research.ticket.entity.IndustrialParty;

public interface IndustrialPartyService {

    List<IndustrialParty> getAllIndustrialParties();

    IndustrialParty saveIndustrialParty(IndustrialParty industrialParty);

    boolean existsByTicketName(String ticketName);  

    Optional<IndustrialParty> findByName(String name);

    void deleteByTicketName(String name);   

    IndustrialParty updateByTicketName(String name, IndustrialParty updates);

}
