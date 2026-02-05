package com.wesleysilva.bappoint.OperatingHours;

import org.springframework.stereotype.Component;

@Component
public class OperatingHoursMapper {
    public OperatingHoursModel toEntity(OperatingHoursDTO operatingHoursDTO) {
        OperatingHoursModel operatingHoursModel = new OperatingHoursModel();

        operatingHoursModel.setId(operatingHoursDTO.getId());
        operatingHoursModel.setStart_time(operatingHoursDTO.getStart_time());
        operatingHoursModel.setEnd_time(operatingHoursDTO.getEnd_time());
        operatingHoursModel.setLunch_start_time(operatingHoursDTO.getLunch_start_time());
        operatingHoursModel.setLunch_end_time(operatingHoursDTO.getLunch_end_time());
        operatingHoursModel.setWeekday(operatingHoursDTO.getWeekday());
        operatingHoursModel.setIs_active(operatingHoursDTO.getIs_active());

        return operatingHoursModel;
    }

    public OperatingHoursDTO toDto(OperatingHoursModel operatingHoursModel) {
        OperatingHoursDTO operatingHoursDTO = new OperatingHoursDTO();

        operatingHoursDTO.setId(operatingHoursModel.getId());
        operatingHoursDTO.setStart_time(operatingHoursModel.getStart_time());
        operatingHoursDTO.setEnd_time(operatingHoursModel.getEnd_time());
        operatingHoursDTO.setLunch_start_time(operatingHoursModel.getLunch_start_time());
        operatingHoursDTO.setLunch_end_time(operatingHoursModel.getLunch_end_time());
        operatingHoursDTO.setWeekday(operatingHoursModel.getWeekday());
        operatingHoursDTO.setIs_active(operatingHoursModel.getIs_active());

        return operatingHoursDTO;
    }
}
