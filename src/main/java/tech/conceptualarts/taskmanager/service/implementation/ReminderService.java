package tech.conceptualarts.taskmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.conceptualarts.logginghelper.adapter.LogAdapter;
import tech.conceptualarts.logginghelper.enums.LogType;
import tech.conceptualarts.taskmanager.constant.ReminderErrorMessage;
import tech.conceptualarts.taskmanager.entity.Reminder;
import tech.conceptualarts.taskmanager.repository.ReminderRepository;
import tech.conceptualarts.taskmanager.service.interfaces.IReminderService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReminderService implements IReminderService {
    private static final Logger LOG = LoggerFactory.getLogger(ReminderService.class);
    private static final String CLASSNAME = ReminderService.class.getName();
    private final ReminderRepository reminderRepository;
    private final LogAdapter logAdapter;

    @Override
    public Optional<Reminder> findReminderById(UUID id) {
        String methodSignature = "findReminderById(UUID)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Optional<Reminder> maybeFoundReminder = reminderRepository.findById(id.toString());
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return maybeFoundReminder;
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Optional.empty();
        }
    }

    @Override
    public Page<Reminder> findAllRemindersOrderedByDeadline(Pageable pageable) {
        String methodSignature = "findRemindersOrderedByDeadlineAndUrgency(Pageable)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Page<Reminder> orderedReminders = reminderRepository.findAllRemindersOrderByDeadline(pageable);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return orderedReminders;
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Page.empty();
        }
    }

    @Override
    public Optional<Reminder> createReminder(Reminder reminder) {
        String methodSignature = "createReminder(Reminder)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Reminder createdReminder = reminderRepository.save(reminder);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return Optional.of(createdReminder);
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Reminder> updateReminder(Reminder reminder, UUID id) {
        String methodSignature = "updateReminder(Reminder)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Optional<Reminder> maybeFoundReminder = reminderRepository.findById(id.toString());
            if (maybeFoundReminder.isEmpty()) {
                logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.FAILED, ReminderErrorMessage.FAILED_TO_FIND_BY_ID + id);
                return Optional.empty();
            }

            Reminder foundReminder = maybeFoundReminder.get();
            foundReminder.setBody(reminder.getBody());
            foundReminder.setType(reminder.getType());
            foundReminder.setComplete(reminder.getComplete());
            foundReminder.setDeadline(reminder.getDeadline());

            Reminder updatedReminder = reminderRepository.save(foundReminder);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return Optional.of(updatedReminder);
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Reminder> patchReminder(Reminder reminder, UUID id) {
        String methodSignature = "updateReminder(Reminder)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Optional<Reminder> maybeFoundReminder = reminderRepository.findById(id.toString());
            if (maybeFoundReminder.isEmpty()) {
                logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.FAILED, ReminderErrorMessage.FAILED_TO_FIND_BY_ID + id);
                return Optional.empty();
            }

            Reminder foundReminder = maybeFoundReminder.get();

            if (StringUtils.hasText(reminder.getBody())) {
                foundReminder.setBody(reminder.getBody());
            }

            if (StringUtils.hasText(reminder.getType())) {
                foundReminder.setType(reminder.getType());
            }

            if (reminder.getComplete() != null) {
                foundReminder.setComplete(reminder.getComplete());
            }

            if (reminder.getDeadline() != null) {
                foundReminder.setDeadline(reminder.getDeadline());
            }

            Reminder updatedReminder = reminderRepository.save(foundReminder);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return Optional.of(updatedReminder);
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteReminder(UUID id) {
        String methodSignature = "deleteReminder(UUID)";
        logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.ENTER);
        try {
            Optional<Reminder> maybeFoundReminder = reminderRepository.findById(id.toString());
            if (maybeFoundReminder.isEmpty()) {
                logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.FAILED, ReminderErrorMessage.FAILED_TO_FIND_BY_ID + id);
                return false;
            }

            Reminder foundReminder = maybeFoundReminder.get();
            reminderRepository.delete(foundReminder);
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXIT);
            return true;
        } catch (Exception e) {
            logAdapter.logToFile(LOG, CLASSNAME, methodSignature, LogType.EXCEPTION, e.getMessage());
            logAdapter.logToExternal(e);
            return false;
        }
    }
}
