package com.wesleysilva.bappoint.exceptions;

public class SettingsNotFoundException extends RuntimeException {
    public SettingsNotFoundException() {super("Settings not found");}
}
