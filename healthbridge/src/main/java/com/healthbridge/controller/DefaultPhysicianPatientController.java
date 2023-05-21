package com.healthbridge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healthbridge.dao.PatientDao;
import com.healthbridge.dao.PhysicianDao;
import com.healthbridge.dao.PhysicianPatientDao;
import com.healthbridge.entity.Patient;
import com.healthbridge.entity.Physician;
import com.healthbridge.errorhandler.ErrorResponse;
import com.healthbridge.errorhandler.SuccessResponse;
import com.healthbridge.service.PhysicianPatientService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/physician-patient")
public class DefaultPhysicianPatientController {

  private final PhysicianPatientDao physicianPatientDao;
  private final PhysicianDao physicianDao;
  private final PatientDao patientDao;
  
  @Autowired
  public DefaultPhysicianPatientController
      (PhysicianPatientDao physicianPatientDao, 
          PhysicianDao physicianDao, PatientDao patientDao ) {
    this.physicianPatientDao = physicianPatientDao;
    this.physicianDao = physicianDao;
    this.patientDao = patientDao;
  }
  
  @PostMapping("/linkPhysician-Patient")
  public ResponseEntity<?> linkPhysicianAndPatient(@RequestParam("physicianId") int physicianId, @RequestParam("patientId") int patientId){
    Physician physician = physicianDao.getById(physicianId);
    Patient patient = patientDao.getById(patientId);
    
    if(physician == null || patient == null) {
      ErrorResponse errorResponse = new ErrorResponse("Invalid physician or patient Id");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    if(patient.getPhysicians().contains(physician) || physician.getPatients().contains(patient)) {
      ErrorResponse errorResponse = new ErrorResponse("Physician is already assigned to the patient");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    physicianPatientDao.linkPhysicianAndPatient(physician, patient);
    SuccessResponse successResponse = new SuccessResponse("patient " + patient.getFirstname() + " " + patient.getLastName() + " has now been assigned to physician: " + physician.getFirstName() + " " + physician.getLastName());
    return ResponseEntity.ok().body(successResponse);
  }
  
  @PostMapping("/unlinkPhysician-Patient")
  public ResponseEntity<?> unlinkPhysicianAndPatient(@RequestParam("physicianId") int physicianId, @RequestParam("patientId") int patientId){
    Physician physician = physicianDao.getById(physicianId);
    Patient patient = patientDao.getById(patientId);
    
    if(physician == null || patient == null) {
      ErrorResponse errorResponse = new ErrorResponse("Invalid physician or patient Id");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    physicianPatientDao.unlinkPhysicianAndPatient(physician, patient);
    SuccessResponse successResponse = new SuccessResponse("patient " + patient.getFirstname() + " " + patient.getLastName() + " has been unassigned to physician: " + physician.getFirstName() + " " + physician.getLastName());
    return ResponseEntity.ok().body(successResponse);
  }
   
  @PostMapping("/physiciansOfPatient")
  public ResponseEntity<?> getPhysiciansOfPatientByPatientId(int patientId) {
    List<Physician> physicians = physicianPatientDao.getPhysiciansByPatientId(patientId);
    if (physicians == null || physicians.isEmpty()) {
      ErrorResponse errorResponse = new ErrorResponse("No physician assigned to this patient or the Id is not valid");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    return ResponseEntity.ok().body(physicians);
  }
  
  @PostMapping("/patientsOfPhysicians")
  public ResponseEntity<?> getPatientsOfPhysicianByPhysicianId(int physicianId){
    List<Patient> patients = physicianPatientDao.getPatientsByPhysicianId(physicianId);
    if(patients == null || patients.isEmpty()) {
      ErrorResponse errorResponse = new ErrorResponse("No patient assigned to this physician or the Physician Id is not valid");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
      
      return ResponseEntity.ok().body(patients);
  }
}
