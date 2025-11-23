package ru.sidorov.reminder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sidorov.reminder.dto.ReminderRequest;
import ru.sidorov.reminder.dto.ReminderResponse;
import ru.sidorov.reminder.entity.Reminder;
import ru.sidorov.reminder.exception.ApplicationException;
import ru.sidorov.reminder.exception.ErrorDiscriptor;
import ru.sidorov.reminder.mapper.ReminderMapper;
import ru.sidorov.reminder.repository.ReminderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    public List<ReminderResponse> getAll() {
        List<Reminder> list = reminderRepository.findAll();
        return reminderMapper.toResponseList(list);
    }

    public ReminderResponse getById(Long reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ApplicationException(ErrorDiscriptor.REMIND_NOT_FOUND));
        return reminderMapper.toResponse(reminder);
    }

    public ReminderResponse create(ReminderRequest reminderRequest) {
        Reminder reminder = reminderMapper.toEntity(reminderRequest);
        Reminder savedReminder = reminderRepository.save(reminder);
        return reminderMapper.toResponse(savedReminder);
    }

    public ReminderResponse update(ReminderRequest reminderRequest, Long reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ApplicationException(ErrorDiscriptor.REMIND_NOT_FOUND));

        Reminder updatedReminder = reminderMapper.toEntity(reminderRequest);
        updatedReminder.setId(reminderId);

        Reminder savedReminder = reminderRepository.save(updatedReminder);
        return reminderMapper.toResponse(savedReminder);
    }

    public void delete(Long reminderId) {
        //можно использовать Optional<>, хз что лучше
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ApplicationException(ErrorDiscriptor.REMIND_NOT_FOUND));
        reminderRepository.delete(reminder);
    }
}