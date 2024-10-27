package com.market.research.ticket.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.research.ticket.entity.ProjectProcess;
import com.market.research.ticket.repository.ProjectProcessRepository;
import com.market.research.ticket.service.ProjectProcessService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProjectProcessServiceImpl implements ProjectProcessService {

    @Autowired
    private ProjectProcessRepository projectProcessRepository;

    @Override
    public List<ProjectProcess> getAllProjectProcesses() {
        return projectProcessRepository.findAll();
    }

    @Override
    public Optional<ProjectProcess> getProjectProcessByProjectName(String projectName) {
        return projectProcessRepository.findByProjectName(projectName);
    }

    @Override
    public ProjectProcess saveProjectProcess(ProjectProcess projectProcess) {
        return projectProcessRepository.save(projectProcess);
    }

    @Override
    @Transactional
    public ProjectProcess updateProjectProcess(String projectName, ProjectProcess updates) {
        ProjectProcess existingProject = projectProcessRepository.findByProjectName(projectName)
            .orElseThrow(() -> new EntityNotFoundException("未找到名称为 " + projectName + " 的项目"));

        if (updates.getProjectProcessId() != null) {
            existingProject.setProjectProcessId(updates.getProjectProcessId());
        }
        if (updates.getProjectName() != null) {
            existingProject.setProjectName(updates.getProjectName());
        }
        if (updates.getFirstLevel() != null) {
            existingProject.setFirstLevel(updates.getFirstLevel());
        }
        if (updates.getSecLevel() != null) {
            existingProject.setSecLevel(updates.getSecLevel());
        }
        if (updates.getBusiness() != null) {
            existingProject.setBusiness(updates.getBusiness());
        }
        if (updates.getStartTime() != null) {
            existingProject.setStartTime(updates.getStartTime());
        }
        if (updates.getProgress() != null) {
            existingProject.setProgress(updates.getProgress());
        }
        if (updates.getFinancingShare() != null) {
            existingProject.setFinancingShare(updates.getFinancingShare());
        }
        if (updates.getFinancier() != null) {
            existingProject.setFinancier(updates.getFinancier());
        }
        if (updates.getBusinessUnit() != null) {
            existingProject.setBusinessUnit(updates.getBusinessUnit());
        }
        if (updates.getLp() != null) {
            existingProject.setLp(updates.getLp());
        }

        return projectProcessRepository.save(existingProject);
    }

    @Override
    public void deleteProjectProcess(String projectName) {
        projectProcessRepository.deleteByProjectName(projectName);
    }

    @Override
    public boolean existsByProjectName(String projectName) {
        return projectProcessRepository.existsByProjectName(projectName);
    }

}