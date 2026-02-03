package com.wesleysilva.bappoint.enums;

public enum AppointmentInterval {
    MINUTES_15(15),
    MINUTES_45(45),
    MINUTES_30(30),
    MINUTES_60(60);

    private final int minutes;

    AppointmentInterval(long minutes) {
        this.minutes = Math.toIntExact(minutes);
    }

    public int getMinutes() {
        return minutes;
    }
}
