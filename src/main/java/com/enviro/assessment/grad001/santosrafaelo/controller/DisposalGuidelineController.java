package com.enviro.assessment.grad001.santosrafaelo.controller;

import com.enviro.assessment.grad001.santosrafaelo.appresponse.APIResponse;
import com.enviro.assessment.grad001.santosrafaelo.dto.DisposalGuidelineDto;
import com.enviro.assessment.grad001.santosrafaelo.service.DisposalGuidelineService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/guideline")
public class DisposalGuidelineController {


    private final DisposalGuidelineService disposalGuidelineService;

    @PostMapping("/save/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    private APIResponse<DisposalGuidelineDto> saveGuideline(@RequestBody @Valid DisposalGuidelineDto guidelineDto, @PathVariable Long categoryId){
        DisposalGuidelineDto disposalGuidelineDto = disposalGuidelineService.saveDisposalGuideline(guidelineDto, categoryId);
        return APIResponse.<DisposalGuidelineDto>builder()
                .statusMessage("Success")
                .statusCode(HttpStatus.CREATED.value())
                .result(disposalGuidelineDto)
                .build();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    private APIResponse<DisposalGuidelineDto> updateGuideline(@RequestBody DisposalGuidelineDto guidelineDto){
        DisposalGuidelineDto disposalGuidelineDto = disposalGuidelineService.updateDisposalGuideline(guidelineDto);
        return APIResponse.<DisposalGuidelineDto>builder()
                .statusMessage("Success")
                .statusCode(HttpStatus.OK.value())
                .result(disposalGuidelineDto)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    private APIResponse<?> deleteGuideline(@PathVariable Long id){
        disposalGuidelineService.deleteDisposalGuideline(id);
        return APIResponse.builder()
                .statusMessage("Success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    private APIResponse<DisposalGuidelineDto> getGuidelineById(@PathVariable Long id){
        DisposalGuidelineDto disposalGuidelineDto = disposalGuidelineService.getDisposalGuidelineById(id);
        return APIResponse.<DisposalGuidelineDto>builder()
                .statusMessage("Success")
                .statusCode(HttpStatus.OK.value())
                .result(disposalGuidelineDto)
                .build();
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private APIResponse<List<DisposalGuidelineDto>> gatAllGuidelines(){
        List<DisposalGuidelineDto> disposalGuidelineDtos = disposalGuidelineService.getAllDisposalGuidelines();
        return APIResponse.<List<DisposalGuidelineDto>>builder()
                .statusMessage("Success")
                .statusCode(HttpStatus.ACCEPTED.value())
                .result(disposalGuidelineDtos)
                .build();
    }

}
