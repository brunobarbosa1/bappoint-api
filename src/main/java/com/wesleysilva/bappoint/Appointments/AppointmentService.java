package com.wesleysilva.bappoint.Appointments;

import com.wesleysilva.bappoint.Appointments.SlotTimes.SlotTimesDTO;
import com.wesleysilva.bappoint.Company.CompanyRepository;
import com.wesleysilva.bappoint.OperatingHours.OperatingHoursModel;
import com.wesleysilva.bappoint.OperatingHours.OperatingHoursRepository;
import com.wesleysilva.bappoint.OperatingHours.OperatingHoursService;
import com.wesleysilva.bappoint.enums.WeekDay;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CompanyRepository companyRepository;
    private final OperatingHoursService operatingHoursService;
    private final OperatingHoursRepository operatingHoursRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, CompanyRepository companyRepository, OperatingHoursService operatingHoursService, OperatingHoursRepository operatingHoursRepository) {
        this.appointmentRepository = appointmentRepository;
        this.companyRepository = companyRepository;
        this.operatingHoursService = operatingHoursService;
        this.operatingHoursRepository = operatingHoursRepository;
    }

    public List<SlotTimesDTO> findAvailableSlots(String dateParams) {

        LocalDate date = LocalDate.parse(dateParams);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        WeekDay weekday = WeekDay.valueOf(dayOfWeek.name());

        List<OperatingHoursModel> hours = operatingHoursRepository
                .findByWeekday(weekday);

        if (hours.isEmpty()) {
            return List.of();
        }

        OperatingHoursModel operatingHours = hours.getFirst();

        LocalDateTime from = operatingHours.getStart_date();
        LocalDateTime to = operatingHours.getEnd_date();

        List<SlotTimesDTO> slots = new ArrayList<>();
        LocalDateTime current = from;

        while (current.isBefore(to)) {
            LocalDateTime next = current.plusMinutes(30);
            slots.add(new SlotTimesDTO(current.toString(), next.toString()));
            current = next;
        }

        return slots;
    }

}
