package com.bosgii.internshipmanagement.exceptions;

public class InvalidMailAddressException extends Exception{
    public InvalidMailAddressException(String errorMessage) {
        super(errorMessage);
    }
    
}
