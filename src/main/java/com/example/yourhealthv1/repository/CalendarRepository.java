package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    List<Calendar> getCalendarsByNameOrderByDate(String name);

    @Query("SELECT c FROM Calendar c WHERE c.date = :date AND c.name = :name")
    Calendar getByDateAndName(@Param("date") LocalDate date, @Param("name") String name);
}
