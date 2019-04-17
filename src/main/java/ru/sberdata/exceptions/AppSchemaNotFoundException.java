package ru.sberdata.exceptions;

public class AppSchemaNotFoundException extends AppRuntimeException {

    public AppSchemaNotFoundException(String message) {
        super(message);
    }

    public AppSchemaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}