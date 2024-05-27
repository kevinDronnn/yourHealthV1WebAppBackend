package com.example.yourhealthv1.service;

import com.example.yourhealthv1.dto.CalendarDto;
import com.example.yourhealthv1.entity.Calendar;
import com.example.yourhealthv1.mapper.CalendarMapper;
import com.example.yourhealthv1.repository.CalendarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalendarService {
    private static Logger logger = LoggerFactory.getLogger(AdvicesService.class);

    @Autowired
    CalendarRepository repository;

    public void saveNote(CalendarDto calendarDto){
        if (calendarDto!=null){
            repository.save(CalendarMapper.INSTANCE.calendarDtoToCalendarEntity(calendarDto));
            logger.info("note was saved");
        }else {
            throw new NullPointerException();
        }
    }

    public List<CalendarDto> getAll(String name){
        List<Calendar> list = repository.getCalendarsByNameOrderByDate(name);
        if (list!=null){
            return CalendarMapper.INSTANCE.calendarEntityToCalendarDto(list);
        }else {
            throw new EntityNotFoundException();
        }

    }

    public CalendarDto getNote(LocalDate date, String name){
        Calendar calendar = repository.getByDateAndName(date,name);
        if (calendar!=null){
            return CalendarMapper.INSTANCE.calendarEntityToCalendarDto(calendar);
        }else {
            throw new EntityNotFoundException();
        }

    }


}
