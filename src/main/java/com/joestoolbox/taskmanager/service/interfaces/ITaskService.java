package com.joestoolbox.taskmanager.service.interfaces;

import com.joestoolbox.taskmanager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ITaskService {
    Page<Task> findTasksOrderedByDeadlineAndUrgency(Pageable pageable);
}
