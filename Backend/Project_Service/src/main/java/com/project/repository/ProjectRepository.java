package com.project.repository;

import com.project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByPriority(String priority, Pageable pageable);
    
    @Query("SELECT p FROM Project p WHERE p.dueDate BETWEEN :startDate AND :endDate")
    List<Project> findProjectsDueBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    @Query("SELECT p FROM Project p WHERE " +
           "LOWER(p.projectName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Project> searchProjects(@Param("query") String query);
}