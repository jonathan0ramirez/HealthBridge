package com.healthbridge.service;

import com.healthbridge.entity.Login;
import com.healthbridge.errorhandler.InvalidLoginException;

public interface LoginService {

    Login validateUser(String username, String password) throws InvalidLoginException;
    
    public void updatePassword(String username, String newPassword) throws InvalidLoginException;

}