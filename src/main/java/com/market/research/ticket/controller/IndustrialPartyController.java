package com.market.research.ticket.controller;

import com.market.research.ticket.entity.IndustrialParty;
import com.market.research.ticket.service.IndustrialPartyService;
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
@RequestMapping("/api/industrial-parties")
@Tag(name = "产业方", description = "产业方管理API")
public class IndustrialPartyController {

    @Autowired
    private IndustrialPartyService industrialPartyService;

    @GetMapping
    @Operation(summary = "获取所有产业方", description = "返回所有产业方的列表")
    @ApiResponse(responseCode = "200", description = "成功获取产业方列表", 
                 content = @Content(schema = @Schema(implementation = IndustrialParty.class)))
    public ResponseEntity<List<IndustrialParty>> getAllIndustrialParties() {
        List<IndustrialParty> industrialParties = industrialPartyService.getAllIndustrialParties();
        return new ResponseEntity<>(industrialParties, HttpStatus.OK);
    }

    @GetMapping("/{ticketName}")
    @Operation(summary = "通过票据名称获取产业方", description = "根据提供的票据名称返回特定的产业方")
    @ApiResponse(responseCode = "200", description = "成功获取产业方", 
                 content = @Content(schema = @Schema(implementation = IndustrialParty.class)))
    @ApiResponse(responseCode = "404", description = "产业方未找到")
    public ResponseEntity<IndustrialParty> getIndustrialPartyByTicketName(
            @Parameter(description = "票据名称") @PathVariable String ticketName) {
        return industrialPartyService.findByName(ticketName)
                .map(industrialParty -> new ResponseEntity<>(industrialParty, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "创建新的产业方", description = "创建一个新的产业方")
    @ApiResponse(responseCode = "201", description = "产业方创建成功", 
                 content = @Content(schema = @Schema(implementation = IndustrialParty.class)))
    public ResponseEntity<IndustrialParty> createIndustrialParty(
            @Parameter(description = "产业方详情") @RequestBody IndustrialParty industrialParty) {
        IndustrialParty savedIndustrialParty = industrialPartyService.saveIndustrialParty(industrialParty);
        return new ResponseEntity<>(savedIndustrialParty, HttpStatus.CREATED);
    }

    @PostMapping("/update/{ticketName}")
    @Operation(summary = "更新产业方", description = "根据票据名称更新现有的产业方")
    @ApiResponse(responseCode = "200", description = "产业方更新成功", 
                 content = @Content(schema = @Schema(implementation = IndustrialParty.class)))
    @ApiResponse(responseCode = "404", description = "产业方未找到")
    public ResponseEntity<IndustrialParty> updateIndustrialParty(
            @Parameter(description = "票据名称") @PathVariable String ticketName,
            @Parameter(description = "更新的产业方详情") @RequestBody IndustrialParty industrialParty) {
        if (!industrialPartyService.existsByTicketName(ticketName)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        IndustrialParty updatedIndustrialParty = industrialPartyService.updateByTicketName(ticketName, industrialParty);
        return new ResponseEntity<>(updatedIndustrialParty, HttpStatus.OK);
    }

    @PostMapping("/delete/{ticketName}")
    @Operation(summary = "删除产业方", description = "根据票据名称删除产业方")
    @ApiResponse(responseCode = "204", description = "产业方删除成功")
    @ApiResponse(responseCode = "404", description = "产业方未找到")
    public ResponseEntity<Void> deleteIndustrialParty(
            @Parameter(description = "票据名称") @PathVariable String ticketName) {
        if (!industrialPartyService.existsByTicketName(ticketName)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        industrialPartyService.deleteByTicketName(ticketName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}