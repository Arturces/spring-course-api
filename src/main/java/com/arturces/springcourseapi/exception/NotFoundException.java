package com.arturces.springcourseapi.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2459340539081918601L;

    public NotFoundException(String msg){
        super(msg);
    }


}
