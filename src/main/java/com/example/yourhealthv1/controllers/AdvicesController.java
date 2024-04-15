package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Advices;
import com.example.yourhealthv1.service.AdvicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdvicesController {

    private static Logger logger = LoggerFactory.getLogger(AdvicesController.class);
    @Autowired
    AdvicesService advicesService;

    @PostMapping("/advice")
    Advices save(@RequestBody Advices newAdvice) {
        logger.info("Advice was added: " + newAdvice.getTitle());
        return advicesService.addAdvices(newAdvice);
    }

    @GetMapping("/advices")
    List<Advices> getAllAdvices() {
        return advicesService.getAdvices();
    }

    @GetMapping("/advices/author/{author}")
    List<Advices> getAllAdvicesOfAuthor(@PathVariable String author) {
        return advicesService.getAdvicesByAuthor(author);
    }

    @DeleteMapping("/advices/{id}")
    public String deleteAdvice(@PathVariable int id) {
        advicesService.deleteAdvice(id);
        logger.info("Advice with id="+id+" was deleted");
        return "advice was deleted, id=" + id;
    }
}
