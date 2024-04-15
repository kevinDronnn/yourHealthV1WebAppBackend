package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
}
