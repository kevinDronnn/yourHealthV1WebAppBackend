package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.WeightDto;
import com.example.yourhealthv1.dto.WorkoutDto;
import com.example.yourhealthv1.service.WeightService;
import com.example.yourhealthv1.service.WorkoutsService;
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
class WorkoutsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkoutsService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldAddWorkout() throws Exception{
        WorkoutDto workoutDto = new WorkoutDto("popa", new Date() , 55);
        doNothing().when(service).saveWorkouts(any(WorkoutDto.class));

        mockMvc.perform(post("/api/saveWorkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(workoutDto)))
                .andExpect(status().isCreated());

        verify(service, times(1)).saveWorkouts(any(WorkoutDto.class));
    }

    @Test
    public void shouldGetAllWeightsByAuthor() throws Exception{
        when(service.getWorkouts("name")).thenReturn(List.of(new WorkoutDto()));

        mockMvc.perform(get("/api/getAllWorkouts/{name}","name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getWorkouts("name");
    }

}