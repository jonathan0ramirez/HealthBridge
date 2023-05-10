package com.healthbridge.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import com.healthbridge.dao.DefaultLoginDao;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;
import com.healthbridge.login.controller.LoginController;
import com.healthbridge.service.LoginService;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

  private LoginController loginController;
  
  @Autowired
  DefaultLoginDao loginDao;
  
  @Mock
  private LoginService loginService;

  @BeforeEach
  public void setUp() {
    
    //loginController = new LoginController(loginService);
  }
  
  @Test
  public void testValidStaffLogin() throws InvalidLoginException{
      Login login = new Login();
      login.setStaffRole("staff");
      when(loginService.validateUser
          ("testuser", "testpassword")).thenReturn(login);

      //String redirect = loginController.login("testuser", "testpassword");
      //assertEquals("redirect:/staff/home", redirect);

      // Changing the username and password should cause the test to fail
      //String redirect2 = loginController.login("wronguser", "wrongpassword");
      //assertEquals("redirect:/login?error=invalid", redirect2);
  }
  
  //if role is different than staff
  @Test
  public void testInvalidStaffLogin() throws InvalidLoginException{
    Login login = new Login();
    login.setStaffRole("patient");
    when(loginService.validateUser
        ("testuser", "testpassword")).thenReturn(login);
    
    //String redirect = loginController.login("testuser", "testpassword");
    //assertEquals("redirect:/login?error=invalid", redirect);
    
    
    
  }

}
