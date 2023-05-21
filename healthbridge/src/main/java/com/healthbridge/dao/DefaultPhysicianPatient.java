package com.healthbridge.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.healthbridge.entity.Patient;
import com.healthbridge.entity.Physician;

@Repository
@Transactional
public class DefaultPhysicianPatient implements PhysicianPatientDao {

  @PersistenceContext
  private EntityManager entityManager;
  
  @Override
  public void linkPhysicianAndPatient(Physician physician, Patient patient) {
   physician.getPatients().add(patient);
   patient.getPhysicians().add(physician);
   entityManager.merge(physician);
   entityManager.merge(patient);
  }

  @Override
  public void unlinkPhysicianAndPatient(Physician physician, Patient patient) {
    physician.getPatients().remove(patient);
    patient.getPhysicians().remove(physician);
    entityManager.merge(physician);
    entityManager.merge(patient);
  }

  @Override
  public List<Physician> getPhysiciansByPatientId(int patientId) {
    Patient patient = entityManager.find(Patient.class, patientId);
    if (patient != null) {
      return patient.getPhysicians();
    }
    return null;
  }

  @Override
  public List<Patient> getPatientsByPhysicianId(int physicianId) {
    Physician physician = entityManager.find(Physician.class, physicianId);
    if(physician != null) {
      return physician.getPatients();
    }
    return null;
  }

}
