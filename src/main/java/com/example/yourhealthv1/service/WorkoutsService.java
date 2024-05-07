package com.example.yourhealthv1.service;

import com.example.yourhealthv1.controller.AdvicesController;
import com.example.yourhealthv1.dto.WorkoutDto;
import com.example.yourhealthv1.mapper.WorkoutMapper;
import com.example.yourhealthv1.repository.WorkoutsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WorkoutsService {

    private static Logger logger = LoggerFactory.getLogger(AdvicesController.class);
    @Autowired
    private WorkoutsRepository workoutsRepository;

    public List<WorkoutDto> getWorkouts(String name){
        Optional<List<WorkoutDto>> list =
                Optional.ofNullable(WorkoutMapper.INSTANCE.
                        workoutsEntityToWorkoutDto(workoutsRepository.getWorkoutsByNameOrderByDate(name)));
        if (list.isPresent()){
            return list.get();
        }else {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public void saveWorkouts(WorkoutDto workouts){
        Optional<WorkoutDto> workoutsOptional = Optional.ofNullable(workouts);
        if (workoutsOptional.isPresent()){
            workoutsRepository.save(WorkoutMapper.INSTANCE.workoutDtoToWorkoutsEntity(workoutsOptional.get()));
            logger.info("workout was added");
        }else {
            throw new NullPointerException();
        }
    }
}
