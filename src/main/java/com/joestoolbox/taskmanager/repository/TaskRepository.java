package com.joestoolbox.taskmanager.repository;

import com.joestoolbox.taskmanager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {

    @Query("SELECT t FROM Task t ORDER BY t.deadline ASC, t.urgency DESC")
    Page<Task> findAllTasksOrderedByDeadlineAndUrgency(Pageable pageable);
}
