package com.market.research.ticket.controller;

import com.market.research.ticket.service.ExcelImportService;
import com.market.research.ticket.service.MatchingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/import")
public class ExcelController {

    @Autowired
    private MatchingService matchingService;
    
    @Autowired
    private ExcelImportService excelImportService;

    @PostMapping("/excel")
    public ResponseEntity<String> importExcelFiles() {
        try {
            excelImportService.importAllExcelFiles();
            return ResponseEntity.ok("所有Excel文件已成功导入");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("导入过程中发生错误：" + e.getMessage());
        }
    }

    @PostMapping("/export")
    public ResponseEntity<String> exportMatchingResults(@RequestParam String ticketName) {
        try {
            matchingService.exportMatchingResultsToExcel(ticketName);
            return ResponseEntity.ok("匹配结果已成功导出到 result.xlsx");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("导出过程中发生错误：" + e.getMessage());
        }
    }

}