package com.healthbridge.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.healthbridge.controller.LoginController;
import com.healthbridge.dao.DefaultLoginDao;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;
import com.healthbridge.service.LoginService;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {
  
  @Mock
  private LoginService loginService;

  @InjectMocks
  private LoginController loginController;
  
  @Test
  void testLogin() throws InvalidLoginException{
    String username = "testuser";
    String password = "testpassword";
    Login login = new Login();
    login.setStaffRole("staff");
    
    when(loginService.validateUser(username, password)).thenReturn(login);
    
    ResponseEntity<String> response = loginController.login(username, password);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Redirect to staff home page", response.getBody());
    
  }
  
  @Test
  void testLoginWithInvalidCredentials() throws InvalidLoginException{
    String username = "testuser";
    String password = "testpassword";
    
    when(loginService.validateUser(username, password))
    .thenThrow(new InvalidLoginException("Invalid Credentials"));
    
    ResponseEntity<String> response = loginController.login(username, password);
    
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    assertEquals("Invalid username or password", response.getBody());
    
  }
  

}
