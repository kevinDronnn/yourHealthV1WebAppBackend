package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.WorkoutDto;
import com.example.yourhealthv1.entity.Workouts;
import com.example.yourhealthv1.service.WorkoutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class WorkoutsController {

    @Autowired
    WorkoutsService workoutsService;

    @GetMapping("/getAllWorkouts/{name}")
    public ResponseEntity<List<WorkoutDto>> getAll(@PathVariable String name) {
        try {
            return ResponseEntity.ok(workoutsService.getWorkouts(name));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(new WorkoutDto()));
        }
    }

    @PostMapping("/saveWorkout")
    public ResponseEntity<String> saveWorkout(@RequestBody WorkoutDto workouts) {
        try {
            workoutsService.saveWorkouts(workouts);
            return ResponseEntity.status(HttpStatus.CREATED).body("workout was added");
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
