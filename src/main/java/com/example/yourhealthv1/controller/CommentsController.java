package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.CommentDto;
import com.example.yourhealthv1.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    @PostMapping("/comment")
    ResponseEntity<CommentDto> save(@RequestBody CommentDto comment) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(commentsService.addComment(comment));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommentDto());
        }
    }

    @GetMapping("/comments")
    ResponseEntity<List<CommentDto>> getAllComments() {
        return ResponseEntity.ok(commentsService.getComments());
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable int id) {
        commentsService.deleteComment(id);
        return ResponseEntity.ok("Comment was deleted, id=" + id);
    }
}
