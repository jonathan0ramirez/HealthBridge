package com.healthbridge.service;

import java.util.List;
import com.healthbridge.entity.Physician;

public interface PhysicianService {
  
  public Physician getById(Integer physician_id);
  List<Physician> findByFirstName(String firstName);
  List<Physician> findByLastName(String lastName);
  List<Physician> getAll();
}
