package com.joestoolbox.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import com.joestoolbox.taskmanager.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, String> {

    @Query("SELECT r FROM Reminder r ORDER BY r.deadline DESC")
    Page<Reminder> findAllRemindersOrderByDeadline(Pageable pageable);
}
