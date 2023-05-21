package com.healthbridge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.healthbridge.dao.PhysicianDao;
import com.healthbridge.entity.Physician;

@Service
@Transactional(readOnly = true)
public class DefaultPhysicianService implements PhysicianService {
  
  private final PhysicianDao physicianDao;
  
  @Autowired
  public DefaultPhysicianService(PhysicianDao physicianDao) {
    this.physicianDao = physicianDao;
  }
  
  @Override
  public List<Physician> findByFirstName(String firstName) {
    
    return physicianDao.findByFirstName(firstName);
  }

  @Override
  public List<Physician> findByLastName(String lastName) {
    
    return physicianDao.findByLastName(lastName);
  }

  @Override
  public List<Physician> getAll() {
   
    return physicianDao.getAll();
  }

  @Override
  public Physician getById(Integer physician_id) {
    
    return physicianDao.getById(physician_id);
  }

}
