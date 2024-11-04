package com.market.research.ticket.controller;

import com.market.research.ticket.entity.ProjectProcess;
import com.market.research.ticket.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/api/matching")
@Tag(name = "匹配控制器", description = "用于处理项目流程匹配相关的接口")
@Slf4j
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    @Operation(summary = "获取匹配的项目流程",
            description = "根据票据名称查找匹配的项目流程列表")
    @Parameter(name = "ticketName", description = "票据名称", required = true)
    @GetMapping(value = "/project-processes", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<ProjectProcess>> getMatchingProjectProcesses(
            @RequestParam(value = "ticketName") String ticketName) {
        // 添加日志便于调试
        log.info("接收到的票据名称: {}", ticketName);
        
        List<ProjectProcess> matchingProcesses = matchingService.findMatchingProjectProcesses(ticketName);
        if (matchingProcesses == null || matchingProcesses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matchingProcesses);
    }
}