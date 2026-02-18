package com.wesleysilva.bappoint.OperatingHours;

import com.wesleysilva.bappoint.OperatingHours.dto.CreateOperatingHoursDTO;
import org.springframework.stereotype.Component;

@Component
public class OperatingHoursMapper {
    public OperatingHoursModel toEntity(CreateOperatingHoursDTO operatingHoursDTO) {
        OperatingHoursModel operatingHoursModel = new OperatingHoursModel();

        operatingHoursModel.setStartTime(operatingHoursDTO.getStartTime());
        operatingHoursModel.setEndTime(operatingHoursDTO.getEndTime());
        operatingHoursModel.setLunchStartTime(operatingHoursDTO.getLunchStartTime());
        operatingHoursModel.setLunchEndTime(operatingHoursDTO.getLunchEndTime());
        operatingHoursModel.setWeekday(operatingHoursDTO.getWeekday());
        operatingHoursModel.setIsActive(operatingHoursDTO.getIsActive());

        return operatingHoursModel;
    }

    public OperatingHoursDTO toDto(OperatingHoursModel operatingHoursModel) {
        OperatingHoursDTO operatingHoursDTO = new OperatingHoursDTO();

        operatingHoursDTO.setId(operatingHoursModel.getId());
        operatingHoursDTO.setStart_time(operatingHoursModel.getStartTime());
        operatingHoursDTO.setEnd_time(operatingHoursModel.getEndTime());
        operatingHoursDTO.setLunch_start_time(operatingHoursModel.getLunchStartTime());
        operatingHoursDTO.setLunch_end_time(operatingHoursModel.getLunchEndTime());
        operatingHoursDTO.setWeekday(operatingHoursModel.getWeekday());
        operatingHoursDTO.setIs_active(operatingHoursModel.getIsActive());

        return operatingHoursDTO;
    }

    public CreateOperatingHoursDTO toCreate(OperatingHoursModel operatingHoursModel) {
        CreateOperatingHoursDTO operatingHoursDTO = new CreateOperatingHoursDTO();

        operatingHoursDTO.setId(operatingHoursModel.getId());
        operatingHoursDTO.setStartTime(operatingHoursModel.getStartTime());
        operatingHoursDTO.setEndTime(operatingHoursModel.getEndTime());
        operatingHoursDTO.setLunchStartTime(operatingHoursModel.getLunchStartTime());
        operatingHoursDTO.setLunchEndTime(operatingHoursModel.getLunchEndTime());
        operatingHoursDTO.setWeekday(operatingHoursModel.getWeekday());
        operatingHoursDTO.setIsActive(operatingHoursModel.getIsActive());

        return operatingHoursDTO;
    }
}
