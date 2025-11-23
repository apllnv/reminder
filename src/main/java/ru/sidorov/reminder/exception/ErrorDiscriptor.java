package ru.sidorov.reminder.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorDiscriptor {
    REMIND_NOT_FOUND("Задача не найдена", HttpStatus.NOT_FOUND),


    ROLE_NOT_FOUND("Роль не найдена", HttpStatus.NOT_FOUND),
    ROLE_ALREADY_EXISTS("Роль с таким именем уже существует", HttpStatus.CONFLICT),
    USER_NOT_FOUND("Пользователь не найден", HttpStatus.NOT_FOUND),
    USERNAME_ALREADY_EXISTS("Пользователь с таким именем уже существует", HttpStatus.CONFLICT),
    PASSWORDS_DO_NOT_MATCH("Пароли не совпадают", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED("Доступ запрещен");

    private final String message;
    private final HttpStatus httpStatus;

    ErrorDiscriptor(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    ErrorDiscriptor(String message) {
        this.message = message;
        this.httpStatus = null;
    }

    public String getMessage() {
        return message;
    }
}
