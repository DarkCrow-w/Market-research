package com.market.research.ticket.repository;

import com.market.research.ticket.entity.CompanyInfo;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Long> {

    Optional<CompanyInfo> findByTicketCode(String ticketCode);

    @Transactional
    void deleteByTicketCode(String ticketCode);

    @Transactional
    CompanyInfo updateByTicketCode(String ticketCode, CompanyInfo companyInfo);

    boolean existsByTicketCode(String ticketCode);
    
}