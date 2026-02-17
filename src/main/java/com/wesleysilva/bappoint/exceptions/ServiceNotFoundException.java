package com.wesleysilva.bappoint.exceptions;

public class ServiceNotFoundException
extends RuntimeException {
    public ServiceNotFoundException() {
        super("Services not found.");
    }
}
