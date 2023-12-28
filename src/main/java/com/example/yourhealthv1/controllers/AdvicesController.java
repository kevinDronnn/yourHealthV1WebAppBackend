package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.repository.AdvicesRepository;
import com.example.yourhealthv1.entity.Advices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdvicesController {

    @Autowired
    AdvicesRepository advicesRepository;

    @PostMapping("/advice")
    Advices addAdvice(@RequestBody Advices newAdvice){
        return advicesRepository.save(newAdvice);
    }

    @GetMapping("/advices")
    List<Advices> getAllAdvices(){
        return advicesRepository.findAll();
    }

    @DeleteMapping("/advices/{id}")
    public String deleteAdvice(@PathVariable int id) {
        Advices advice = advicesRepository.getReferenceById(id);
        advicesRepository.delete(advice);
        return "advice was deleted, id=" + id;
    }
}
