package com.market.research.ticket.service;

import java.util.List;
import java.util.Optional;

import com.market.research.ticket.entity.ProjectProcess;

public interface ProjectProcessService {

    List<ProjectProcess> getAllProjectProcesses();
    
    Optional<ProjectProcess> getProjectProcessByProjectName(String projectName);
    
    ProjectProcess saveProjectProcess(ProjectProcess projectProcess);
    
    ProjectProcess updateProjectProcess(String projectName, ProjectProcess projectProcess);
    
    void deleteProjectProcess(String projectName);
    
    boolean existsByProjectName(String projectName);
    
}