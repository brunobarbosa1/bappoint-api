package com.wesleysilva.bappoint.exceptions;

public class AppointmentQueryException extends RuntimeException {
    public AppointmentQueryException() {
        super("Failed to list appointments.");
    }
}
