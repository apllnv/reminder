package ru.sidorov.reminder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ru.sidorov.reminder.dto.reminder.PageReminderResponse;
import ru.sidorov.reminder.dto.reminder.ReminderRequest;
import ru.sidorov.reminder.dto.reminder.ReminderResponse;
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

    default PageReminderResponse toPageResponse(Page<Reminder> page) {
        if (page == null) {
            return null;
        }

        PageReminderResponse response = new PageReminderResponse();
        response.setContent(toResponseList(page.getContent()));
        response.setPageNo(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }
}