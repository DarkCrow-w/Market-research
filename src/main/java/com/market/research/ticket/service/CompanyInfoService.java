package com.market.research.ticket.service;

import java.util.List;
import java.util.Optional;

import com.market.research.ticket.entity.CompanyInfo;

public interface CompanyInfoService {

    List<CompanyInfo> getAllCompanyInfos();
    
    Optional<CompanyInfo> getCompanyInfoByTicketCode(String ticketCode);
    
    CompanyInfo saveCompanyInfo(CompanyInfo companyInfo);
    
    CompanyInfo updateCompanyInfo(String ticketCode, CompanyInfo companyInfo);
    
    void deleteCompanyInfo(String ticketCode);
    
    boolean existsByTicketCode(String ticketCode);
    
}
