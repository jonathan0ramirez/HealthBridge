package com.healthbridge.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler{

 @ExceptionHandler(InvalidLoginException.class)
 @ResponseStatus(HttpStatus.UNAUTHORIZED)
 public String handleInvalidLoginException() {
   return "redirect:/login?error=invalid";
 }
 
}
