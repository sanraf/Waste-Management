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
    @ResponseStatus(HttpStatus.CREATED)
    private APIResponse<WasteCategoryDto> saveCategory(@RequestBody @Valid WasteCategoryDto categoryDto){
        WasteCategoryDto wasteCategoryDto = categoryService.saveCategory(categoryDto);
        return APIResponse.<WasteCategoryDto>builder()
                .message("Success")
                .statusCode(HttpStatus.CREATED.value())
                .result(wasteCategoryDto)
                .build();
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    private APIResponse<List<WasteCategoryDto>> getAllCategories(){
        List<WasteCategoryDto> categoryDtos = categoryService.gatAllCategory();

        return APIResponse.<List<WasteCategoryDto>>builder()
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .result(categoryDtos)
                .build();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    private APIResponse<WasteCategoryDto> getAllCategoryById(@PathVariable Long id){
        WasteCategoryDto category = categoryService.getCategoryById(id);
        return APIResponse.<WasteCategoryDto>builder()
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .result(category)
                .build();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private APIResponse<WasteCategoryDto> updateCategory(@RequestBody WasteCategoryDto categoryDto){
        WasteCategoryDto wasteCategoryDto = categoryService.updateCategory(categoryDto);

        return APIResponse.<WasteCategoryDto>builder()
                .message("Success")
                .statusCode(HttpStatus.ACCEPTED.value())
                .result(wasteCategoryDto)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    private APIResponse<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return APIResponse.<WasteCategoryDto>builder()
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
