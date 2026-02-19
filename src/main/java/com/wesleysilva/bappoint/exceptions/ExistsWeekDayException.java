package com.wesleysilva.bappoint.exceptions;

public class ExistsWeekDayException extends RuntimeException {
    public ExistsWeekDayException() {
        super("Weekday already registered.");
    }
}
