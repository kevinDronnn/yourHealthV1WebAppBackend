package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightRepository extends JpaRepository<Weight, Integer> {
    List<Weight> getWeightByNameOrderByDate(String name);
}
