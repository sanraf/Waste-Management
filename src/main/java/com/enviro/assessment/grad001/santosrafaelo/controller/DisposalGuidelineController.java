package com.enviro.assessment.grad001.santosrafaelo.controller;

import com.enviro.assessment.grad001.santosrafaelo.appresponse.APIResponse;
import com.enviro.assessment.grad001.santosrafaelo.dto.DisposalGuidelineDto;
import com.enviro.assessment.grad001.santosrafaelo.service.DisposalGuidelineService;
import com.enviro.assessment.grad001.santosrafaelo.service.serviceimpl.DisposalGuidelineImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/guideline")
public class DisposalGuidelineController {


    private final DisposalGuidelineService disposalGuidelineService;

    @PostMapping("/save/{categoryId}")
    private APIResponse<DisposalGuidelineDto> saveGuideline(@RequestBody @Valid DisposalGuidelineDto guidelineDto, @PathVariable Long categoryId){
        DisposalGuidelineDto disposalGuidelineDto = disposalGuidelineService.saveDisposalGuideline(guidelineDto, categoryId);
        return APIResponse.<DisposalGuidelineDto>builder()
                .statusMessage("success")
                .result(disposalGuidelineDto)
                .build();
    }

    @PutMapping("/update")
    private APIResponse<DisposalGuidelineDto> updateGuideline(@RequestBody DisposalGuidelineDto guidelineDto){
        DisposalGuidelineDto disposalGuidelineDto = disposalGuidelineService.updateDisposalGuideline(guidelineDto);
        return APIResponse.<DisposalGuidelineDto>builder()
                .statusMessage("success")
                .result(disposalGuidelineDto)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    private APIResponse<?> deleteGuideline(@PathVariable Long id){
        disposalGuidelineService.deleteDisposalGuideline(id);
        return APIResponse.builder()
                .statusMessage("success")
                .build();
    }

    @GetMapping("/get/{id}")
    private APIResponse<DisposalGuidelineDto> getGuidelineById(@PathVariable Long id){
        DisposalGuidelineDto disposalGuidelineDto = disposalGuidelineService.getDisposalGuidelineById(id);
        return APIResponse.<DisposalGuidelineDto>builder()
                .statusMessage("success")
                .result(disposalGuidelineDto)
                .build();
    }

    @GetMapping("/get")
    private APIResponse<List<DisposalGuidelineDto>> gatAllGuidelines(){
        List<DisposalGuidelineDto> disposalGuidelineDtos = disposalGuidelineService.gatAllDisposalGuidelines();
        return APIResponse.<List<DisposalGuidelineDto>>builder()
                .statusMessage("success")
                .result(disposalGuidelineDtos)
                .build();
    }

}
