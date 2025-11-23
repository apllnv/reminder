package ru.sidorov.reminder.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReminderRequest {
    private String title;
    private String description;
    private LocalDateTime remind;
    private Long userId;
}
