package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Advices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvicesRepository extends JpaRepository<Advices,Integer> {
    public List<Advices> getAdvicesByAuthorName(String name);
}
