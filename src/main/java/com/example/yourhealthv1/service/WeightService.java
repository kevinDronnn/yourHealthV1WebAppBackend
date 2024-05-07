package com.example.yourhealthv1.service;

import com.example.yourhealthv1.controller.AdvicesController;
import com.example.yourhealthv1.dto.WeightDto;
import com.example.yourhealthv1.entity.Weight;
import com.example.yourhealthv1.mapper.WeightMapper;
import com.example.yourhealthv1.repository.WeightRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WeightService {

    private static Logger logger = LoggerFactory.getLogger(AdvicesController.class);
    @Autowired
    WeightRepository weightRepository;

    public List<WeightDto> getAllWeights(String name){
        Optional<List<WeightDto>> weightList =
                Optional.ofNullable(WeightMapper.INSTANCE.weightEntityToWeightDto
                        (weightRepository.getWeightByNameOrderByDate(name)));
        if (weightList.isPresent()){
            return weightList.get();
        }else {
            throw new NoSuchElementException();
        }
    }
    @Transactional
    public void saveWeight(WeightDto weight){
        Optional<WeightDto> w = Optional.ofNullable(weight);
        if (w.isPresent()){
            weightRepository.save(WeightMapper.INSTANCE.weightDtoToWeightEntity(w.get()));
            logger.info("weight was added");
        }else {
            throw new NullPointerException();
        }
    }
}
