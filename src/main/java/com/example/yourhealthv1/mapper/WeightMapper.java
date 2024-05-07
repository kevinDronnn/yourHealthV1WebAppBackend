package com.example.yourhealthv1.mapper;

import com.example.yourhealthv1.dto.WeightDto;
import com.example.yourhealthv1.entity.Weight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WeightMapper {

    WeightMapper INSTANCE = Mappers.getMapper(WeightMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "weight", source = "weight")
    Weight weightDtoToWeightEntity(WeightDto weightDto);


    @Mapping(target = "name", source = "name")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "weight", source = "weight")
    List<WeightDto> weightEntityToWeightDto(List<Weight> weightList);
}
