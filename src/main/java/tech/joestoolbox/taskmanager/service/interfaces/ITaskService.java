package tech.joestoolbox.taskmanager.service.interfaces;

import tech.joestoolbox.taskmanager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ITaskService {
    Page<Task> findTasksOrderedByDeadlineAndUrgency(Pageable pageable);
}
