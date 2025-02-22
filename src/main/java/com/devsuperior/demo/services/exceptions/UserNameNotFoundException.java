package com.devsuperior.demo.services.exceptions;

public class UserNameNotFoundException extends RuntimeException {

    public UserNameNotFoundException(String msg) {
        super(msg);
    }

}
