package com.enviro.assessment.grad001.santosrafaelo.mapper;

import com.enviro.assessment.grad001.santosrafaelo.dto.DisposalGuidelineDto;
import com.enviro.assessment.grad001.santosrafaelo.model.DisposalGuideline;

import java.util.function.Function;

public class GuidelineMapper {

    public static Function<DisposalGuideline, DisposalGuidelineDto> mapToGuidelineDto(){
        return guideline -> new DisposalGuidelineDto(
                guideline.getId(),
                guideline.getGuideline(),
                guideline.getCategoryId()
        );
    }

}
