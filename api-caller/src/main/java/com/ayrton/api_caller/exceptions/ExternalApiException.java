package com.ayrton.api_caller.exceptions;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
