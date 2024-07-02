package tech.conceptualarts.taskmanager.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.conceptualarts.taskmanager.entity.Task;


public interface ITaskService {
    Page<Task> findTasksOrderedByDeadlineAndUrgency(Pageable pageable);
}
