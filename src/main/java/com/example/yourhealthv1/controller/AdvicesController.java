package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.AdviceDto;
import com.example.yourhealthv1.entity.Advices;
import com.example.yourhealthv1.service.AdvicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdvicesController {

    private static Logger logger = LoggerFactory.getLogger(AdvicesController.class);
    @Autowired
    AdvicesService advicesService;

    @PostMapping("/advice")
    ResponseEntity<AdviceDto> save(@RequestBody AdviceDto newAdvice) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(advicesService.addAdvices(newAdvice));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(newAdvice);
        }
    }

    @GetMapping("/advices")
    ResponseEntity<List<AdviceDto>> getAllAdvices() {
        return ResponseEntity.ok(advicesService.getAdvices());
    }

    @GetMapping("/advices/author/{author}")
    ResponseEntity<List<AdviceDto>> getAllAdvicesOfAuthor(@PathVariable String author) {
        try{
            return ResponseEntity.ok().body(advicesService.getAdvicesByAuthor(author));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(List.of(new AdviceDto()));
        }

    }

    @DeleteMapping("/advices/{id}")
    public ResponseEntity<String> deleteAdvice(@PathVariable int id) {
        advicesService.deleteAdvice(id);
        return ResponseEntity.ok().body("Advice with id="+id+" was deleted");
    }
}
