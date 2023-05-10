package com.healthbridge.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.healthbridge.dao.LoginDao;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;
import com.healthbridge.service.DefaultLoginService;

@ExtendWith(MockitoExtension.class)
class DefaultLoginServiceTest {

  @Mock
  private LoginDao loginDao;
  
  @InjectMocks
  private DefaultLoginService loginService;
  
  @Test
  public void testValidateUser() throws InvalidLoginException{
    String username = "testuser";
    String password = "testpassword";
    
    Login login = new Login();
    login.setUsername(username);
    login.setPassword(password);
    
    when(loginDao.findByUsername(anyString())).thenReturn(login);
    
    Login actualLogin = loginService.validateUser(username, password);
    
    assertEquals(login, actualLogin);
    
  }
  
  @Test
  public void testInvalidUsername() {
    String username = "wronguser";
    String password = "testpassword";
    
    
    when(loginDao.findByUsername(anyString())).thenReturn(null);
    
    try {
      loginService.validateUser(username, password);
    } catch (InvalidLoginException e) {
      assertEquals("Username not found", e.getMessage());
    }
    
  }
  
  @Test
  public void testIncorrectPassword() {
    String username = "testuser";
    String password = "testpassword";
    
    Login login = new Login();
    login.setUsername(username);
    login.setPassword(password);
    
    when(loginDao.findByUsername(anyString())).thenReturn(login);
    
  }

}
