package com.healthbridge.dao;

import java.util.List;
import com.healthbridge.entity.Login;

public interface LoginDao {
  
  public void save (Login login);
  
  public void updatePassword(String username, String password);
  
  public List<Login> getAllLogins();
  
  public Login findByUsername(String username);
}
