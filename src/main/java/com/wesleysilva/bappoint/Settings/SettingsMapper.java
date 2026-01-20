package com.wesleysilva.bappoint.Settings;

import org.springframework.stereotype.Component;

@Component
public class SettingsMapper {
    public SettingsModel map(SettingsDTO settingsDTO) {
        SettingsModel settingsModel = new SettingsModel();

        settingsModel.setId(settingsDTO.getId());
        settingsModel.setAppointment_interval(settingsDTO.getAppointment_interval());
        settingsModel.setMax_cancellation_interval(settingsDTO.getMax_cancellation_interval());

        return settingsModel;
    }

    public SettingsDTO map(SettingsModel settingsModel) {

        SettingsDTO settingsDTO = new SettingsDTO();

        settingsDTO.setId(settingsModel.getId());
        settingsDTO.setAppointment_interval(settingsModel.getAppointment_interval());
        settingsDTO.setMax_cancellation_interval(settingsModel.getMax_cancellation_interval());

        return settingsDTO;
    }
}
