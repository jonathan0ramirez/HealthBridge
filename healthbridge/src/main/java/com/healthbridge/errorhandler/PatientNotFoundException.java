package com.healthbridge.errorhandler;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(String message) {
      super(message);
    }
}
