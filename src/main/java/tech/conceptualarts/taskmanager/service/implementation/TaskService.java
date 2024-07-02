package tech.conceptualarts.taskmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.conceptualarts.logginghelper.adapter.LogAdapter;
import tech.conceptualarts.taskmanager.entity.Task;
import tech.conceptualarts.taskmanager.repository.TaskRepository;
import tech.conceptualarts.taskmanager.service.interfaces.ITaskService;


import static tech.conceptualarts.logginghelper.enums.LogType.*;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final static Logger LOG = LoggerFactory.getLogger(TaskService.class);
    private final static String CLASSNAME = TaskService.class.getName();
    private final TaskRepository taskRepository;
    private final LogAdapter logAdapter;

    @Override
    public Page<Task> findTasksOrderedByDeadlineAndUrgency(Pageable pageable) {
        String methodSignature = "findRemindersOrderedByDeadlineAndUrgency(Pageable)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, ENTER);
        try {
            Page<Task> tasks = taskRepository.findAllTasksOrderedByDeadlineAndUrgency(pageable);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, EXIT);
            return tasks;
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Page.empty();
        }
    }
}
