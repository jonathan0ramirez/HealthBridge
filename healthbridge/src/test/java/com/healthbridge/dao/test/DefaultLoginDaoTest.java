package com.healthbridge.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.healthbridge.dao.DefaultLoginDao;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;
import com.healthbridge.service.DefaultLoginService;

@SpringBootTest
class DefaultLoginDaoTest {
  
  
  @Autowired
  private DefaultLoginDao loginDao;
  
  @Autowired
  private DefaultLoginService loginService;
    
  @Test
  void testSave() {
    //Create login with parameters (username, password)
    Login login = new Login("testuser", "testpassword", "staff", "staffId");
    
    //call the method in loginDao to save the login 
    loginDao.save(login);
    
    //Retrieve all the login objects from the database
    List<Login> logins = loginDao.getAllLogins();
    
    //assert that the retrieved login contains the saved login info
    assertTrue(logins.contains(login));
    System.out.println("testSave" + logins.toString());
    
  }

  
  @Test
  void validateLogin() throws InvalidLoginException {
  Login login = new Login ("testuser", "testpassword", "staff", "staffId");
    //assertThat(log.getUsername().equals("testuser"));
    //System.out.println("findByUsername");
    //Login login = loginDao.findByUsername("testuser");
    loginDao.save(login);
    System.out.println(login.getPassword().toString());
 // call the validateUser() method with the correct username and password
    Login validatedLogin = loginService.validateUser("testuser", "testpassword");
 // check if the validatedLogin is not null
    
 // check if the validatedLogin has the same username and password as the original login object
    assertEquals("testuser", validatedLogin.getUsername());
    assertEquals("testpassword", validatedLogin.getPassword());
   
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
  
  
  
//for isolation test enter here to comment out -> /*
  @AfterEach
  void cleanup() {
    loginDao.deleteAll();
    System.out.println("deleted");
  }
  // */
}
