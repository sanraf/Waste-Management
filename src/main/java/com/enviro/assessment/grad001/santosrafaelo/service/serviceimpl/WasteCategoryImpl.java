package com.enviro.assessment.grad001.santosrafaelo.service.serviceimpl;

import com.enviro.assessment.grad001.santosrafaelo.appexception.BusinessOperationFailedException;
import com.enviro.assessment.grad001.santosrafaelo.appexception.WasteEntityNotFoundException;
import com.enviro.assessment.grad001.santosrafaelo.dto.WasteCategoryDto;
import com.enviro.assessment.grad001.santosrafaelo.mapper.CategoryMapper;
import com.enviro.assessment.grad001.santosrafaelo.model.WasteCategory;
import com.enviro.assessment.grad001.santosrafaelo.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.santosrafaelo.service.WasteCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class WasteCategoryImpl implements WasteCategoryService {

    @Autowired
    private final WasteCategoryRepository categoryRepository;

    @Override
    public WasteCategoryDto saveCategory(WasteCategoryDto categoryDto) {

        try {
            WasteCategory category = WasteCategory.builder()
                    .name(categoryDto.name())
                    .description(categoryDto.description())
                    .guidelines(new ArrayList<>())
                    .build();
            WasteCategory savedCategory = categoryRepository.save(category);
            log.info("WasteCategoryImpl: category saved successfully in DB");
            return CategoryMapper.mapToCategoryDto().apply(savedCategory);
        } catch (Exception e) {
            log.error("Exception occurred while creating waste category  {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to create category");
        }
    }

    @Override
    public WasteCategoryDto updateCategory(WasteCategoryDto categoryDto) {
        try {
            WasteCategory newData = categoryRepository.findById(categoryDto.id())
                     .map(oldData ->{
                         Optional.ofNullable(categoryDto.name()).ifPresent(oldData::setName);
                         Optional.ofNullable(categoryDto.description()).ifPresent(oldData::setDescription);
                         return categoryRepository.save(oldData);
                     }).orElseThrow(() -> new WasteEntityNotFoundException("Cannot update non-existing category with id " + categoryDto.id()));

            log.info("WasteCategoryImpl: category updated successfully in DB");
            return CategoryMapper.mapToCategoryDto().apply(newData);
        } catch (WasteEntityNotFoundException e) {
            log.error("Exception occurred while updating waste category  {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to update category");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            categoryRepository.findById(id)
                    .ifPresentOrElse(categoryRepository::delete,()->{ throw new WasteEntityNotFoundException("Cannot delete non-existing category with id " + id);});
            log.info("WasteCategoryImpl: category deleted successfully in DB");
        } catch (Exception e) {
            log.error("Exception occurred while deleting waste category  {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to deleting category");
        }
    }

    @Override
    public WasteCategoryDto getCategoryById(Long id) {
        try {
            WasteCategory category = categoryRepository.findById(id).orElseThrow(() -> new WasteEntityNotFoundException("Not category found with id " + id));
            log.info("WasteCategoryImpl: category fetched successfully from DB");
            return CategoryMapper.mapToCategoryDto().apply(category);
        } catch (WasteEntityNotFoundException e) {
            log.error("Exception occurred while fetching waste category by ID  {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to fetch category");
        }
    }

    @Override
    public List<WasteCategoryDto> gatAllCategory() {
        try {
            List<WasteCategory> categories =  categoryRepository.findAll();

            log.debug("DisposalGuidelineImpl: category successfully fetched from DB {}",categories);
            return categories.stream().map(category -> CategoryMapper.mapToCategoryDto().apply(category)).toList();
        } catch (Exception e) {
            log.error("Exception occurred while fetching list of waste category by ID  {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to fetch all category From DB");
        }
    }

}
