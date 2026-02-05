package com.wesleysilva.bappoint.Availability;

import com.wesleysilva.bappoint.Appointments.AppointmentModel;
import com.wesleysilva.bappoint.Appointments.AppointmentRepository;
import com.wesleysilva.bappoint.OffDay.OffDaysModel;
import com.wesleysilva.bappoint.OffDay.OffDaysRepository;
import com.wesleysilva.bappoint.OperatingHours.OperatingHoursModel;
import com.wesleysilva.bappoint.OperatingHours.OperatingHoursRepository;
import com.wesleysilva.bappoint.Services.ServiceModel;
import com.wesleysilva.bappoint.Settings.SettingsDTO;
import com.wesleysilva.bappoint.Settings.SettingsService;
import com.wesleysilva.bappoint.enums.AppointmentInterval;
import com.wesleysilva.bappoint.enums.WeekDay;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SlotsTimesService {
    private final AppointmentRepository appointmentRepository;
    private final OperatingHoursRepository operatingHoursRepository;
    private final SettingsService settingsService;
    private final OffDaysRepository offDaysRepository;

    public SlotsTimesService(AppointmentRepository appointmentRepository, OperatingHoursRepository operatingHoursRepository, SettingsService settingsService, OffDaysRepository offDaysRepository) {
        this.appointmentRepository = appointmentRepository;
        this.operatingHoursRepository = operatingHoursRepository;
        this.settingsService = settingsService;
        this.offDaysRepository = offDaysRepository;
    }

    public List<SlotTimesDTO> findAvailableSlots(UUID companyId, String dateParams) {
        LocalDate date = LocalDate.parse(dateParams);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        WeekDay weekday = WeekDay.valueOf(dayOfWeek.name());

        //check if there is off day in the date
        List<OffDaysModel> offDays = offDaysRepository.findByDate(date);
        if(!offDays.isEmpty()) {
            return List.of();
        }

        SettingsDTO settings = settingsService.getByCompanyId(companyId);
        AppointmentInterval appointmentInterval = settings.getAppointment_interval();

        //check if hours of the day is empty means day off
        List<OperatingHoursModel> hours = operatingHoursRepository.findByWeekday(weekday);
        if (hours.isEmpty()) {
            return List.of();
        }

        //check is there is any appointment for the date
        List<AppointmentModel> bookedAppointments = appointmentRepository
                .findByAppointmentDateAndCompanyId(date, companyId);

        OperatingHoursModel operatingHours = hours.getFirst();
        List<SlotTimesDTO> slots = generateSlots(operatingHours, date, appointmentInterval.getMinutes());

        return slots.stream()
                .filter(slot -> !isSlotOccupied(slot, bookedAppointments, date))
                .toList();
    }

    private List<SlotTimesDTO> generateSlots(OperatingHoursModel operatingHours, LocalDate date, int intervalMinutes) {
        LocalDateTime from = date.atTime(operatingHours.getStart_time());
        LocalDateTime to = date.atTime(operatingHours.getEnd_time());

        LocalTime lunchStart = operatingHours.getLunch_start_time();
        LocalTime lunchEnd = operatingHours.getLunch_end_time();

        List<SlotTimesDTO> slots = new ArrayList<>();
        LocalDateTime current = from;

        while (!current.plusMinutes(intervalMinutes).isAfter(to)) {
            LocalDateTime next = current.plusMinutes(intervalMinutes);

            //skip lunchtime
            if (lunchStart != null && lunchEnd != null) {
                LocalDateTime lunchFrom = date.atTime(lunchStart);
                LocalDateTime lunchTo = date.atTime(lunchEnd);

                boolean intersectsLunch =
                        current.isBefore(lunchTo) && next.isAfter(lunchFrom);

                if (intersectsLunch) {
                    current = lunchTo; //go to end of lunch
                    continue;
                }
            }

            slots.add(new SlotTimesDTO(current.toString(), next.toString()));
            current = next;
        }
        return slots;
    }

    //check if the slot is occupied
    private boolean isSlotOccupied(SlotTimesDTO slot, List<AppointmentModel> booked, LocalDate date) {
        LocalDateTime slotStart = LocalDateTime.parse(slot.getStart());
        LocalDateTime slotEnd = LocalDateTime.parse(slot.getEnd());

        return booked.stream().anyMatch(appointment -> {
            if (appointment.getServices() == null || appointment.getServices().isEmpty()) {
                return false;
            }

            int totalDuration = appointment.getServices().stream()
                    .mapToInt(ServiceModel::getDuration_minutes)
                    .sum();

            LocalDateTime appointmentStart = appointment.getStartTime();
            LocalDateTime appointmentEnd = appointmentStart.plusMinutes(totalDuration);

            return !(slotEnd.isBefore(appointmentStart) || slotStart.isAfter(appointmentEnd));
        });
    }
}
