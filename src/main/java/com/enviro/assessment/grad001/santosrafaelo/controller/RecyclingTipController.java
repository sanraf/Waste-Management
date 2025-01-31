package com.enviro.assessment.grad001.santosrafaelo.controller;

import com.enviro.assessment.grad001.santosrafaelo.appresponse.APIResponse;
import com.enviro.assessment.grad001.santosrafaelo.dto.RecyclingTipDto;
import com.enviro.assessment.grad001.santosrafaelo.service.RecyclingTipService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/tip")
public class RecyclingTipController {


    private final RecyclingTipService recyclingTipService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public APIResponse<RecyclingTipDto> saveRecyclingTip(@RequestBody @Valid RecyclingTipDto tipDto) {;
        RecyclingTipDto recyclingTipDto = recyclingTipService.saveRecyclingTip(tipDto);
        return APIResponse.<RecyclingTipDto>builder()
                .message("Success")
                .statusCode(HttpStatus.CREATED.value())
                .result(recyclingTipDto)
                .build();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public APIResponse<RecyclingTipDto> updateRecyclingTip(@RequestBody RecyclingTipDto tipDto) {
        RecyclingTipDto recyclingTipDto = recyclingTipService.updateRecyclingTip(tipDto);
        return APIResponse.<RecyclingTipDto>builder()
                .message("Success")
                .statusCode(HttpStatus.ACCEPTED.value())
                .result(recyclingTipDto)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse<?> deleteRecyclingTip(@PathVariable Long id) {
        recyclingTipService.deleteRecyclingTip(id);
        return APIResponse.builder()
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse<RecyclingTipDto> getRecyclingTipById(@PathVariable Long id) {
        RecyclingTipDto recyclingTip = recyclingTipService.getRecyclingTipById(id);
        return APIResponse.<RecyclingTipDto>builder()
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .result(recyclingTip)
                .build();
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse<List<RecyclingTipDto>> gatAllRecyclingTip() {
        List<RecyclingTipDto> recyclingTipDtos = recyclingTipService.getAllRecyclingTip();
        return APIResponse.<List<RecyclingTipDto>>builder()
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .result(recyclingTipDtos)
                .build();
    }
}
