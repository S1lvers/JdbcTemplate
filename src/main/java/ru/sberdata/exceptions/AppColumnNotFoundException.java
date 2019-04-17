package ru.sberdata.exceptions;

public class AppColumnNotFoundException extends AppRuntimeException {

    public AppColumnNotFoundException(String message) {
        super(message);
    }

    public AppColumnNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
