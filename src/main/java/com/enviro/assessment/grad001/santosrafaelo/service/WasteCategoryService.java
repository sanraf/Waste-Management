package com.enviro.assessment.grad001.santosrafaelo.service;

import com.enviro.assessment.grad001.santosrafaelo.dto.WasteCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WasteCategoryService {

     WasteCategoryDto saveCategory(WasteCategoryDto categoryDto);
     WasteCategoryDto updateCategory(WasteCategoryDto categoryDto);
     void deleteCategory(Long id);
     WasteCategoryDto getCategoryById(Long id);
     List<WasteCategoryDto> gatAllCategory();

}
