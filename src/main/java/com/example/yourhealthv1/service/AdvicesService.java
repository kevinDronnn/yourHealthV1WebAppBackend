package com.example.yourhealthv1.service;

import com.example.yourhealthv1.dto.AdviceDto;
import com.example.yourhealthv1.entity.Advices;
import com.example.yourhealthv1.mapper.AdviceMapper;
import com.example.yourhealthv1.repository.AdvicesRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdvicesService {

    private static Logger logger = LoggerFactory.getLogger(AdvicesService.class);
    @Autowired
    AdvicesRepository advicesRepository;

    public List<AdviceDto> getAdvices() {
        return AdviceMapper.INSTANCE.advicesEntityToAdviceDto(advicesRepository.findAll());
    }
    public List<AdviceDto> getAdvicesByAuthor(String name) {
        Optional<List<AdviceDto>> list = Optional.ofNullable(AdviceMapper.INSTANCE.advicesEntityToAdviceDto(advicesRepository.getAdvicesByAuthorName(name)));
        if (list.isPresent()) {
            return list.get();
        }else{
            throw new NoSuchElementException();
        }
    }
    @Transactional
    public AdviceDto addAdvices(AdviceDto advices) {
        Optional<AdviceDto> adv = Optional.ofNullable(advices);
        if (adv.isPresent()){
            advicesRepository.save(AdviceMapper.INSTANCE.adviceDtoToAdvicesEntity(adv.get()));
            logger.info("Advice was added: " + advices.getTitle());
            return adv.get();
        }else {
            throw new NullPointerException("some of fields are null");
        }

    }
    @Transactional
    public void deleteAdvice(int id) {
        advicesRepository.deleteById(id);
        logger.info("advice with id= "+id+" was delete");
    }


}
