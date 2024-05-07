package com.example.yourhealthv1.mapper;

import com.example.yourhealthv1.dto.WorkoutDto;
import com.example.yourhealthv1.entity.Workouts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkoutMapper {
    WorkoutMapper INSTANCE = Mappers.getMapper(WorkoutMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "duration", source = "duration")
    Workouts workoutDtoToWorkoutsEntity(WorkoutDto workoutDto);


    @Mapping(target = "name", source = "name")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "duration", source = "duration")
    List<WorkoutDto> workoutsEntityToWorkoutDto(List<Workouts> workoutsList);

}
