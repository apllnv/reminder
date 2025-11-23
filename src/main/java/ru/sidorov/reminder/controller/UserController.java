package ru.sidorov.reminder.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.reminder.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("domain/api/v1/")
public class UserController {
    private final UserService userService;
}
