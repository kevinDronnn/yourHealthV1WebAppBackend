package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.WeightDto;
import com.example.yourhealthv1.service.WeightService;
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
class WeightControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeightService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldAddWeight() throws Exception{
        WeightDto weightDto = new WeightDto("popa", new Date() , 55.0f);
        doNothing().when(service).saveWeight(any(WeightDto.class));

        mockMvc.perform(post("/api/saveWeight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(weightDto)))
                .andExpect(status().isCreated());

        verify(service, times(1)).saveWeight(any(WeightDto.class));
    }

    @Test
    public void shouldGetAllWeightsByAuthor() throws Exception{
        when(service.getAllWeights("name")).thenReturn(List.of(new WeightDto()));

        mockMvc.perform(get("/api/getAllWeights/{name}","name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getAllWeights("name");
    }
}