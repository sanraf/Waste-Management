package com.enviro.assessment.grad001.santosrafaelo.service.serviceimpl;

import com.enviro.assessment.grad001.santosrafaelo.appexception.BusinessOperationFailedException;
import com.enviro.assessment.grad001.santosrafaelo.appexception.WasteEntityNotFoundException;
import com.enviro.assessment.grad001.santosrafaelo.dto.DisposalGuidelineDto;
import com.enviro.assessment.grad001.santosrafaelo.mapper.GuidelineMapper;
import com.enviro.assessment.grad001.santosrafaelo.model.DisposalGuideline;
import com.enviro.assessment.grad001.santosrafaelo.model.WasteCategory;
import com.enviro.assessment.grad001.santosrafaelo.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.santosrafaelo.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.santosrafaelo.service.DisposalGuidelineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class DisposalGuidelineImpl implements DisposalGuidelineService {


    private final DisposalGuidelineRepository guidelineRepository;

    private final WasteCategoryRepository categoryRepository;

    @Override
    public DisposalGuidelineDto saveDisposalGuideline(DisposalGuidelineDto guidelineDto, Long categoryId) {
        try {
            WasteCategory category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new WasteEntityNotFoundException("No category found with id " + categoryId));
            log.debug("DisposalGuidelineImpl: category successfully fetched from DB {}",category);
            DisposalGuideline guideline = DisposalGuideline.builder()
                    .guideline(guidelineDto.guideline())
                    .categoryId(category.getId())
                    .build();

            category.getGuidelines().add(guideline);

            categoryRepository.save(category);
            log.info("DisposalGuidelineImpl: guideline saved successfully in DB");
            return GuidelineMapper.mapToGuidelineDto().apply(guideline);
        } catch (WasteEntityNotFoundException e) {
            log.error("Exception occurred while creating disposal guideline {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to create guideline");
        }
    }

    @Override
    public DisposalGuidelineDto updateDisposalGuideline(DisposalGuidelineDto guidelineDto) {
        try {
            DisposalGuideline newData = guidelineRepository.findById(guidelineDto.id())
                    .map(oldData ->{
                        Optional.ofNullable(guidelineDto.guideline()).ifPresent(oldData::setGuideline);
                        return guidelineRepository.save(oldData);
                    }).orElseThrow(() -> new WasteEntityNotFoundException("Cannot update non-existing guideline with id " + guidelineDto.id()));

            log.info("DisposalGuidelineImpl: guideline updated successfully in DB");
            return GuidelineMapper.mapToGuidelineDto().apply(newData);
        } catch (WasteEntityNotFoundException e) {
            log.error("Exception occurred while updating disposal guideline {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to update guideline");
        }
    }

    @Override
    public void deleteDisposalGuideline(Long id) {
        try {
            DisposalGuideline guideline = guidelineRepository.findById(id)
                    .orElseThrow(()-> new WasteEntityNotFoundException("Cannot delete non-existing guideline with id " + id));
            WasteCategory category = categoryRepository.findById(guideline.getCategoryId())
                    .orElseThrow(() -> new WasteEntityNotFoundException("category not found"));
            log.debug("DisposalGuidelineImpl: guideline & category successfully fetched from DB {} {}",guideline,category);
            category.getGuidelines().remove(guideline);
            categoryRepository.save(category);

            guidelineRepository.delete(guideline);
        } catch (WasteEntityNotFoundException e) {
            log.error("Exception occurred while deleting disposal guideline {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to delete guideline");
        }
    }

    @Override
    public DisposalGuidelineDto getDisposalGuidelineById(Long id) {
        try {
            DisposalGuideline disposalGuideline = guidelineRepository.findById(id)
                    .orElseThrow(() -> new WasteEntityNotFoundException("No guideline found with id " + id));

            log.info("DisposalGuidelineImpl: guideline fetched by ID successfully from DB");
            return GuidelineMapper.mapToGuidelineDto().apply(disposalGuideline);
        } catch (WasteEntityNotFoundException e) {
            log.error("Exception occurred while fetching disposal guideline by ID {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to fetch guideline from DB");
        }
    }

    @Override
    public List<DisposalGuidelineDto> gatAllDisposalGuidelines() {
        try {
            List<DisposalGuideline> guidelines = guidelineRepository.findAll();

            log.info("DisposalGuidelineImpl: guideline list fetched successfully from DB");
            return guidelines.stream()
                    .map(disposalGuideline -> GuidelineMapper
                            .mapToGuidelineDto().apply(disposalGuideline)).toList();

        } catch (Exception e) {
            log.error("Exception occurred while fetching list of disposal guideline {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to fetch all guidelines from DB");
        }
    }
}
