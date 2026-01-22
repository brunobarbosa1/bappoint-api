package com.wesleysilva.bappoint.Settings;

import com.wesleysilva.bappoint.Company.CompanyModel;
import com.wesleysilva.bappoint.Company.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class SettingsService {

    private final CompanyRepository companyRepository;
    private final SettingsMapper settingsMapper;

    public SettingsService(
            CompanyRepository companyRepository,
            SettingsMapper settingsMapper
    ) {
        this.companyRepository = companyRepository;
        this.settingsMapper = settingsMapper;
    }

    public SettingsDTO getByCompanyId(UUID companyId) {
        CompanyModel company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        SettingsModel settings = company.getSettings();

        if (settings == null) {
            throw new EntityNotFoundException("Settings not found for company");
        }

        return settingsMapper.map(settings);
    }

    public SettingsDTO updateByCompanyId(UUID companyId, SettingsDTO dto) {
        CompanyModel company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        SettingsModel settings = company.getSettings();

        if (settings == null) {
            throw new EntityNotFoundException("Settings not found for company");
        }

        settings.setAppointment_interval(dto.getAppointment_interval());
        settings.setMax_cancellation_interval(dto.getMax_cancellation_interval());

        return settingsMapper.map(settings);
    }
}
