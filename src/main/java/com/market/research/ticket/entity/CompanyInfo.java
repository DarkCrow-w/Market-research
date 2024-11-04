package com.market.research.ticket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_info")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long companyInfoId;
    private String ticketCode;
    private String ticketName;
    private Double totalMarketValue;
    private Double circulationValue;
    private String status;
    private String listedDate;
    private String province;
    private String thirdLevel;
    private String business;
    private String prodName;
    private String prodType;
    private Double funds;
    private Double tradingFinancialAssets;
    private Double assetLiabilityRatio;
    private Double operatingIncome;
    private Double netProfit;
    private String compType;
    private String address;
    private String office;
    private String city;
    private String countyLevelCity;
    private String tel;
    private String email;

}
