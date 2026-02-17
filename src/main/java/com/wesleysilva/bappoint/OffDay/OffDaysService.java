package com.wesleysilva.bappoint.OffDay;

import com.wesleysilva.bappoint.Company.CompanyModel;
import com.wesleysilva.bappoint.Company.CompanyRepository;
import com.wesleysilva.bappoint.OffDay.dto.CreateOffDayDTO;
import com.wesleysilva.bappoint.OffDay.dto.OffDaysResponseDTO;
import com.wesleysilva.bappoint.OperatingHours.OperatingHoursDTO;
import com.wesleysilva.bappoint.OperatingHours.OperatingHoursModel;
import com.wesleysilva.bappoint.Settings.SettingsModel;
import com.wesleysilva.bappoint.Settings.SettingsRepository;
import com.wesleysilva.bappoint.exceptions.CompanyNotFoundException;
import com.wesleysilva.bappoint.exceptions.SettingsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OffDaysService {

   private final OffDaysRepository offDaysRepository;
   private final OffDaysMapper offDaysMapper;
   private final SettingsRepository settingsRepository;
   private final CompanyRepository companyRepository;

    public OffDaysService(OffDaysRepository offDaysRepository, OffDaysMapper offDaysMapper, SettingsRepository settingsRepository, CompanyRepository companyRepository) {
        this.offDaysRepository = offDaysRepository;
        this.offDaysMapper = offDaysMapper;
        this.settingsRepository = settingsRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CreateOffDayDTO createOffDays(UUID companyId, OffDaysDTO offDaysDTO) {
        OffDaysModel offDaysModel = offDaysMapper.toEntity(offDaysDTO);

        CompanyModel company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);

        SettingsModel settings = company.getSettings();

        if(settings == null) {
            throw new SettingsNotFoundException();
        }

        offDaysModel.setSettings(settings);

        offDaysModel = offDaysRepository.save(offDaysModel);

        return offDaysMapper.toCreate(offDaysModel);
    }

    @Transactional(readOnly = true)
    public List<OffDaysResponseDTO> getAllOffDays(){
        List<OffDaysModel> offDaysModels = offDaysRepository.findAll();
        return offDaysModels.stream()
                .map(offDaysMapper::toResponse)
                .collect(Collectors.toList());
    }

    public OffDaysDTO getOffDaysById(UUID offDaysId) {
        OffDaysModel offDayById = offDaysRepository.findById(offDaysId).orElseThrow(()  -> new RuntimeException("OffDays not found"));
        return offDaysMapper.toDto(offDayById);
    }

    void deleteOffDaysById(UUID offDaysId) {
        offDaysRepository.deleteById(offDaysId);
    }

    public OffDaysDTO updateService(UUID offDaysID, OffDaysDTO offDaysDTO) {
        Optional<OffDaysModel> existingOffDays = offDaysRepository.findById(offDaysID);

        if (existingOffDays.isPresent()) {
            OffDaysModel offDaysToUpdate = existingOffDays.get();

            offDaysToUpdate.setOffDaystype(offDaysDTO.getOffDaysType());
            offDaysToUpdate.setReason(offDaysDTO.getReason());
            offDaysToUpdate.setDate(offDaysDTO.getDate());
            offDaysToUpdate.setOffDaystype(offDaysDTO.getOffDaysType());

            OffDaysModel savedOffDays = offDaysRepository.save(offDaysToUpdate);
            return offDaysMapper.toDto(savedOffDays);
        }

        return null;
    }
}
