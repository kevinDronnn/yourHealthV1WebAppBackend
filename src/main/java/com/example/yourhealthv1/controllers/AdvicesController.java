package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Advices;
import com.example.yourhealthv1.service.AdvicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdvicesController {

    @Autowired
    AdvicesService advicesService;

    @PostMapping("/advice")
    Advices save(@RequestBody Advices newAdvice) {
        return advicesService.addAdvices(newAdvice);
    }

    @GetMapping("/advices")
    List<Advices> getAllAdvices() {
        return advicesService.getAdvices();
    }

    @DeleteMapping("/advices/{id}")
    public String deleteAdvice(@PathVariable int id) {
        advicesService.deleteAdvice(id);
        return "advice was deleted, id=" + id;
    }
}
