package com.enviro.assessment.grad001.santosrafaelo.service;

import com.enviro.assessment.grad001.santosrafaelo.dto.DisposalGuidelineDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DisposalGuidelineService {

    DisposalGuidelineDto saveDisposalGuideline(DisposalGuidelineDto guidelineDto,Long categoryId);
    DisposalGuidelineDto updateDisposalGuideline(DisposalGuidelineDto guidelineDto);
    void deleteDisposalGuideline(Long id);
    DisposalGuidelineDto getDisposalGuidelineById(Long id);
    List<DisposalGuidelineDto> getAllDisposalGuidelines();
}
