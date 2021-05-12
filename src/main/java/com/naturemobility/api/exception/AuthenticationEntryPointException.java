package com.naturemobility.api.exception;


public class AuthenticationEntryPointException extends RuntimeException {

    public AuthenticationEntryPointException(String msg, Throwable e) {
        super(msg, e);

    }

    public AuthenticationEntryPointException(String msg) {
        super(msg);
    }

    public AuthenticationEntryPointException() {
        super();
    }
}
