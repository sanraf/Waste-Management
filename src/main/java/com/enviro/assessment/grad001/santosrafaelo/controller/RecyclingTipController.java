package com.enviro.assessment.grad001.santosrafaelo.controller;

import com.enviro.assessment.grad001.santosrafaelo.appresponse.APIResponse;
import com.enviro.assessment.grad001.santosrafaelo.dto.RecyclingTipDto;
import com.enviro.assessment.grad001.santosrafaelo.service.RecyclingTipService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/tip")
public class RecyclingTipController {


    private final RecyclingTipService recyclingTipService;

    @PostMapping("/save")
    public APIResponse<RecyclingTipDto> saveRecyclingTip(@RequestBody @Valid RecyclingTipDto tipDto) {;
        RecyclingTipDto recyclingTipDto = recyclingTipService.saveRecyclingTip(tipDto);
        return APIResponse.<RecyclingTipDto>builder()
                .statusMessage("success")
                .result(recyclingTipDto)
                .build();
    }

    @PutMapping("/update")
    public APIResponse<RecyclingTipDto> updateRecyclingTip(@RequestBody RecyclingTipDto tipDto) {
        RecyclingTipDto recyclingTipDto = recyclingTipService.updateRecyclingTip(tipDto);
        return APIResponse.<RecyclingTipDto>builder()
                .statusMessage("success")
                .result(recyclingTipDto)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public APIResponse<?> deleteRecyclingTip(@PathVariable Long id) {
        recyclingTipService.deleteRecyclingTip(id);
        return APIResponse.builder()
                .statusMessage("success")
                .build();
    }

    @GetMapping("/get/{id}")
    public APIResponse<RecyclingTipDto> getRecyclingTipById(@PathVariable Long id) {
        RecyclingTipDto recyclingTip = recyclingTipService.getRecyclingTipById(id);
        return APIResponse.<RecyclingTipDto>builder()
                .statusMessage("success")
                .result(recyclingTip)
                .build();
    }

    @GetMapping("/get-all")
    public APIResponse<List<RecyclingTipDto>> gatAllRecyclingTip() {
        List<RecyclingTipDto> recyclingTipDtos = recyclingTipService.gatAllRecyclingTip();
        return APIResponse.<List<RecyclingTipDto>>builder()
                .statusMessage("success")
                .result(recyclingTipDtos)
                .build();
    }
}
