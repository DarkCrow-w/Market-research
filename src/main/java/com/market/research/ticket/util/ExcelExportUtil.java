package com.market.research.ticket.util;

import com.market.research.ticket.entity.ProjectProcess;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class ExcelExportUtil {

    public void exportToExcel(List<ProjectProcess> processes, String fileName) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("匹配结果");

            // 创建标题行
            Row headerRow = sheet.createRow(0);
            String[] columns = {"First Level", "Sec Level", "Business", "Start Time", "Progress", "Financing Share", "Financier", "Business Unit", "LP"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // 填充数据
            int rowNum = 1;
            for (ProjectProcess process : processes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(process.getFirstLevel());
                row.createCell(1).setCellValue(process.getSecLevel());
                row.createCell(2).setCellValue(process.getBusiness());
                row.createCell(3).setCellValue(process.getStartTime());
                row.createCell(4).setCellValue(process.getProgress());
                row.createCell(5).setCellValue(process.getFinancingShare());
                row.createCell(6).setCellValue(process.getFinancier());
                row.createCell(7).setCellValue(process.getBusinessUnit());
                row.createCell(8).setCellValue(process.getLp());
            }

            // 自动调整列宽
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 确保目录存在
            Path dirPath = Paths.get("src", "main", "resources", "excel");
            Files.createDirectories(dirPath);

            // 保存文件
            Path filePath = dirPath.resolve(fileName);
            try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                workbook.write(outputStream);
            }
        }
    }
}