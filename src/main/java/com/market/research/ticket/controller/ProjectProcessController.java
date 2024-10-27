package com.market.research.ticket.controller;

import com.market.research.ticket.entity.ProjectProcess;
import com.market.research.ticket.service.ProjectProcessService;
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
@RequestMapping("/api/project-processes")
@Tag(name = "项目进程", description = "项目进程管理API")
public class ProjectProcessController {

    @Autowired
    private ProjectProcessService projectProcessService;

    @GetMapping
    @Operation(summary = "获取所有项目进程", description = "返回所有项目进程的列表")
    @ApiResponse(responseCode = "200", description = "成功获取项目进程列表", 
                 content = @Content(schema = @Schema(implementation = ProjectProcess.class)))
    public ResponseEntity<List<ProjectProcess>> getAllProjectProcesses() {
        List<ProjectProcess> projectProcesses = projectProcessService.getAllProjectProcesses();
        return new ResponseEntity<>(projectProcesses, HttpStatus.OK);
    }

    @GetMapping("/{projectName}")
    @Operation(summary = "通过项目名称获取项目进程", description = "根据提供的项目名称返回特定的项目进程")
    @ApiResponse(responseCode = "200", description = "成功获取项目进程", 
                 content = @Content(schema = @Schema(implementation = ProjectProcess.class)))
    @ApiResponse(responseCode = "404", description = "项目进程未找到")
    public ResponseEntity<ProjectProcess> getProjectProcessByProjectName(
            @Parameter(description = "项目名称") @PathVariable String projectName) {
        return projectProcessService.getProjectProcessByProjectName(projectName)
                .map(projectProcess -> new ResponseEntity<>(projectProcess, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "创建新的项目进程", description = "创建一个新的项目进程")
    @ApiResponse(responseCode = "201", description = "项目进程创建成功", 
                 content = @Content(schema = @Schema(implementation = ProjectProcess.class)))
    public ResponseEntity<ProjectProcess> createProjectProcess(
            @Parameter(description = "项目进程详情") @RequestBody ProjectProcess projectProcess) {
        ProjectProcess savedProjectProcess = projectProcessService.saveProjectProcess(projectProcess);
        return new ResponseEntity<>(savedProjectProcess, HttpStatus.CREATED);
    }

    @PostMapping("/update/{projectName}")
    @Operation(summary = "更新项目进程", description = "根据项目名称更新现有的项目进程")
    @ApiResponse(responseCode = "200", description = "项目进程更新成功", 
                 content = @Content(schema = @Schema(implementation = ProjectProcess.class)))
    @ApiResponse(responseCode = "404", description = "项目进程未找到")
    public ResponseEntity<ProjectProcess> updateProjectProcess(
            @Parameter(description = "项目名称") @PathVariable String projectName,
            @Parameter(description = "更新的项目进程详情") @RequestBody ProjectProcess projectProcess) {
        if (!projectProcessService.existsByProjectName(projectName)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProjectProcess updatedProjectProcess = projectProcessService.updateProjectProcess(projectName, projectProcess);
        return new ResponseEntity<>(updatedProjectProcess, HttpStatus.OK);
    }

    @PostMapping("/delete/{projectName}")
    @Operation(summary = "删除项目进程", description = "根据项目名称删除项目进程")
    @ApiResponse(responseCode = "204", description = "项目进程删除成功")
    @ApiResponse(responseCode = "404", description = "项目进程未找到")
    public ResponseEntity<Void> deleteProjectProcess(
            @Parameter(description = "项目名称") @PathVariable String projectName) {
        if (!projectProcessService.existsByProjectName(projectName)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        projectProcessService.deleteProjectProcess(projectName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}