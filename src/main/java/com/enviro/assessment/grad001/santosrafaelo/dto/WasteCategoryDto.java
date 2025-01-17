package com.enviro.assessment.grad001.santosrafaelo.dto;

import com.enviro.assessment.grad001.santosrafaelo.model.DisposalGuideline;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record WasteCategoryDto(Long id,
                               @NotBlank(message = "category name is required")String name,
                               @NotBlank(message = "category description is required")String description, List<DisposalGuideline> guidelines) {

}
