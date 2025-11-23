package ru.sidorov.reminder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.reminder.dto.ReminderRequest;
import ru.sidorov.reminder.dto.ReminderResponse;
import ru.sidorov.reminder.service.ReminderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("domain/api/v1/")
public class RemindController {

    private final ReminderService reminderService;

    @GetMapping("list")
    public List<ReminderResponse> list() {
        return reminderService.getAll();
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
    @PutMapping ("reminder/update/{remindId}")
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
