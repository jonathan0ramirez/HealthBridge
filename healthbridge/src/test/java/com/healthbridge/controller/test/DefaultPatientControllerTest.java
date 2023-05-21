package com.healthbridge.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.healthbridge.controller.DefaultPatientController;
import com.healthbridge.entity.Patient;
import com.healthbridge.errorhandler.PatientNotFoundException;
import com.healthbridge.service.PatientService;
@ExtendWith(MockitoExtension.class)
class DefaultPatientControllerTest {

  @Mock
  private PatientService patientService;
  
  @InjectMocks
  private DefaultPatientController controller;
  
  @Test
  public void getPatientByIdExistingPatientReturnsPatient() {
    
    Integer patientId = 1;
    Patient patient = new Patient();
    patient.setId(patientId);
    when(patientService.getById(patientId)).thenReturn(patient);
    
    ResponseEntity<?> response = controller.getPatientById(patientId);
    
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(patient);
  }
    
  @Test
  public void getPatientByIdInvalidPatientIdSuppliedThrowsPatientNotFound() {
    Integer patientId = 1;
    Patient patient = new Patient();
    when(patientService.getById(patientId)).thenReturn(null);
    
    assertThatThrownBy(() -> controller.getPatientById(patientId))
      .isInstanceOf(PatientNotFoundException.class)
      .hasMessage("Invalid patient Id Supplied");
  }

}
