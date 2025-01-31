package com.enviro.assessment.grad001.santosrafaelo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DisposalGuidelineDto(Long id,
                                   @NotBlank(message = "disposal guideline is required") String guideline,
                                   Long categoryId) {
}
