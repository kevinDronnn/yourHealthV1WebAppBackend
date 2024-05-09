package com.example.yourhealthv1.mapper;

import com.example.yourhealthv1.dto.CalendarDto;
import com.example.yourhealthv1.entity.Calendar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CalendarMapper {


    CalendarMapper INSTANCE = Mappers.getMapper(CalendarMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "text", source = "text")
    Calendar calendarDtoToCalendarEntity(CalendarDto calendarDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "text", source = "text")
    List<CalendarDto> calendarEntityToCalendarDto(List<Calendar> calendars);
}
