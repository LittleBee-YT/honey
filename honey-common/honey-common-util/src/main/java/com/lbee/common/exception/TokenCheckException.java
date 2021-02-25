package com.lbee.common.exception;

public class TokenCheckException extends RuntimeException{

    public TokenCheckException() {
    }

    public TokenCheckException(String message) {
        super(message);
    }

    public TokenCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
