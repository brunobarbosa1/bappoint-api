package com.wesleysilva.bappoint.OperatingHours;

import com.wesleysilva.bappoint.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatingHoursDTO {
    private UUID id;
    private WeekDay weekday;
    private Boolean is_active;
    private LocalTime start_time;
    private LocalTime end_time;
    private LocalTime lunch_start_time;
    private LocalTime lunch_end_time;
}
