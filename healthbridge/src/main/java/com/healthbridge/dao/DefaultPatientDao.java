package com.healthbridge.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.healthbridge.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

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

  @Override
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
    
  }
  
  
}
