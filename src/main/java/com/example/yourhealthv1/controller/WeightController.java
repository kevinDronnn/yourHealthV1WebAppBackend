package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.WeightDto;
import com.example.yourhealthv1.entity.Weight;
import com.example.yourhealthv1.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WeightController {

    @Autowired
    WeightService weightService;

    @GetMapping("/getAllWeights/{name}")
    public ResponseEntity<List<WeightDto>> getAll(@PathVariable String name) {

       try {
           return ResponseEntity.ok(weightService.getAllWeights(name));
       }catch (NoSuchElementException e){
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(List.of(new WeightDto()));
       }
    }

    @PostMapping("/saveWeight")
    public ResponseEntity<String> addWeight(@RequestBody WeightDto weight) {
        try{
            weightService.saveWeight(weight);
            return ResponseEntity.status(HttpStatus.CREATED).body("weight was added");
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
