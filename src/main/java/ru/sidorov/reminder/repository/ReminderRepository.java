package ru.sidorov.reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sidorov.reminder.entity.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
