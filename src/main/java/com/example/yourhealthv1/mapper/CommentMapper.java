package com.example.yourhealthv1.mapper;

import com.example.yourhealthv1.dto.CommentDto;
import com.example.yourhealthv1.entity.Comments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "text", source = "text")
    @Mapping(target = "authorName", source = "authorName")
    @Mapping(target = "recipes", source = "recipes")
    Comments commentDtoToCommentsEntity(CommentDto commentDto);


    @Mapping(target = "text", source = "text")
    @Mapping(target = "authorName", source = "authorName")
    @Mapping(target = "recipes", source = "recipes")
    List<CommentDto> commentsEntityToCommentDto(List<Comments> commentsList);
}
