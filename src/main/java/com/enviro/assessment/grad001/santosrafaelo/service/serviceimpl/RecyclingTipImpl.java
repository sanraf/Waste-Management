package com.enviro.assessment.grad001.santosrafaelo.service.serviceimpl;

import com.enviro.assessment.grad001.santosrafaelo.appexception.BusinessOperationFailedException;
import com.enviro.assessment.grad001.santosrafaelo.appexception.WasteEntityNotFoundException;
import com.enviro.assessment.grad001.santosrafaelo.dto.RecyclingTipDto;
import com.enviro.assessment.grad001.santosrafaelo.mapper.TipMapper;
import com.enviro.assessment.grad001.santosrafaelo.model.RecyclingTip;
import com.enviro.assessment.grad001.santosrafaelo.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.santosrafaelo.service.RecyclingTipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static com.enviro.assessment.grad001.santosrafaelo.util.ExceptionMessage.TIP_NOT_FOUND;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RecyclingTipImpl implements RecyclingTipService {

    private final RecyclingTipRepository tipRepository;

    @Override
    public RecyclingTipDto saveRecyclingTip(RecyclingTipDto tipDto) {

        try {
            RecyclingTip recyclingTip = RecyclingTip.builder()
                    .tip(tipDto.tip())
                    .build();

            RecyclingTip saveTip = tipRepository.save(recyclingTip);
            log.info("RecyclingTiImpl: tip saved successfully in DB");

            return TipMapper.mapToTipDto().apply(saveTip);
        } catch (Exception e) {
            log.error("Exception occurred while creating recycling tip   {}",e.getMessage());
            throw new BusinessOperationFailedException("Something went wrong");
        }
    }

    @Override
    public RecyclingTipDto updateRecyclingTip(RecyclingTipDto tipDto) {

        try {
            RecyclingTip newData = tipRepository.findById(tipDto.id())
                    .map(oldData ->{
                        Optional.ofNullable(tipDto.tip()).ifPresent(oldData::setTip);
                        return tipRepository.save(oldData);
                    }).orElseThrow(() -> new WasteEntityNotFoundException(String.format(TIP_NOT_FOUND,tipDto.id())));

            log.info("RecyclingTiImpl: tip updated successfully in DB");
            return TipMapper.mapToTipDto().apply(newData);
        } catch (WasteEntityNotFoundException e) {
            log.error("Exception occurred while fetching waste category by ID  {}",e.getMessage());
            throw new WasteEntityNotFoundException(e.getMessage());
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessOperationFailedException("Something went wrong");
        }

    }

    @Override
    public void deleteRecyclingTip(Long id) {
        try {
            RecyclingTip tip = tipRepository.findById(id)
                    .orElseThrow(() -> new WasteEntityNotFoundException(String.format(TIP_NOT_FOUND,id)));
            tipRepository.delete(tip);

            log.info("RecyclingTiImpl: recycling tip deleted successfully");
        } catch (WasteEntityNotFoundException e) {
            log.error(e.getMessage());
            throw new WasteEntityNotFoundException(e.getMessage());
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessOperationFailedException("Something went wrong");
        }
    }

    @Override
    public RecyclingTipDto getRecyclingTipById(Long id) {

        try {
            RecyclingTip tip = tipRepository.findById(id)
                     .orElseThrow(() -> new WasteEntityNotFoundException(String.format(TIP_NOT_FOUND,id)));
            log.info("RecyclingTiImpl: recycling tip fetched by ID successfully from DB");
            return TipMapper.mapToTipDto().apply(tip);
        } catch (WasteEntityNotFoundException e) {
            log.error("Exception occurred while fetching recycling tip by ID");
            throw new WasteEntityNotFoundException(e.getMessage());
        }catch (Exception e) {
            log.error("Exception occurred while fetching tip by ID {}",e.getMessage());
            throw new BusinessOperationFailedException("Something went wrong");
        }
    }

    @Override
    public List<RecyclingTipDto> getAllRecyclingTip() {

        try {
            return tipRepository.findAll()
                    .stream().map(tipRepository -> TipMapper.mapToTipDto().apply(tipRepository)).toList();
        } catch (Exception e) {
            log.error("Exception occurred while fetching list of tips {}",e.getMessage());
            throw new BusinessOperationFailedException("Failed to fetch all tips from DB");
        }
    }
}
