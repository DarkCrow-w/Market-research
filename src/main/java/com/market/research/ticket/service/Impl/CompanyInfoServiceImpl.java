package com.market.research.ticket.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.market.research.ticket.entity.CompanyInfo;
import com.market.research.ticket.repository.CompanyInfoRepository;
import com.market.research.ticket.service.CompanyInfoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Override
    public List<CompanyInfo> getAllCompanyInfos() {
        return companyInfoRepository.findAll();
    }

    @Override
    public Optional<CompanyInfo> getCompanyInfoByTicketCode(String ticketCode) {
        return companyInfoRepository.findByTicketCode(ticketCode);
    }

    @Override
    public CompanyInfo saveCompanyInfo(CompanyInfo companyInfo) {
        return companyInfoRepository.save(companyInfo);
    }

    @Override
    @Transactional
    public CompanyInfo updateCompanyInfo(String ticketCode, CompanyInfo updates) {
        CompanyInfo existingCompany = companyInfoRepository.findByTicketCode(ticketCode)
            .orElseThrow(() -> new EntityNotFoundException("未找到代码为 " + ticketCode + " 的公司"));

        if (updates.getCompanyInfoId() != null) {
            existingCompany.setCompanyInfoId(updates.getCompanyInfoId());
        }
        if (updates.getTicketCode() != null) {
            existingCompany.setTicketCode(updates.getTicketCode());
        }
        if (updates.getTicketName() != null) {
            existingCompany.setTicketName(updates.getTicketName());
        }
        if (updates.getTotalMarketValue() != null) {
            existingCompany.setTotalMarketValue(updates.getTotalMarketValue());
        }
        if (updates.getCirculationValue() != null) {
            existingCompany.setCirculationValue(updates.getCirculationValue());
        }
        if (updates.getStatus() != null) {
            existingCompany.setStatus(updates.getStatus());
        }
        if (updates.getListedDate() != null) {
            existingCompany.setListedDate(updates.getListedDate());
        }
        if (updates.getProvince() != null) {
            existingCompany.setProvince(updates.getProvince());
        }
        if (updates.getThirdLevel() != null) {
            existingCompany.setThirdLevel(updates.getThirdLevel());
        }
        if (updates.getBusiness() != null) {
            existingCompany.setBusiness(updates.getBusiness());
        }
        if (updates.getProdName() != null) {
            existingCompany.setProdName(updates.getProdName());
        }
        if (updates.getProdType() != null) {
            existingCompany.setProdType(updates.getProdType());
        }
        if (updates.getFunds() != null) {
            existingCompany.setFunds(updates.getFunds());
        }
        if (updates.getTradingFinancialAssets() != null) {
            existingCompany.setTradingFinancialAssets(updates.getTradingFinancialAssets());
        }
        if (updates.getAssetLiabilityRatio() != null) {
            existingCompany.setAssetLiabilityRatio(updates.getAssetLiabilityRatio());
        }
        if (updates.getOperatingIncome() != null) {
            existingCompany.setOperatingIncome(updates.getOperatingIncome());
        }
        if (updates.getNetProfit() != null) {
            existingCompany.setNetProfit(updates.getNetProfit());
        }
        if (updates.getCompType() != null) {
            existingCompany.setCompType(updates.getCompType());
        }
        if (updates.getAddress() != null) {
            existingCompany.setAddress(updates.getAddress());
        }
        if (updates.getOffice() != null) {
            existingCompany.setOffice(updates.getOffice());
        }
        if (updates.getCity() != null) {
            existingCompany.setCity(updates.getCity());
        }
        if (updates.getCountyLevelCity() != null) {
            existingCompany.setCountyLevelCity(updates.getCountyLevelCity());
        }
        if (updates.getTel() != null) {
            existingCompany.setTel(updates.getTel());
        }
        if (updates.getEmail() != null) {
            existingCompany.setEmail(updates.getEmail());
        }

        return companyInfoRepository.save(existingCompany);
    }

    @Override
    public void deleteCompanyInfo(String ticketCode) {
        companyInfoRepository.deleteByTicketCode(ticketCode);
    }

    @Override
    public boolean existsByTicketCode(String ticketCode) {
        return companyInfoRepository.existsByTicketCode(ticketCode);
    }

}
