package tech.joestoolbox.taskmanager.service.implementation;

import tech.joestoolbox.taskmanager.constant.ProjectErrorMessage;
import tech.joestoolbox.taskmanager.entity.Project;
import tech.joestoolbox.taskmanager.repository.ProjectRepository;
import tech.joestoolbox.taskmanager.service.interfaces.IProjectService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.joestoolbox.logginghelper.adapter.LogAdapter;
import tech.joestoolbox.logginghelper.enums.LogType;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectService.class);
    private static final String CLASSNAME = ProjectService.class.getName();
    private final ProjectRepository projectRepository;
    private final LogAdapter logAdapter;

    @Override
    public Optional<Project> findProjectById(UUID id) {
        String methodSignature = "findProjectById(UUID)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Optional<Project> maybeProject = projectRepository.findById(id.toString());
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return maybeProject;
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Optional.empty();
        }
    }

    @Override
    public Page<Project> searchProjectsByTitle(String title, Pageable pageable) {
        String methodSignature = "searchProjectsByTitle(String, Pageable)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Page<Project> foundProjects = projectRepository.searchProjectsByTitle(title, pageable);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return foundProjects;
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Page.empty();
        }
    }

    @Override
    public Optional<Project> createProject(Project project) {
        String methodSignature = "createProject(Project)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Project createdProject = projectRepository.save(project);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return Optional.of(createdProject);
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Project> updateProject(Project project, UUID id) {
        String methodSignature = "updateProject(Project, UUID)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Optional<Project> maybeFoundProject = projectRepository.findById(id.toString());
            if (maybeFoundProject.isEmpty()) {
                logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.FAILED, ProjectErrorMessage.FAILED_TO_FIND_BY_ID + id);
                return Optional.empty();
            }

            Project foundProject = maybeFoundProject.get();
            foundProject.setTitle(project.getTitle());
            foundProject.setDescription(project.getDescription());
            Project updatedProject = projectRepository.save(foundProject);

            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return Optional.of(updatedProject);
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Project> patchProject(Project project, UUID id) {
        String methodSignature = "patchProject(Project, UUID)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Optional<Project> maybeFoundProject = projectRepository.findById(id.toString());
            if (maybeFoundProject.isEmpty()) {
                logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.FAILED, ProjectErrorMessage.FAILED_TO_FIND_BY_ID + id);
                return Optional.empty();
            }

            Project foundProject = maybeFoundProject.get();

            if (StringUtils.hasText(project.getTitle())) {
                foundProject.setTitle(project.getTitle());
            }

            if (StringUtils.hasText(project.getDescription())) {
                foundProject.setDescription(project.getDescription());
            }

            Project patchedProject = projectRepository.save(foundProject);

            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return Optional.of(patchedProject);
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteProject(UUID id) {
        String methodSignature = "deleteProject(UUID)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Optional<Project> maybeFoundProject = projectRepository.findById(id.toString());
            if (maybeFoundProject.isEmpty()) {
                logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.FAILED, ProjectErrorMessage.FAILED_TO_FIND_BY_ID + id);
                return false;
            }

            Project foundProject = maybeFoundProject.get();
            projectRepository.delete(foundProject);

            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return true;
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return false;
        }
    }
}
