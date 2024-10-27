package com.market.research.ticket.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.research.ticket.entity.IndustrialParty;
import com.market.research.ticket.repository.IndustrialPartyRepository;
import com.market.research.ticket.service.IndustrialPartyService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class IndustrialPartyServiceImpl implements IndustrialPartyService {

    @Autowired
    private IndustrialPartyRepository industrialPartyRepository;

    @Override
    public List<IndustrialParty> getAllIndustrialParties() {
        return industrialPartyRepository.findAll();
    }

    @Override
    public IndustrialParty saveIndustrialParty(IndustrialParty industrialParty) {
        return industrialPartyRepository.save(industrialParty);
    }

    @Override
    public boolean existsByTicketName(String ticketName) {
        return industrialPartyRepository.existsByTicketName(ticketName);
    }

    @Override
    public Optional<IndustrialParty> findByName(String name) {
        return industrialPartyRepository.findByTicketName(name);
    }

    @Override
    public void deleteByTicketName(String name) {
        industrialPartyRepository.deleteByTicketName(name);
    }

    @Override
    @Transactional
    public IndustrialParty updateByTicketName(String name, IndustrialParty updates) {
        IndustrialParty existingParty = industrialPartyRepository.findByTicketName(name)
            .orElseThrow(() -> new EntityNotFoundException("未找到名为 " + name + " 的实体"));

            if (updates.getIndustrialPartyId() != null) {
                existingParty.setIndustrialPartyId(updates.getIndustrialPartyId());
            }
            if (updates.getTicketName() != null) {
                existingParty.setTicketName(updates.getTicketName());
            }
            if (updates.getFirstLevel() != null) {
                existingParty.setFirstLevel(updates.getFirstLevel());
            }
            if (updates.getThirdLevel() != null) {
                existingParty.setThirdLevel(updates.getThirdLevel());
            }
            if (updates.getDirection() != null) {
                existingParty.setDirection(updates.getDirection());
            }
            if (updates.getProduction() != null) {
                existingParty.setProduction(updates.getProduction());
            }
            if (updates.getDockingPerson() != null) {
                existingParty.setDockingPerson(updates.getDockingPerson());
            }
            if (updates.getPosition() != null) {
                existingParty.setPosition(updates.getPosition());
            }
            if (updates.getMarketTime() != null) {
                existingParty.setMarketTime(updates.getMarketTime());
            }
        

        return industrialPartyRepository.save(existingParty);
    }
}
