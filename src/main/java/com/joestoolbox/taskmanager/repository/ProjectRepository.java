package com.joestoolbox.taskmanager.repository;

import com.joestoolbox.taskmanager.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
    @Query("SELECT p FROM Project p WHERE p.title LIKE :title%")
    Page<Project> searchProjectsByTitle(String title, Pageable pageable);
}