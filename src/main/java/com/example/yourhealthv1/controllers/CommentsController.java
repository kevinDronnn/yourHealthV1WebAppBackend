package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Comments;
import com.example.yourhealthv1.service.CommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CommentsController {
    private static Logger logger = LoggerFactory.getLogger(AdvicesController.class);
    @Autowired
    CommentsService commentsService;

    @PostMapping("/comment")
    Comments save(@RequestBody Comments comment) {
        logger.info("Comment was added");
        return commentsService.addComment(comment);
    }

    @GetMapping("/comments")
    List<Comments> getAllComments() {
        return commentsService.getComments();
    }

    @DeleteMapping("/comment/{id}")
    public String deleteCommentById(@PathVariable int id) {
        commentsService.deleteComment(id);
        logger.info("Comment with id="+id+" was deleted");
        return "Comment was deleted, id=" + id;
    }
}
