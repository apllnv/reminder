package ru.sidorov.reminder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sidorov.reminder.dto.ReminderRequest;
import ru.sidorov.reminder.dto.ReminderResponse;
import ru.sidorov.reminder.entity.Reminder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    // Request -> Entity (убрали ignore для полей со значениями по умолчанию)
    @Mapping(target = "id", ignore = true)
    Reminder toEntity(ReminderRequest request);

    // Entity -> Response
    ReminderResponse toResponse(Reminder entity);

    // Для списков
    List<ReminderResponse> toResponseList(List<Reminder> entities);
}