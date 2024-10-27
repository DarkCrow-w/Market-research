package com.market.research.ticket.util;

import com.market.research.ticket.entity.IndustrialParty;
import com.market.research.ticket.entity.ProjectProcess;
import com.market.research.ticket.entity.CompanyInfo;
import com.market.research.ticket.repository.IndustrialPartyRepository;
import com.market.research.ticket.repository.ProjectProcessRepository;
import com.market.research.ticket.repository.CompanyInfoRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImportUtil {

    @Autowired
    private IndustrialPartyRepository industrialPartyRepository;

    @Autowired
    private ProjectProcessRepository projectProcessRepository;

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    public void importExcelData(String industrialPartyFilePath, String projectProcessFilePath, String companyInfoFilePath) throws IOException {
        importIndustrialParties(industrialPartyFilePath);
        importProjectProcesses(projectProcessFilePath);
        importCompanyInfo(companyInfoFilePath);
    }

    private void importIndustrialParties(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<IndustrialParty> parties = new ArrayList<>();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头
                IndustrialParty party = new IndustrialParty();
                party.setTicketName(getCellValueAsString(row.getCell(0)));
                party.setFirstLevel(getCellValueAsString(row.getCell(1)));
                party.setThirdLevel(getCellValueAsString(row.getCell(2)));
                party.setDirection(getCellValueAsString(row.getCell(3)));
                party.setProduction(getCellValueAsString(row.getCell(4)));
                party.setDockingPerson(getCellValueAsString(row.getCell(5)));
                party.setPosition(getCellValueAsString(row.getCell(6)));
                party.setMarketTime(getCellValueAsString(row.getCell(7)));
                parties.add(party);
            }
            industrialPartyRepository.saveAll(parties);
        }
    }

    private void importProjectProcesses(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(1);
            List<ProjectProcess> processes = new ArrayList<>();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头
                ProjectProcess process = new ProjectProcess();
                process.setFirstLevel(getCellValueAsString(row.getCell(0)));
                process.setSecLevel(getCellValueAsString(row.getCell(1)));
                process.setBusiness(getCellValueAsString(row.getCell(2)));
                process.setStartTime(getCellValueAsString(row.getCell(3)));
                process.setProgress(getCellValueAsString(row.getCell(4)));
                process.setFinancingShare(getCellValueAsString(row.getCell(5)));
                process.setFinancier(getCellValueAsString(row.getCell(6)));
                process.setBusinessUnit(getCellValueAsString(row.getCell(7)));
                process.setLp(getCellValueAsString(row.getCell(8)));
                processes.add(process);
            }
            projectProcessRepository.saveAll(processes);
        }
    }

    private void importCompanyInfo(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(2);
            List<CompanyInfo> companies = new ArrayList<>();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头
                CompanyInfo company = new CompanyInfo();
                company.setTicketCode(getCellValueAsString(row.getCell(0)));
                company.setTicketName(getCellValueAsString(row.getCell(1)));
                company.setTotalMarketValue(getCellValueAsString(row.getCell(2)));
                company.setCirculationValue(getCellValueAsString(row.getCell(3)));
                company.setStatus(getCellValueAsString(row.getCell(4)));
                company.setListedDate(getCellValueAsString(row.getCell(5)));
                company.setProvince(getCellValueAsString(row.getCell(6)));
                company.setThirdLevel(getCellValueAsString(row.getCell(7)));
                company.setBusiness(getCellValueAsString(row.getCell(8)));
                company.setProdName(getCellValueAsString(row.getCell(9)));
                company.setProdType(getCellValueAsString(row.getCell(10)));
                company.setFunds(getCellValueAsString(row.getCell(11)));
                company.setTradingFinancialAssets(getCellValueAsString(row.getCell(12)));
                company.setAssetLiabilityRatio(getCellValueAsString(row.getCell(13)));
                company.setOperatingIncome(getCellValueAsString(row.getCell(14)));
                company.setNetProfit(getCellValueAsString(row.getCell(15)));
                company.setCompType(getCellValueAsString(row.getCell(16)));
                company.setAddress(getCellValueAsString(row.getCell(17)));
                company.setOffice(getCellValueAsString(row.getCell(18)));
                company.setCity(getCellValueAsString(row.getCell(19)));
                company.setCountyLevelCity(getCellValueAsString(row.getCell(20)));
                company.setTel(getCellValueAsString(row.getCell(21)));
                company.setEmail(getCellValueAsString(row.getCell(22)));
                companies.add(company);
            }
            companyInfoRepository.saveAll(companies);
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}