package com.healthbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healthbridge.service.LoginService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;

@RestController
@RequestMapping("/login")
@PreAuthorize("hasRole('ROLE_staff')")
@OpenAPIDefinition(info = @Info(title = "Staff Login"), servers = {
    @Server(url = "http://localhost:8080", description = "")})
public class LoginController {

    private final LoginService loginService;
    
    @Autowired
    public LoginController(LoginService loginService) {
      this.loginService = loginService;
    }
    
    @GetMapping
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws InvalidLoginException {
      
      
      try {
        Login validatedLogin = loginService.validateUser(username, password);
      if (validatedLogin != null && validatedLogin.getStaffRole().equals("staff")) {
         
        
        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, "/staff/home")
            .build();
          
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
      } catch(InvalidLoginException e) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        
        }
    }
}
