package com.wesleysilva.bappoint.exceptions;

public class AppointmentDeleteException extends RuntimeException{
    public AppointmentDeleteException() {super("Error deleting appointment.");}
}
