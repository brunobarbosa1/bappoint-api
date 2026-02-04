package com.wesleysilva.bappoint.Appointments;

import com.wesleysilva.bappoint.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private UUID id;

    private List<UUID> serviceIds;

    private String costumerName;
    private String costumerEmail;
    private String costumerPhone;

    private LocalDateTime startTime;

    private AppointmentStatus appointmentStatus;
}
