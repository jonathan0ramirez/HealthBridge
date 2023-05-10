package com.healthbridge.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.healthbridge.entity.Patient;

@Repository
public class DefaultPatientDao implements PatientDao {

  
  @PersistenceContext
  private EntityManager entityManager;
  
  @Transactional
  @Override
  public Patient getById(Integer patient_id) {
    Patient patient = entityManager.find(Patient.class, patient_id);
    if ( patient == null) {
      return null;
    }
   return patient;
  }
  
  @Transactional
  @Override
  public List<Patient> getAll() {
    return entityManager.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
  }


  
  
}
