package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.Advices;
import com.example.yourhealthv1.repository.AdvicesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AdvicesServiceTest {


    private List<Advices> advicesList = new ArrayList<>();
    @InjectMocks
    private AdvicesService advicesService;

    @Mock
    private AdvicesRepository advicesRepository;

    @Mock
    private Advices advices;

    @BeforeEach
    void setUp() {
        advices = new Advices();
        advices.setId(22);
        advices.setTitle("popa");
        advices.setDescription("fdfdsf");
    }

    @Test
    void shouldGetAdvices() {
        advicesList.add(advices);
        when(advicesRepository.findAll()).thenReturn(advicesList);

        List<Advices> list = advicesService.getAdvices();

        verify(advicesRepository).findAll();
        assertEquals(advicesList.get(0), list.get(0));
    }

    @Test
    void shouldAddAdvice() {
        advicesRepository.save(advices);

        verify(advicesRepository).save(advices);
    }

    @Test
    void shouldDeleteAdvice() {
        advicesRepository.deleteById(advices.getId());

        verify(advicesRepository).deleteById(advices.getId());
    }
}