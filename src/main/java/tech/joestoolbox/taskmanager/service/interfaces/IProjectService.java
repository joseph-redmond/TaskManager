package tech.joestoolbox.taskmanager.service.interfaces;

import tech.joestoolbox.taskmanager.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IProjectService {
    Optional<Project> findProjectById(UUID id);
    Page<Project> searchProjectsByTitle(String title, Pageable pageable);
    Optional<Project> createProject(Project project);
    Optional<Project> updateProject(Project project, UUID id);
    Optional<Project> patchProject(Project project, UUID id);
    Boolean deleteProject(UUID id);
}
