package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.Advices;
import com.example.yourhealthv1.repository.AdvicesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvicesService {
    @Autowired
    AdvicesRepository advicesRepository;

    public List<Advices> getAdvices() {
        return advicesRepository.findAll();
    }
    public List<Advices> getAdvicesByAuthor(String name) {
        return advicesRepository.getAdvicesByAuthorName(name);
    }
    @Transactional
    public Advices addAdvices(Advices advices) {
        return advicesRepository.save(advices);
    }
    @Transactional
    public void deleteAdvice(int id) {
        advicesRepository.deleteById(id);
    }


}
