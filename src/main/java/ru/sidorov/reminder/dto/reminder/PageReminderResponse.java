package ru.sidorov.reminder.dto.reminder;

import lombok.Data;

import java.util.List;

@Data
public class PageReminderResponse {
    private List<ReminderResponse> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;
}
