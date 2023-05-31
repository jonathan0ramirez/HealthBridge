package com.healthbridge.dao;

import java.util.List;
import com.healthbridge.entity.Patient;

public interface PatientDao {
  public Patient getById(Integer id);
  public List<Patient> getAll();
  public List<Patient> getByName(String firstName, String lastName);
}
