package com.market.research.ticket.service;

import com.market.research.ticket.util.ExcelImportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ExcelImportService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelImportService.class);

    @Autowired
    private ExcelImportUtil excelImportUtil;

    public void importAllExcelFiles() throws IOException {
        Path resourcePath = Paths.get("src", "main", "resources", "excel");

        String industrialPartyFilePath = resourcePath.resolve("industrial_party.xlsx").toString();
        String projectProcessFilePath = resourcePath.resolve("project_process.xlsx").toString();
        String companyInfoFilePath = resourcePath.resolve("company_info.xlsx").toString();

        logger.info("开始导入Excel文件");
        excelImportUtil.importExcelData(industrialPartyFilePath, projectProcessFilePath, companyInfoFilePath);
        logger.info("Excel文件导入完成");
    }
}