package com.healthbridge.dao;

import java.time.LocalDate;
import java.util.List;
import com.healthbridge.entity.Appointment;

public interface AppointmentDao {
  
  public void saveAppointment(Appointment appointment);
  
  public void updateAppointment(Appointment appointment);
  
  public void deleteAppointment(Appointment appointment);
  
  public Appointment getAppointmentById(int appointmentId);
  
  public List<Appointment> getAppointmentsByPatientId(int patientId);
  
  public List<Appointment> getAppointmentsByPhysicianId(int physicianId);
  
  public List<Appointment> getAppointmentsByPatientAndPhysicianId(int physicianId, int patientId);
  
  public List<Appointment> getAllAppointmentsByDate(LocalDate date);
}
