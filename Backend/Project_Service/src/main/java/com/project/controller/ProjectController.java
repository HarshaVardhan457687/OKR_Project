package com.project.controller;

import com.project.model.Project;
import com.project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @PostMapping("/new")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Project>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "projectId") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String priority) {
        return ResponseEntity.ok(projectService.getAllProjects(page, size, sortBy, sortDir, priority));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(@RequestParam String query) {
        return ResponseEntity.ok(projectService.searchProjects(query));
    }

    @GetMapping("/due-between")
    public ResponseEntity<List<Project>> getProjectsDueBetween(
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        return ResponseEntity.ok(projectService.getProjectsDueBetween(startDate, endDate));
    }
}


