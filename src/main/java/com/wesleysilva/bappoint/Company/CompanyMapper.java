package com.wesleysilva.bappoint.Company;

import com.wesleysilva.bappoint.Appointments.AppointmentMapper;
import com.wesleysilva.bappoint.Appointments.AppointmentModel;
import com.wesleysilva.bappoint.Services.ServiceModel;
import com.wesleysilva.bappoint.Settings.SettingsMapper;
import com.wesleysilva.bappoint.Settings.SettingsModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    private final SettingsMapper settingsMapper;
    private final AppointmentMapper appointmentMapper;

    public CompanyMapper(SettingsMapper settingsMapper, AppointmentMapper appointmentMapper) {
        this.settingsMapper = settingsMapper;
        this.appointmentMapper = appointmentMapper;
    }

    public CompanyModel toEntity(CompanyDTO companyDTO) {
        CompanyModel companyModel = new CompanyModel();
        companyModel.setId(companyDTO.getId());
        companyModel.setName(companyDTO.getName());
        companyModel.setEmail(companyDTO.getEmail());
        companyModel.setPhone(companyDTO.getPhone());
        companyModel.setAddress(companyDTO.getAddress());

        if (companyDTO.getSettings() != null) {
            SettingsModel settingsModel = settingsMapper.map(companyDTO.getSettings());
            companyModel.setSettings(settingsModel);
        }

        if (companyDTO.getAppointments() != null) {
            List<AppointmentModel> appointmentModels = companyDTO.getAppointments()
                    .stream()
                    .map(appointmentMapper::toEntity)
                    .peek(appointment -> ((AppointmentModel) appointment).setCompany(companyModel))
                    .collect(Collectors.toList());
            companyModel.setAppointments(appointmentModels);
        }

        return companyModel;
    }

    public CompanyDTO toDto(CompanyModel companyModel) {
        CompanyDTO companyDTO = new CompanyDTO();
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
                            .map(appointmentMapper::toDto)
                            .collect(Collectors.toList())
            );
        }

        return companyDTO;
    }
}
