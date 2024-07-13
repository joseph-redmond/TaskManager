package com.joestoolbox.taskmanager.adapter;

import com.joestoolbox.taskmanager.entity.Task;
import com.joestoolbox.taskmanager.service.implementation.TaskService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tech.conceptualarts.logginghelper.adapter.LogAdapter;

import static tech.conceptualarts.logginghelper.enums.LogType.*;

@Component
@RequiredArgsConstructor
public class TaskAdapter {
    private final Logger LOG = LoggerFactory.getLogger(TaskAdapter.class);
    private final String CLASSNAME = TaskAdapter.class.getName();
    private final LogAdapter logAdapter;
    private final TaskService taskService;

    public Page<Task> findTasksOrderedByDeadlineAndUrgency(Pageable pageable) {
        String methodSignature = "findTasksOrderedByDeadlineAndUrgency(Pageable)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, ENTER);
        try {
            Page<Task> pageOfTasks = taskService.findTasksOrderedByDeadlineAndUrgency(pageable);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, EXIT);
            return pageOfTasks;
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Page.empty();
        }
    }

}
