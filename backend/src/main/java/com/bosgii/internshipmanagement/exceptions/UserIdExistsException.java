package com.bosgii.internshipmanagement.exceptions;

public class UserIdExistsException extends Exception{
    public UserIdExistsException(Long id) {
        super("Id: " + id + " exists.");
    }
}
