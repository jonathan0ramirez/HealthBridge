package com.healthbridge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.healthbridge.dao.PatientDao;
import com.healthbridge.entity.Patient;

@Service
@Transactional(readOnly = true)
public class DefaultPatientService implements PatientService {

  private final PatientDao patientDao;
  
  @Autowired
  public DefaultPatientService(PatientDao patientDao) {
    this.patientDao = patientDao;
  }
  
  @Override
  public Patient getById(Integer patientId) {
    
    return patientDao.getById(patientId);
  }

  @Override
  public List<Patient> getAll() {
    return patientDao.getAll();
  }

}
