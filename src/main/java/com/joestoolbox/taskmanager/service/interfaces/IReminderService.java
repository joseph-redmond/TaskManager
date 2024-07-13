package com.joestoolbox.taskmanager.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.joestoolbox.taskmanager.entity.Reminder;

import java.util.Optional;
import java.util.UUID;

public interface IReminderService {

    Optional<Reminder> findReminderById(UUID id);
    Page<Reminder> findAllRemindersOrderedByDeadline(Pageable pageable);
    Optional<Reminder> createReminder(Reminder reminder);
    Optional<Reminder> updateReminder(Reminder reminder, UUID id);
    Optional<Reminder> patchReminder(Reminder reminder, UUID id);
    Boolean deleteReminder(UUID id);
}
