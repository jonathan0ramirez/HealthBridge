package com.healthbridge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.healthbridge.entity.Patient;
import com.healthbridge.errorhandler.ErrorResponse;
import com.healthbridge.errorhandler.PatientNotFoundException;
import com.healthbridge.service.PatientService;

@RestController
@RequestMapping("/staff/home/patients")
public class DefaultPatientController {

  private final PatientService patientService;
  
  @Autowired
  public DefaultPatientController(PatientService patientService) {
    this.patientService = patientService;
   }
  
  
  @GetMapping("/{id}")
  public ResponseEntity<?> getPatientById(@PathVariable("id") Integer patientId){
    try {
    Patient patient = patientService.getById(patientId);
    if (patient != null) {
      return ResponseEntity.ok(patient);
    } else {
      throw new PatientNotFoundException("Invalid patient Id Supplied");
    }
  } catch (PatientNotFoundException e) {
    
    ErrorResponse errorResponse = new ErrorResponse("Invalid patient Id Supplied");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }
  }
    
  
  @GetMapping("/getall")
  public ResponseEntity<List<Patient>> getAllPatients(){
    List<Patient> patients = patientService.getAll();
    return ResponseEntity.ok(patients);
  }
  
}
