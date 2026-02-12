package com.wesleysilva.bappoint.Company;

import com.wesleysilva.bappoint.Appointments.AppointmentMapper;
import com.wesleysilva.bappoint.Company.dto.CompanyResponseDTO;
import com.wesleysilva.bappoint.Company.dto.CreateCompanyDTO;
import com.wesleysilva.bappoint.Company.dto.UpdateCompanyDTO;
import com.wesleysilva.bappoint.Settings.SettingsMapper;
import com.wesleysilva.bappoint.Settings.SettingsModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    private final SettingsMapper settingsMapper;
    private final AppointmentMapper appointmentMapper;

    public CompanyMapper(SettingsMapper settingsMapper, AppointmentMapper appointmentMapper) {
        this.settingsMapper = settingsMapper;
        this.appointmentMapper = appointmentMapper;
    }

    public CompanyModel toCreate(CreateCompanyDTO createCompanyDTO) {
        CompanyModel companyModel = new CompanyModel();
        companyModel.setName(createCompanyDTO.getName());
        companyModel.setEmail(createCompanyDTO.getEmail());
        companyModel.setPhone(createCompanyDTO.getPhone());
        companyModel.setAddress(createCompanyDTO.getAddress());

        if (createCompanyDTO.getSettings() != null) {
            SettingsModel settingsModel = settingsMapper.map(createCompanyDTO.getSettings());
            companyModel.setSettings(settingsModel);
        }

        return companyModel;
    }

    public CompanyResponseDTO toResponseDTO(CompanyModel companyModel) {
        CompanyResponseDTO companyDTO = new CompanyResponseDTO();
        companyDTO.setId(companyModel.getId());
        companyDTO.setName(companyModel.getName());
        companyDTO.setEmail(companyModel.getEmail());
        companyDTO.setPhone(companyModel.getPhone());
        companyDTO.setAddress(companyModel.getAddress());

        if (companyModel.getSettings() != null) {
            companyDTO.setSettings(settingsMapper.map(companyModel.getSettings()));
        }

        if (companyModel.getAppointments() != null) {
            companyDTO.setAppointments(
                    companyModel.getAppointments().stream()
                            .map(appointmentMapper::toResponseDTO)
                            .collect(Collectors.toList())
            );
        }

        return companyDTO;
    }

    public CompanyModel toUpdateCompany(UpdateCompanyDTO updateCompanyDTO, CompanyModel existingCompany) {
        if (updateCompanyDTO.getName() != null) existingCompany.setName(updateCompanyDTO.getName());
        if (updateCompanyDTO.getEmail() != null) existingCompany.setEmail(updateCompanyDTO.getEmail());
        if (updateCompanyDTO.getPhone() != null) existingCompany.setPhone(updateCompanyDTO.getPhone());
        if (updateCompanyDTO.getAddress() != null) existingCompany.setAddress(updateCompanyDTO.getAddress());

        if (updateCompanyDTO.getSettings() != null) {
            existingCompany.setSettings(settingsMapper.map(updateCompanyDTO.getSettings()));
        }

        return existingCompany;
    }
}
