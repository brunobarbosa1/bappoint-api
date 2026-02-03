package com.wesleysilva.bappoint.Appointments;

import com.wesleysilva.bappoint.Appointments.SlotTimes.SlotTimesDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("company/{companyId}/appointments")
public class AppointmentController {

    AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/available-times")
    public List<SlotTimesDTO> getAvailableTimes(@RequestParam String date) {
        return appointmentService.findAvailableSlots(date);
    }
}
