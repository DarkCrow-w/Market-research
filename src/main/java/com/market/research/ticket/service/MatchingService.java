package com.market.research.ticket.service;

import com.market.research.ticket.entity.ProjectProcess;
import com.market.research.ticket.repository.ProjectProcessRepository;
import com.market.research.ticket.util.ExcelExportUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private ExcelExportUtil excelExportUtil;

    @Autowired
    private ProjectProcessRepository projectProcessRepository;

    public List<ProjectProcess> findMatchingProjectProcesses(String ticketName) {
        List<ProjectProcess> matchedByFirstLevel = projectProcessRepository.findMatchingByFirstLevel(ticketName);

        if (!matchedByFirstLevel.isEmpty()) {
            return matchedByFirstLevel;
        }

        List<ProjectProcess> matchedByThirdLevel = projectProcessRepository.findMatchingByThirdLevel(ticketName);

        if (!matchedByThirdLevel.isEmpty()) {
            return matchedByThirdLevel;
        }

        return null;
    }

    public void exportMatchingResultsToExcel(String ticketName) throws IOException {
        List<ProjectProcess> matchingProcesses = findMatchingProjectProcesses(ticketName);
        if (matchingProcesses != null && !matchingProcesses.isEmpty()) {
            excelExportUtil.exportToExcel(matchingProcesses, "result.xlsx");
        }
    }
}