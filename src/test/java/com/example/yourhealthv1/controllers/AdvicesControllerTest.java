package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Advices;
import com.example.yourhealthv1.service.AdvicesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdvicesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdvicesService advicesService;

    @Mock
    private Advices advices;


    @BeforeEach
    void setUp() {
        advices = new Advices();
        advices.setId(22);
        advices.setTitle("idk");
        advices.setDescription("fdfdsf");
    }

    @Test
    void shouldGetAdvices() throws Exception {
        when(advicesService.getAdvices()).thenReturn(List.of(advices));

        this.mockMvc.perform(get("/api/advices"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveAdvices() throws Exception {
        when(advicesService.addAdvices(advices)).thenReturn(advices);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/advice")
                        .content(asJsonString(advices))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteAdvices() throws Exception {
        doNothing().when(advicesService).deleteAdvice(advices.getId());
        this.mockMvc.perform(delete("/api/advices/{id}", advices.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}