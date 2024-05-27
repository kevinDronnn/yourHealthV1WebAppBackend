package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.CalendarDto;
import com.example.yourhealthv1.service.CalendarService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CalendarController {

    @Autowired
    CalendarService service;

    @PostMapping("/saveNote")
    public ResponseEntity<String> addNote(@RequestBody CalendarDto calendarDto){
        try {
            service.saveNote(calendarDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("note was added");
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/getAllNotes/{name}")
    public ResponseEntity<List<CalendarDto>> getAllNotes(@PathVariable String name){
        try {
            return ResponseEntity.ok(service.getAll(name));
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(List.of(new CalendarDto()));
        }

    }

    @GetMapping("/getNote/{date}/{name}")
    public ResponseEntity<CalendarDto> getNoteByParams(@PathVariable String date, @PathVariable String name){
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return ResponseEntity.ok(service.getNote(parsedDate, name));
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(new CalendarDto());
        }

    }
}
