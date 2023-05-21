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
import com.healthbridge.entity.Physician;
import com.healthbridge.errorhandler.ErrorResponse;
import com.healthbridge.errorhandler.PhysicianNotFoundException;
import com.healthbridge.service.PatientService;
import com.healthbridge.service.PhysicianService;

@RestController
@RequestMapping("staff/home/physicians")
public class DefaultPhysicianController {
  
 private final PhysicianService physicianService;
  
  @Autowired
  public DefaultPhysicianController(PhysicianService physicianService) {
    this.physicianService = physicianService;
   }
  
  @GetMapping("/firstName/{firstName}")
  public ResponseEntity<?> getPhysicianByFirstName(@PathVariable String firstName){
    try {
      List<Physician> physician = physicianService.findByFirstName(firstName);
      if (!physician.isEmpty()) {
        return ResponseEntity.ok(physician);
      } else {
        
        throw new PhysicianNotFoundException("No matches found");
      }
    } catch (PhysicianNotFoundException e) {
      
      ErrorResponse errorResponse = new ErrorResponse("No matches found");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
  }
  
  @GetMapping("/lastName/{lastName}")
  public ResponseEntity<?> getPhysicianByLastName(@PathVariable String lastName){
    try {
      List<Physician> physician = physicianService.findByLastName(lastName);
      if (!physician.isEmpty()) {
        return ResponseEntity.ok(physician);
      } else {
        
        throw new PhysicianNotFoundException("No matches found");
      }
    } catch (PhysicianNotFoundException e) {
      
      ErrorResponse errorResponse = new ErrorResponse("No matches found");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
  }
  
  @GetMapping("/getall")
  public ResponseEntity<List<Physician>> getAllPhysician(){
    List<Physician> physician = physicianService.getAll();
    return ResponseEntity.ok(physician);
  }
  
}
