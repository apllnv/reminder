package ru.sidorov.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ReminderApplication {

    public static void main(String[] args) {

        SpringApplication.run(ReminderApplication.class, args);
    }
}