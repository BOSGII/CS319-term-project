package com.bosgii.internshipmanagement.exceptions;

public class InvalidMailAddressException extends Exception{
    public InvalidMailAddressException(String mail) {
        super(mail + " address is not a valid email address");
    }
    
}
