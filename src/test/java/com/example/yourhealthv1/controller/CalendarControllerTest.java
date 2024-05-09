package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.CalendarDto;
import com.example.yourhealthv1.dto.WorkoutDto;
import com.example.yourhealthv1.service.CalendarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class CalendarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalendarService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldAddNote() throws Exception{
        CalendarDto calendarDto = new CalendarDto("name",new Date(), "some text");
        doNothing().when(service).saveNote(any(CalendarDto.class));

        mockMvc.perform(post("/api/saveNote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calendarDto)))
                .andExpect(status().isCreated());

        verify(service, times(1)).saveNote(any(CalendarDto.class));
    }

    @Test
    public void shouldGetAllNotesByAuthor() throws Exception{
        when(service.getAll("name")).thenReturn(List.of(new CalendarDto()));

        mockMvc.perform(get("/api/getNotes/{name}","name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getAll("name");
    }
}