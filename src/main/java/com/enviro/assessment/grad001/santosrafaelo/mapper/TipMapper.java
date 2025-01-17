package com.enviro.assessment.grad001.santosrafaelo.mapper;

import com.enviro.assessment.grad001.santosrafaelo.dto.RecyclingTipDto;
import com.enviro.assessment.grad001.santosrafaelo.model.RecyclingTip;

import java.util.function.Function;

public class TipMapper {

    public static Function<RecyclingTip, RecyclingTipDto> mapToTipDto(){
        return recyclingTip -> new RecyclingTipDto(
                recyclingTip.getId(),
                recyclingTip.getTip()
        );
    }

}
