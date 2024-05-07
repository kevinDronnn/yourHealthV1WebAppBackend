package com.example.yourhealthv1.mapper;

import com.example.yourhealthv1.dto.AdviceDto;
import com.example.yourhealthv1.entity.Advices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdviceMapper {

    AdviceMapper INSTANCE = Mappers.getMapper(AdviceMapper.class);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "authorName", source = "authorName")
    Advices adviceDtoToAdvicesEntity(AdviceDto adviceDto);


    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "authorName", source = "authorName")
    List<AdviceDto> advicesEntityToAdviceDto(List<Advices> advicesList);
}
