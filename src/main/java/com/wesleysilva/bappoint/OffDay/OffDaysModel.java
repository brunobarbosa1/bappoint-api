package com.wesleysilva.bappoint.OffDay;

import com.wesleysilva.bappoint.Settings.SettingsModel;
import com.wesleysilva.bappoint.enums.OffDaysType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "off_days")
public class OffDaysModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settings_id")
    private SettingsModel settings;

    private String reason;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "off_days_type")
    private OffDaysType offDaystype;
}
