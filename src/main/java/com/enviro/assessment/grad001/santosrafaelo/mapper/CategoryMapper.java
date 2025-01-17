package com.enviro.assessment.grad001.santosrafaelo.mapper;



import com.enviro.assessment.grad001.santosrafaelo.dto.WasteCategoryDto;
import com.enviro.assessment.grad001.santosrafaelo.model.WasteCategory;

import java.util.function.Function;

public class CategoryMapper {

    public static Function<WasteCategory,WasteCategoryDto>  mapToCategoryDto(){
        return category -> new WasteCategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getGuidelines()
        );
    }
}
