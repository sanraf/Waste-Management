package com.enviro.assessment.grad001.santosrafaelo.service;

import com.enviro.assessment.grad001.santosrafaelo.dto.RecyclingTipDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecyclingTipService {

    RecyclingTipDto saveRecyclingTip(RecyclingTipDto tipDto);
    RecyclingTipDto updateRecyclingTip(RecyclingTipDto tipDto);
    void deleteRecyclingTip(Long id);
    RecyclingTipDto getRecyclingTipById(Long id);
    List<RecyclingTipDto> getAllRecyclingTip();
}
