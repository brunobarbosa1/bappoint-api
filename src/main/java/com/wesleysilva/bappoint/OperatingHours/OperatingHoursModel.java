package com.wesleysilva.bappoint.OperatingHours;

import com.wesleysilva.bappoint.Settings.SettingsModel;
import com.wesleysilva.bappoint.enums.WeekDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "operating_hours")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatingHoursModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    Boolean is_active;

    LocalTime start_time;
    LocalTime end_time;

    LocalTime lunch_start_time;
    LocalTime lunch_end_time;

    @ManyToOne
    @JoinColumn(name = "settings_id")
    private SettingsModel settings;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday", nullable = false)
    private WeekDay weekday;

}
