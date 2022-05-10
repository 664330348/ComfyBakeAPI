package com.revature.comfybake.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("No user found using the provided credentials.");
    }

    public AuthenticationException(String msg){
        super(msg);
    }

}
