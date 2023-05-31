package com.healthbridge.service;

import java.util.List;
import com.healthbridge.entity.Patient;

public interface PatientService {

  Patient getById(Integer patientId);
  
  List<Patient> getAll();
  
  public List<Patient> getByName(String firstName, String lastName);
  
  
}
