package ru.sidorov.reminder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.reminder.dto.reminder.PageReminderResponse;
import ru.sidorov.reminder.dto.reminder.ReminderRequest;
import ru.sidorov.reminder.dto.reminder.ReminderResponse;
import ru.sidorov.reminder.service.ReminderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("domain/api/v1/")
public class ReminderController {

    private final ReminderService reminderService;

    // Контроллер
    @GetMapping("list")
    public PageReminderResponse list(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return reminderService.getList(pageNo, pageSize);
    }

    @GetMapping("sort")
    public PageReminderResponse sort(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection
    ) {
        return reminderService.getSortedList(pageNo, pageSize, sortBy, sortDirection);
    }

    @GetMapping("reminder/{remindId}")
    public ReminderResponse byId(@PathVariable("remindId") Long remindId) {
        return reminderService.getById(remindId);
    }

    @PostMapping("reminder/create")
    public ReminderResponse create(@RequestBody ReminderRequest reminderRequest) {
        return reminderService.create(reminderRequest);
    }

    //нужно возвращать ответ, а не сущность
    @PutMapping("reminder/update/{remindId}")
    public ReminderResponse update(
            @RequestBody ReminderRequest reminderRequest,
            @PathVariable("remindId") Long remindId) {
        return reminderService.update(reminderRequest, remindId);
    }

    @DeleteMapping("reminder/delete/{remindId}")
    public void delete(@PathVariable("remindId") Long remindId) {
        reminderService.delete(remindId);
    }
}
