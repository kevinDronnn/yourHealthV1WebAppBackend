package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    List<Calendar> getCalendarsByNameOrderByDate(String name);
}
