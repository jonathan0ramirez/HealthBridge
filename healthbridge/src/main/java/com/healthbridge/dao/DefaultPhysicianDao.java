package com.healthbridge.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.healthbridge.entity.Physician;

@Repository
public class DefaultPhysicianDao implements PhysicianDao {
  
  @PersistenceContext
  EntityManager entityManager;
  
  @Transactional
  @Override
  public List<Physician> findByFirstName(String firstName) {
    String sql = "SELECT * FROM physician WHERE first_name LIKE :firstname";
    Query query = entityManager.createNativeQuery(sql, Physician.class);
    query.setParameter("firstname", firstName + "%");
    List<Physician> byFirstName = query.getResultList();
    
    return byFirstName;
  }
  @Transactional
  @Override
  public List<Physician> findByLastName(String lastName) {
    String sql = "SELECT * FROM physician WHERE last_name LIKE :lastname";
    Query query = entityManager.createNativeQuery(sql, Physician.class);
    query.setParameter("lastname", lastName + "%");
    List<Physician> byLastName = query.getResultList();
    return byLastName;
  }
  
  @Transactional
  @Override
  public List<Physician> getAll() {
    String sql = "SELECT * FROM physician";
    Query query = entityManager.createNativeQuery(sql, Physician.class);
    List<Physician> getAll = query.getResultList();
    return getAll;
  }
  
  @Transactional
  @Override
  public Physician getById(Integer physician_id) {
    Physician physician = entityManager.find(Physician.class, physician_id);
    if (physician == null) {
    return null;
  } 
    return physician;
  }

}
