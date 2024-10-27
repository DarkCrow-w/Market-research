package com.market.research.ticket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInfo {

    private Long companyInfoId;
    private String ticketCode;
    private String ticketName;
    private String totalMarketValue;
    private String circulationValue;
    private String status;
    private String listedDate;
    private String province;
    private String thirdLevel;
    private String business;
    private String prodName;
    private String prodType;
    private String funds;
    private String tradingFinancialAssets;
    private String assetLiabilityRatio;
    private String operatingIncome;
    private String netProfit;
    private String compType;
    private String address;
    private String office;
    private String city;
    private String countyLevelCity;
    private String tel;
    private String email;

}
