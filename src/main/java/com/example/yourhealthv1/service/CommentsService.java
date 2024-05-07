package com.example.yourhealthv1.service;

import com.example.yourhealthv1.dto.CommentDto;
import com.example.yourhealthv1.mapper.CommentMapper;
import com.example.yourhealthv1.repository.CommentsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    private static Logger logger = LoggerFactory.getLogger(CommentsService.class);
    @Autowired
    CommentsRepository commentsRepository;

    public List<CommentDto> getComments() {
        return CommentMapper.INSTANCE.commentsEntityToCommentDto(commentsRepository.findAll());
    }
    @Transactional
    public CommentDto addComment(CommentDto comment) {
        Optional<CommentDto> comm = Optional.ofNullable(comment);
        if (comm.isPresent()){
            logger.info("Comment was added");
            commentsRepository.save(CommentMapper.INSTANCE.commentDtoToCommentsEntity(comm.get()));
            return comm.get();
        }else {
            throw new NullPointerException("some of fields are null");
        }
    }
    @Transactional
    public void deleteComment(int id) {
        commentsRepository.deleteById(id);
        logger.info("Comment with id="+id+" was deleted");
    }
}
