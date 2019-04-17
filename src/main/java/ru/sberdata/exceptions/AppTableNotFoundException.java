package ru.sberdata.exceptions;

/**
 * UNCHECKED
 */
public class AppTableNotFoundException extends AppRuntimeException {

    public AppTableNotFoundException(String message) {
        super(message);
    }

    public AppTableNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}