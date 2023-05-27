package com.bosgii.internshipmanagement.exceptions;

public class UserIdExistsException extends Exception{
    public UserIdExistsException(String errorMessage) {
        super(errorMessage);
    }
}
