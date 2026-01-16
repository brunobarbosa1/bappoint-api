package com.wesleysilva.bappoint.enums;

public enum WeekDay {
    SUNDAY("Domingo"),
    MONDAY("Segunda"),
    TUESDAY("Terça"),
    WEDNESDAY("Quarta"),
    THURSDAY("Quinta"),
    FRIDAY("Sexta"),
    SATURDAY("Sábado");

    private final String displayName;

    WeekDays(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
