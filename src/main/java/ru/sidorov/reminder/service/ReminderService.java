package ru.sidorov.reminder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sidorov.reminder.dto.reminder.PageReminderResponse;
import ru.sidorov.reminder.dto.reminder.ReminderRequest;
import ru.sidorov.reminder.dto.reminder.ReminderResponse;
import ru.sidorov.reminder.entity.Reminder;
import ru.sidorov.reminder.exception.ApplicationException;
import ru.sidorov.reminder.exception.ErrorDiscriptor;
import ru.sidorov.reminder.mapper.ReminderMapper;
import ru.sidorov.reminder.repository.ReminderRepository;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    public PageReminderResponse getList(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Reminder> page = reminderRepository.findAll(pageable);
        PageReminderResponse pageReminderResponse = reminderMapper.toPageResponse(page);

        return pageReminderResponse;
    }

    public PageReminderResponse getSortedList(int pageNo, int pageSize, String sortBy, String sortDirection) {
        //или лучше через if else делать без тернарного оператора?
        Sort sort = "desc".equals(sortDirection)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Reminder> page = reminderRepository.findAll(pageable);
        PageReminderResponse pageReminderResponse = reminderMapper.toPageResponse(page);

        return pageReminderResponse;
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