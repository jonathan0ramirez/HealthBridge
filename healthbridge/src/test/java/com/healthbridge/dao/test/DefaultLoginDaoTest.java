package com.healthbridge.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.healthbridge.dao.DefaultLoginDao;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;
import com.healthbridge.service.DefaultLoginService;

@ExtendWith(MockitoExtension.class)
class DefaultLoginDaoTest {
  
  @Mock
  private DefaultLoginDao loginDao;
  
  @InjectMocks
  private DefaultLoginService loginService;
    
  @Test
  void testValidateLogin() throws InvalidLoginException{
    Login login = new Login("testuser", "testpassword", "staff", "staffId");
 //setup up -> when the findByUsername(username) then it returns the login that was just created
    when(loginDao.findByUsername("testuser")).thenReturn(login);
    
    //this should test the validateUser to validate with (username, password)
    Login validatedLogin = loginService.validateUser("testuser", "testpassword");
    
    assertNotNull(validatedLogin);
    
    assertEquals("testuser", validatedLogin.getUsername());
    assertEquals("testpassword", validatedLogin.getPassword());
    //verify that loginDao findbyUsername was just called once in the method
    verify(loginDao, times(1)).findByUsername("testuser");
  }
  
  @Test
  void testInvalidUsernameOrPassword() {
    //correct user wrong pass
    assertThrows(InvalidLoginException.class, () ->{
      loginService.validateUser("testuser", "wrongpassword");
    });
    //wrong user correct pass
    assertThrows(InvalidLoginException.class, () -> {
      loginService.validateUser("wronguser", "testpassword");
    });
    //case sensitive
    assertThrows(InvalidLoginException.class, () ->{
      loginService.validateUser("TESTuser", "testpassword");
    });
    //case sensitive
    assertThrows(InvalidLoginException.class, () ->{
      loginService.validateUser("testuser", "TESTpassword");
    });
    //null password
    assertThrows(InvalidLoginException.class, () ->{
      loginService.validateUser("testuser", null);
    });
    
  }
  

}
