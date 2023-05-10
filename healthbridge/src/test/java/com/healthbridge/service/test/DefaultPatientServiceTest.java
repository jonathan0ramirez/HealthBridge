package com.healthbridge.service.test;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.healthbridge.dao.PatientDao;
import com.healthbridge.entity.Gender;
import com.healthbridge.entity.Patient;
import com.healthbridge.service.DefaultPatientService;

@ExtendWith(MockitoExtension.class)
class DefaultPatientServiceTest {

  @Mock
  private PatientDao patientDao; 
  
  @InjectMocks
  private DefaultPatientService patientService;
  
  private Patient patient1;
  private Patient patient2; 
  
  @BeforeEach
  public void setup() {
    
    patient1 = new Patient();
    patient1.setId(1);
    patient1.setFirstname("Peter");
    patient1.setLastName("Parker");
    patient1.setDateOfBirth("1990-01-01");
    patient1.setRaceEthnicity("White");
    patient1.setGender(Gender.male);
    patient1.setMailingAddress("123 main st, Queens, NY");
    patient1.setResidentialAddress("123 main st, Queens, NY");
    patient1.setContactNumber("1234567890");
    
    patient2 = new Patient();
    patient2.setFirstname("Bruce");
    patient2.setLastName("Banner");
    patient2.setDateOfBirth("1970-05-14");
    patient2.setRaceEthnicity("Caucasian");
    patient2.setGender(Gender.male);
    patient2.setMailingAddress("456 first st, Brooklyn, NY");
    patient2.setResidentialAddress("456 first st, Brooklyn, NY");
    patient2.setContactNumber("0987654321");
  }
  
    @Test
    public void testGetById() {
      when(patientDao.getById(anyInt())).thenReturn(patient1);
      
      Patient actualPatient = patientService.getById(2);
      
      Assertions.assertEquals(patient1, actualPatient);
    }
  

}
