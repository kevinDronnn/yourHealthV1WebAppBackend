package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Workouts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutsRepository extends JpaRepository<Workouts, Integer> {
    List<Workouts> getWorkoutsByNameOrderByDate(String name);
}
