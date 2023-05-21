package com.healthbridge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthbridge.dao.PhysicianPatientDao;
import com.healthbridge.entity.Patient;
import com.healthbridge.entity.Physician;

@Service
public class PhysicianPatientService {

  private final PhysicianPatientDao physicianPatientDao;
  
  @Autowired
  public PhysicianPatientService(PhysicianPatientDao physicianPatientDao) {
    this.physicianPatientDao = physicianPatientDao;
  }
  
  public void linkPhysicianAndPatient(Physician physician, Patient patient) {
    physicianPatientDao.linkPhysicianAndPatient(physician, patient);
  }
  
  public void unlinkPhysicianAndPatient(Physician physician, Patient patient) {
    physicianPatientDao.unlinkPhysicianAndPatient(physician, patient);
  }
  
  public List<Physician> getPhysicianByPatient(int patientId){
    return physicianPatientDao.getPhysiciansByPatientId(patientId);
  }
  
  public List<Patient> getPatientsByPhysicianId(int physicianId){
    return physicianPatientDao.getPatientsByPhysicianId(physicianId);
  }
  
}
