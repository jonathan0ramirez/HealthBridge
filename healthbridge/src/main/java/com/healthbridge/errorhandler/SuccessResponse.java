package com.healthbridge.errorhandler;

public class SuccessResponse {
  public String message; 
  
  public SuccessResponse(String message) {
    this.message = message;
   }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message; 
  }
  
}
