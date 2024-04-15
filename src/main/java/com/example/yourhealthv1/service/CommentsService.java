package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.Comments;
import com.example.yourhealthv1.repository.CommentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentsService {

    @Autowired
    CommentsRepository commentsRepository;

    public List<Comments> getComments() {
        return commentsRepository.findAll();
    }
    @Transactional
    public Comments addComment(Comments comment) {
        return commentsRepository.save(comment);
    }
    @Transactional
    public void deleteComment(int id) {
        commentsRepository.deleteById(id);
    }
}
