package com.market.research.ticket.controller;

import com.market.research.ticket.entity.ProjectProcess;
import com.market.research.ticket.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matching")
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    @GetMapping("/project-processes")
    public ResponseEntity<List<ProjectProcess>> getMatchingProjectProcesses(@RequestParam String ticketName) {
        List<ProjectProcess> matchingProcesses = matchingService.findMatchingProjectProcesses(ticketName);
        if (matchingProcesses == null || matchingProcesses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matchingProcesses);
    }
    
}