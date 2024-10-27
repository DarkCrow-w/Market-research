package com.market.research.ticket.controller;

import com.market.research.ticket.entity.CompanyInfo;
import com.market.research.ticket.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/company-infos")
@Tag(name = "公司信息", description = "公司信息管理API")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @GetMapping
    @Operation(summary = "获取所有公司信息", description = "返回所有公司信息的列表")
    @ApiResponse(responseCode = "200", description = "成功获取公司信息列表", content = @Content(schema = @Schema(implementation = CompanyInfo.class)))
    public ResponseEntity<List<CompanyInfo>> getAllCompanyInfos() {
        List<CompanyInfo> companyInfos = companyInfoService.getAllCompanyInfos();
        return new ResponseEntity<>(companyInfos, HttpStatus.OK);
    }

    @GetMapping("/{ticketCode}")
    @Operation(summary = "通过票据代码获取公司信息", description = "根据提供的票据代码返回特定的公司信息")
    @ApiResponse(responseCode = "200", description = "成功获取公司信息", content = @Content(schema = @Schema(implementation = CompanyInfo.class)))
    @ApiResponse(responseCode = "404", description = "公司信息未找到")
    public ResponseEntity<CompanyInfo> getCompanyInfoByTicketCode(
            @Parameter(description = "票据代码") @PathVariable String ticketCode) {
        return companyInfoService.getCompanyInfoByTicketCode(ticketCode)
                .map(companyInfo -> new ResponseEntity<>(companyInfo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "创建新的公司信息", description = "创建一个新的公司信息")
    @ApiResponse(responseCode = "201", description = "公司信息创建成功", content = @Content(schema = @Schema(implementation = CompanyInfo.class)))
    public ResponseEntity<CompanyInfo> createCompanyInfo(
            @Parameter(description = "公司信息详情") @RequestBody CompanyInfo companyInfo) {
        CompanyInfo savedCompanyInfo = companyInfoService.saveCompanyInfo(companyInfo);
        return new ResponseEntity<>(savedCompanyInfo, HttpStatus.CREATED);
    }

    @PostMapping("/update/{ticketCode}")
    @Operation(summary = "更新公司信息", description = "根据票据代码更新现有的公司信息")
    @ApiResponse(responseCode = "200", description = "公司信息更新成功", content = @Content(schema = @Schema(implementation = CompanyInfo.class)))
    @ApiResponse(responseCode = "404", description = "公司信息未找到")
    public ResponseEntity<CompanyInfo> updateCompanyInfo(
            @Parameter(description = "票据代码") @PathVariable String ticketCode,
            @Parameter(description = "更新的公司信息详情") @RequestBody CompanyInfo companyInfo) {
        if (!companyInfoService.existsByTicketCode(ticketCode)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CompanyInfo updatedCompanyInfo = companyInfoService.updateCompanyInfo(ticketCode, companyInfo);
        return new ResponseEntity<>(updatedCompanyInfo, HttpStatus.OK);
    }

    @PostMapping("/delete/{ticketCode}")
    @Operation(summary = "删除公司信息", description = "根据票据代码删除公司信息")
    @ApiResponse(responseCode = "204", description = "公司信息删除成功")
    @ApiResponse(responseCode = "404", description = "公司信息未找到")
    public ResponseEntity<Void> deleteCompanyInfo(
            @Parameter(description = "票据代码") @PathVariable String ticketCode) {
        if (!companyInfoService.existsByTicketCode(ticketCode)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyInfoService.deleteCompanyInfo(ticketCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}