package ru.sidorov.reminder.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReminderResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime remind;
    private Long userId;
    private LocalDateTime createdAt;
    private Boolean isNotified;
}