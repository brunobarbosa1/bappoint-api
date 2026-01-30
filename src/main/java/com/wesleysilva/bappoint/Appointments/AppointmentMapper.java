package com.wesleysilva.bappoint.Appointments;

import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public AppointmentModel toEntity(AppointmentDTO appointmentsDTO) {
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setId(appointmentsDTO.getId());
        appointmentModel.setCostumerName(appointmentsDTO.getCostumerName());
        appointmentModel.setCostumerEmail(appointmentsDTO.getCostumerEmail());
        appointmentModel.setCostumerPhone(appointmentsDTO.getCostumerPhone());
        appointmentModel.setAppointmentDate(appointmentsDTO.getAppointmentDate());
        appointmentModel.setStartTime(appointmentsDTO.getStartTime());
        appointmentModel.setEndTime(appointmentsDTO.getEndTime());
        appointmentModel.setAppointmentStatus(appointmentsDTO.getAppointmentStatus());

        return appointmentModel;
    }

    public AppointmentDTO toDto(AppointmentModel appointmentModel) {
        AppointmentDTO appointmentsDTO = new AppointmentDTO();
        appointmentsDTO.setId(appointmentModel.getId());
        appointmentsDTO.setCostumerName(appointmentModel.getCostumerName());
        appointmentsDTO.setCostumerEmail(appointmentModel.getCostumerEmail());
        appointmentsDTO.setCostumerPhone(appointmentModel.getCostumerPhone());
        appointmentsDTO.setAppointmentDate(appointmentModel.getAppointmentDate());
        appointmentsDTO.setStartTime(appointmentModel.getStartTime());
        appointmentsDTO.setEndTime(appointmentModel.getEndTime());
        appointmentsDTO.setAppointmentStatus(appointmentModel.getAppointmentStatus());

        return appointmentsDTO;
    }
}
