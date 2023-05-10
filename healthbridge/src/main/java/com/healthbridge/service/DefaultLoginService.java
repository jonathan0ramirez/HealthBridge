package com.healthbridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthbridge.dao.LoginDao;
import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;

@Service
public class DefaultLoginService implements LoginService {

  @Autowired
  private LoginDao loginDao; //inject the LoginDao
  
  //validate method 
  @Override
  public Login validateUser(String username, String password) throws InvalidLoginException{
    //get the Login from the database with the logindao findbyuser method
    Login login = loginDao.findByUsername(username);

    if (login == null) {
      throw new InvalidLoginException("Username not found");
    }
    String storedPassword = login.getPassword();
    
    if (password.equals(storedPassword)) {
      return login;
    } else {
      throw new InvalidLoginException("Incorrect password");
    }
    
    
  }

}
