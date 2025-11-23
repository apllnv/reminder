package ru.sidorov.reminder.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String massage;
    private Date timestamp;
}
