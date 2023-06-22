package com.kristofer.travelingapi.services.exceptions;

public class ObjectAlreadyExists extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectAlreadyExists(String msg){
        super(msg);
    }
}
