package com.dtu.kolgo.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("Access Denied");
    }

}
