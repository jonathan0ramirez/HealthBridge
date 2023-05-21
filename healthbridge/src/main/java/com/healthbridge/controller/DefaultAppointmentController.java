package com.healthbridge.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healthbridge.entity.Appointment;
import com.healthbridge.entity.AppointmentStatus;
import com.healthbridge.entity.Patient;
import com.healthbridge.entity.Physician;
import com.healthbridge.errorhandler.AppointmentResponse;
import com.healthbridge.service.AppointmentService;
import com.healthbridge.service.PatientService;
import com.healthbridge.service.PhysicianService;

@RestController
@RequestMapping("/staff/home/appointments")
public class DefaultAppointmentController {
  
  @Autowired
  private AppointmentService appointmentService;
  
  @Autowired
  private PhysicianService physicianService;
  
  @Autowired
  private PatientService patientService;
  

  @PostMapping("/newAppointment")
  public ResponseEntity<?> saveAppointment(
      @RequestParam int physicianId,
      @RequestParam int patientId,
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
      @RequestParam String startTimeString,
      @RequestParam String durationString){
      
      String durationToParse;
      if ( durationString.equals("15") || durationString.equals("30") || durationString.equals("45")) {
        durationToParse = "PT" + durationString + "M";
      } else if (durationString.equals("1hour")) {
        durationToParse = "PT1H";
      } else {
        String messageDuration = "Please enter 15 for 15minutes, 30 for 30mins, 45 for 45mins or 1hour";
        AppointmentResponse response = new AppointmentResponse(messageDuration);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
      }
      Duration duration = Duration.parse(durationToParse);
      Physician physician = physicianService.getById(physicianId);
      Patient patient = patientService.getById(patientId);
      Appointment appointment = new Appointment();
      
      LocalTime appointmentStart = LocalTime.parse(startTimeString, DateTimeFormatter.ofPattern("HH:mm"));
      LocalTime appointmentDuration = appointmentStart.plus(duration);
      
      List<Appointment> existingAppointments = appointmentService.getAppointmentsByPhysicianId(physicianId);
    try {  
      for(Appointment existingAppointment : existingAppointments) {
      LocalTime existingStart = existingAppointment.getAppointmentTime();
      LocalTime existingEnd = existingStart.plus(duration);
      
      if(existingAppointment.getAppointmentDate().equals(date) && appointmentStart.isBefore(existingEnd) && existingStart.isBefore(appointmentDuration)) {
        String messageConflictAppointment = "Schedule conflict: Another appointment is already scheduled at the specified time";
        AppointmentResponse response = new AppointmentResponse(messageConflictAppointment);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
      }
    } 
      appointment.setPatient(patient);
      appointment.setPhysician(physician);
      appointment.setAppointmentDate(date);
      appointment.setAppointmentTime(appointmentStart);
      appointment.setAppointmentDuration(appointmentDuration);
      appointment.setStatus(AppointmentStatus.scheduled);
      
      appointmentService.saveAppointment(appointment);
      
      return new ResponseEntity<>(appointment, HttpStatus.CREATED);
  } catch(IllegalArgumentException e) {
    String message = e.getMessage();
    AppointmentResponse response = new AppointmentResponse(message);
   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
  }
  
    @DeleteMapping("/appointments/delete{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id){
      Appointment appointment = appointmentService.getAppointmentById(id);
      
      if (appointment == null) {
        return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
      }
      
      appointmentService.deleteAppointment(appointment);
      
      return new ResponseEntity<>("Appointment deleted successfully", HttpStatus.OK);
      
    }
    
    @PutMapping("/updateAppointment")
    public ResponseEntity<?> updateAppointment(
        @RequestParam int appointmentId,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @RequestParam String startTimeString,
        @RequestParam String durationString){
      
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        if(appointment == null) {
          String noAppointmentMessage = "No appointment found";
          AppointmentResponse response = new AppointmentResponse(noAppointmentMessage);
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        String durationToParse;
        
        if ( durationString.equals("15") || durationString.equals("30") || durationString.equals("45")) {
          durationToParse = "PT" + durationString + "M";
        } else if (durationString.equals("1hour")) {
          durationToParse = "PT1H";
        } else {
          String messageDuration = "Please enter 15 for 15minutes, 30 for 30mins, 45 for 45mins or 1hour";
          AppointmentResponse response = new AppointmentResponse(messageDuration);
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Duration duration = Duration.parse(durationToParse);
        LocalTime appointmentStart = LocalTime.parse(startTimeString, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime appointmentDuration = appointmentStart.plus(duration);
        Physician physician = appointment.getPhysician();
        int physicianId = physician.getId();
        
        
        List<Appointment> existingAppointments = appointmentService.getAppointmentsByPhysicianId(physicianId);
        try {  
          for(Appointment existingAppointment : existingAppointments) {
          LocalTime existingStart = existingAppointment.getAppointmentTime();
          LocalTime existingEnd = existingStart.plus(duration);
          
          if(existingAppointment.getAppointmentDate().equals(date) && appointmentStart.isBefore(existingEnd) && existingStart.isBefore(appointmentDuration)) {
            String messageConflictAppointment = "Schedule conflict: Another appointment is already scheduled at the specified time";
            AppointmentResponse response = new AppointmentResponse(messageConflictAppointment);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
          }
        } 
         
          appointment.setAppointmentDate(date);
          appointment.setAppointmentTime(appointmentStart);
          appointment.setAppointmentDuration(appointmentDuration);
          appointment.setStatus(AppointmentStatus.scheduled);
          
          appointmentService.updateAppointment(appointment);
          
          return new ResponseEntity<>(appointment, HttpStatus.CREATED);
      } catch(IllegalArgumentException e) {
        String message = e.getMessage();
         AppointmentResponse response = new AppointmentResponse(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
      }
      }
    
    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentsById(@PathVariable int appointmentId){
      Appointment appointment = appointmentService.getAppointmentById(appointmentId);
      
      if (appointment != null) {
        return ResponseEntity.ok(appointment);
      } else {
        String message = "No appointment found with the ID provided";
        return ResponseEntity.notFound().build();
      }
    }
    
    @GetMapping("/appointmentsPatient{patientId}")
    public ResponseEntity<AppointmentResponse> getAppointmentsByPatientId(@PathVariable int patientId){
     List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
     if(!appointments.isEmpty()) {
       return ResponseEntity.ok(new AppointmentResponse(appointments));
     } else {
       String message = "No appointment found for the patient or the Patient Id provided is invalid";
       AppointmentResponse response = new AppointmentResponse(message);
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
     }
   }
    
    @GetMapping("/appointmentsPhysician{physicianId}")
    public ResponseEntity<AppointmentResponse> getAppointmentsByPhysicianId(@PathVariable int physicianId){
      List<Appointment> appointments = appointmentService.getAppointmentsByPhysicianId(physicianId);
      if(!appointments.isEmpty()) {
        return ResponseEntity.ok(new AppointmentResponse(appointments));
      } else {
        String message = "No appointment found for the physician or the Physician Id provided is invalid";
        AppointmentResponse response = new AppointmentResponse(message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      }
    }

}
