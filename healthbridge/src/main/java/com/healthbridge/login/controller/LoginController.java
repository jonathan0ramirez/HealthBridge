package com.healthbridge.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.healthbridge.service.LoginService;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    
    @Autowired
    public LoginController(LoginService loginService) {
      this.loginService = loginService;
    }

    @GetMapping
    public String login(@RequestParam String username, @RequestParam String password) throws InvalidLoginException {
      
      System.out.println(username.toString());
      System.out.println(password.toString());
      try {
        Login validatedLogin = loginService.validateUser(username, password);
      if (validatedLogin != null && validatedLogin.getStaffRole().equals("staff")) {
        System.out.println("inside: " + username.toString());  
        
        return "redirect:/staff/home";
          
      } 
      }
      catch(InvalidLoginException e) {
          return "redirect:/login?error=invalid";
        
        }
        return "redirect:/login?error=invalid";
    
    }
    
  
    
}
