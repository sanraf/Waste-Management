package com.enviro.assessment.grad001.santosrafaelo.dto;


import jakarta.validation.constraints.NotBlank;

public record RecyclingTipDto(Long id, @NotBlank(message = "recycling tip is required")String tip) {
}
