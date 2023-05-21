package com.healthbridge.dao;

import java.util.List;
import com.healthbridge.entity.Physician;

public interface PhysicianDao {
  
  public Physician getById(Integer physician_id);
  List<Physician> findByFirstName(String firstName);
  List<Physician> findByLastName(String lastName);
  List<Physician> getAll();
  
  
}
