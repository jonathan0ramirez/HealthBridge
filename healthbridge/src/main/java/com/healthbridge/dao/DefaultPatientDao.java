package com.healthbridge.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    String sql = "SELECT * FROM patient";
    Query query = entityManager.createNativeQuery(sql, Patient.class);
    List<Patient> patients = query.getResultList();
    return patients;
  }

  @Override
  public List<Patient> getByName(String firstName, String lastName) {
    String sql = "SELECT * FROM patient WHERE first_name LIKE :firstname AND last_name LIKE :lastName";
    Query query = entityManager.createNativeQuery(sql, Patient.class);
    query.setParameter("firstname", firstName + "%");
    query.setParameter("lastName", lastName + "%");
    List<Patient> patients = query.getResultList();
    return patients;
  }

}
