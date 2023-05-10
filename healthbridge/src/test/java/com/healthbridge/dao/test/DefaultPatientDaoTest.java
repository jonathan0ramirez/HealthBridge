package com.healthbridge.dao.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import com.healthbridge.dao.DefaultPatientDao;
import com.healthbridge.entity.Gender;
import com.healthbridge.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@ActiveProfiles("test")
class DefaultPatientDaoTest {
  
  @Autowired
  private DefaultPatientDao patientDao;
  
  @PersistenceContext
  private EntityManager entityManager;
  
  private Patient patient;
  
  @BeforeEach
  public void setup(){
    patient = new Patient();
    patient.setFirstname("Peter");
    patient.setLastName("Parker");
    patient.setDateOfBirth("1990-01-01");
    patient.setRaceEthnicity("White");
    patient.setGender(Gender.male);
    patient.setMailingAddress("123 main st, Queens, NY");
    patient.setResidentialAddress("123 main st, Queens, NY");
    patient.setContactNumber("1234567890");
    entityManager.persist(patient);
    entityManager.flush();
  }
  
  @Test
  @Transactional
  public void testGetById() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    patient = patientDao.getById(1);
    Assertions.assertEquals("Peter", patient.getFirstname());
    Assertions.assertEquals("Parker", patient.getLastName());
    Assertions.assertEquals("1990-01-01", patient.getDateOfBirth().format(formatter));
    Assertions.assertEquals("White", patient.getRaceEthnicity());
    Assertions.assertEquals(Gender.male, patient.getGender());
    Assertions.assertEquals("123 main st, Queens, NY", patient.getMailingAddress());
    Assertions.assertEquals("123 main st, Queens, NY", patient.getResidentialAddress());
    Assertions.assertEquals("1234567890", patient.getContactNumber());
  }
  
    @Test
    @Transactional
    void testGetAllPatients() {
      List<Patient> patients = patientDao.getAll();
      assertNotNull(patients);
      assertTrue(patients.size() > 0);
    }
    
    @Test
    @Transactional
    public void testGetByIdPatientNotFound() {
      patient = patientDao.getById(0);
      Assertions.assertNull(patient);
    }

}
