package com.example.PatientManagementWeb.Exceptions;

public class InvalidUsername extends RuntimeException {
    public InvalidUsername(String message) {
        super(message);
    }
}
