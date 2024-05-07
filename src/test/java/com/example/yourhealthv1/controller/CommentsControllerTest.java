package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.AdviceDto;
import com.example.yourhealthv1.dto.CommentDto;
import com.example.yourhealthv1.service.CommentsService;
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
class CommentsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentsService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSaveComment() throws Exception {
        CommentDto commentDto = new CommentDto("s","sd",1);
        when(service.addComment(any(CommentDto.class))).thenReturn(new CommentDto());

        mockMvc.perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentDto)))
                .andExpect(status().isCreated());

        verify(service, times(1)).addComment(any(CommentDto.class));
    }

    @Test
    public void shouldDeleteComment() throws Exception {
        doNothing().when(service).deleteComment(7);

        mockMvc.perform(delete("/api/comment/{id}",7)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteComment(7);
    }

    @Test
    public void shouldGetComments() throws Exception {
        when(service.getComments()).thenReturn(List.of(new CommentDto()));

        mockMvc.perform(get("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getComments();
    }
}