package com.wesleysilva.bappoint.OffDay;

import com.wesleysilva.bappoint.enums.OffDaysType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffDaysDTO {
    private UUID id;
    private String reason;

    private LocalDate date;
    private OffDaysType offDaysType;

}
