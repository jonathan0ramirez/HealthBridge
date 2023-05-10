package com.healthbridge.dao;

import java.util.List;
import com.healthbridge.entity.Patient;
import jakarta.persistence.EntityManager;

public interface PatientDao {
  public Patient getById(Integer id);
  public List<Patient> getAll();
  public void setEntityManager(EntityManager entityManager);
  
}
