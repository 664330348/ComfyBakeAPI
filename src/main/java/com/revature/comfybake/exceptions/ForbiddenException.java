package com.revature.comfybake.exceptions;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException() {
        super("Your account cannot perform this action at this time");
    }

    public ForbiddenException(String message) {
        super();
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
