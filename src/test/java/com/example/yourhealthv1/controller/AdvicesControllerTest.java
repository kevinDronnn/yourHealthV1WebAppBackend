package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.AdviceDto;
import com.example.yourhealthv1.service.AdvicesService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class AdvicesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdvicesService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSaveAdvice() throws Exception {
        AdviceDto adviceDto = new AdviceDto(1,"some","desc","author");
        when(service.addAdvices(any(AdviceDto.class))).thenReturn(adviceDto);


        mockMvc.perform(post("/api/advice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adviceDto)))
                .andExpect(status().isCreated());

        verify(service, times(1)).addAdvices(any(AdviceDto.class));
    }

    @Test
    public void shouldGetAllAdvices() throws Exception {
        when(service.getAdvices()).thenReturn(List.of(new AdviceDto()));


        mockMvc.perform(get("/api/advices")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getAdvices();
    }

    @Test
    public void shouldGetAllAdvicesByAuthor() throws Exception {
        when(service.getAdvicesByAuthor("popa")).thenReturn(List.of(new AdviceDto()));


        mockMvc.perform(get("/api/advices/author/{author}","popa")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getAdvicesByAuthor("popa");
    }

    @Test
    public void shouldDeleteAdvicesById() throws Exception {
        doNothing().when(service).deleteAdvice(7);

        mockMvc.perform(delete("/api/advices/{id}",7)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteAdvice(7);
    }


}