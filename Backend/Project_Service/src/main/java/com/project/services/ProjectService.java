package com.project.services;

import com.project.model.Project;
import com.project.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        validateProjectDates(project.getStartDate(), project.getDueDate());
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
    }

    public Page<Project> getAllProjects(int page, int size, String sortBy, String sortDir, String priority) {
        Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        
        if (priority != null) {
            return projectRepository.findByPriority(priority, pageRequest);
        }
        return projectRepository.findAll(pageRequest);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);
        validateProjectDates(projectDetails.getStartDate(), projectDetails.getDueDate());
        
        project.setProjectName(projectDetails.getProjectName());
        project.setDescription(projectDetails.getDescription());
        project.setStartDate(projectDetails.getStartDate());
        project.setDueDate(projectDetails.getDueDate());
        project.setPriority(projectDetails.getPriority());
        project.setRelatedDocuments(projectDetails.getRelatedDocuments());
        
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
    }

    public List<Project> searchProjects(String query) {
        return projectRepository.searchProjects(query);
    }

    public List<Project> getProjectsDueBetween(Date startDate, Date endDate) {
        return projectRepository.findProjectsDueBetween(startDate, endDate);
    }

    private void validateProjectDates(Date startDate, Date dueDate) {
        if (startDate != null && dueDate != null && startDate.after(dueDate)) {
            throw new IllegalArgumentException("Start date must be before due date");
        }
    }
}
