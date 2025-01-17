package com.enviro.assessment.grad001.santosrafaelo.controller;

import com.enviro.assessment.grad001.santosrafaelo.appresponse.APIResponse;
import com.enviro.assessment.grad001.santosrafaelo.dto.WasteCategoryDto;
import com.enviro.assessment.grad001.santosrafaelo.model.WasteCategory;
import com.enviro.assessment.grad001.santosrafaelo.service.WasteCategoryService;
import com.enviro.assessment.grad001.santosrafaelo.service.serviceimpl.WasteCategoryImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/category")
public class WasteCategoryController {


    private final WasteCategoryService categoryService;

    @PostMapping("/save")
    private APIResponse<WasteCategoryDto> saveCategory(@RequestBody @Valid WasteCategoryDto categoryDto){
        WasteCategoryDto wasteCategoryDto = categoryService.saveCategory(categoryDto);
        return APIResponse.<WasteCategoryDto>builder()
                .statusMessage("success")
                .statusCode(HttpStatus.CREATED)
                .result(wasteCategoryDto)
                .build();
    }

    @GetMapping("/get-all")
    private APIResponse<List<WasteCategoryDto>> getAllCategories(){
        List<WasteCategoryDto> categoryDtos = categoryService.gatAllCategory();

        return APIResponse.<List<WasteCategoryDto>>builder()
                .statusMessage("success")
                .statusCode(HttpStatus.ACCEPTED)
                .result(categoryDtos)
                .build();
    }

    @GetMapping("/get/{id}")
    private APIResponse<WasteCategoryDto> getAllCategoryById(@PathVariable Long id){
        WasteCategoryDto category = categoryService.getCategoryById(id);
        return APIResponse.<WasteCategoryDto>builder()
                .statusMessage("success")
                .statusCode(HttpStatus.ACCEPTED)
                .result(category)
                .build();
    }

    @PutMapping("/update")
    private APIResponse<WasteCategoryDto> updateCategory(@RequestBody WasteCategoryDto categoryDto){
        WasteCategoryDto wasteCategoryDto = categoryService.updateCategory(categoryDto);

        return APIResponse.<WasteCategoryDto>builder()
                .statusMessage("success")
                .statusCode(HttpStatus.ACCEPTED)
                .result(wasteCategoryDto)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    private APIResponse<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return APIResponse.<WasteCategoryDto>builder()
                .statusMessage("success")
                .statusCode(HttpStatus.ACCEPTED)
                .build();
    }
}
