package com.enviro.assessment.grad001.santosrafaelo.appexception;

public class BusinessOperationFailedException extends RuntimeException{
    public BusinessOperationFailedException(String message) {
        super(message);
    }
}
