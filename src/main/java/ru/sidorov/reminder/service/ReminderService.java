package ru.sidorov.reminder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.sidorov.reminder.dto.reminder.PageReminderRequest;
import ru.sidorov.reminder.dto.reminder.ReminderRequest;
import ru.sidorov.reminder.dto.reminder.ReminderResponse;
import ru.sidorov.reminder.entity.Reminder;
import ru.sidorov.reminder.exception.ApplicationException;
import ru.sidorov.reminder.exception.ErrorDiscriptor;
import ru.sidorov.reminder.mapper.ReminderMapper;
import ru.sidorov.reminder.repository.ReminderRepository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    public PageReminderRequest getList(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Reminder> page = reminderRepository.findAll(pageable);

        List<ReminderResponse> reminderResponses = reminderMapper.toResponseList(page.getContent());
        PageReminderRequest pageReminderRequest = new PageReminderRequest();
        pageReminderRequest.setPageNo(page.getNumber());
        pageReminderRequest.setPageSize(page.getSize());
        pageReminderRequest.setLastPage(page.isLast());
        pageReminderRequest.setTotalPages(page.getTotalPages());
        pageReminderRequest.setTotalElements(page.getTotalElements());
        pageReminderRequest.setContent(reminderResponses);

        return pageReminderRequest;
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