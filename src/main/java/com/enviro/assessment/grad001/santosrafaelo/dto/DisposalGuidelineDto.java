package com.enviro.assessment.grad001.santosrafaelo.dto;

import jakarta.validation.constraints.NotBlank;

public record DisposalGuidelineDto(Long id,
                                   @NotBlank(message = "disposal guideline is required") String guideline,
                                   Long categoryId) {
}
