package com.arturces.springcourseapi.exception;


public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2459340539081918601L;

    public NotFoundException(String msg){
        super(msg);
    }


}
