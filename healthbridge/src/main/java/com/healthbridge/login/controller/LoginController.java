package com.healthbridge.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healthbridge.service.LoginService;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;

@RestController
@RequestMapping("/login/staff")
public class LoginController {

    private final LoginService loginService;
    
    @Autowired
    public LoginController(LoginService loginService) {
      this.loginService = loginService;
    }
    
    @GetMapping
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws InvalidLoginException {
      
      System.out.println(username.toString());
      System.out.println(password.toString());
      try {
        Login validatedLogin = loginService.validateUser(username, password);
      if (validatedLogin != null && validatedLogin.getStaffRole().equals("staff")) {
        System.out.println("inside: " + username.toString());  
        
        return ResponseEntity.ok("Redirect to staff home page");
          
      } 
      }
      catch(InvalidLoginException e) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        
        }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
      
    }
    
  
    
}
