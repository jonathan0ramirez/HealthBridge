package com.healthbridge.errorhandler;

public class InvalidLoginException extends Exception {
  
  public InvalidLoginException(String message) {
    super(message);
  }
  
}
