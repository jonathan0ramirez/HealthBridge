package com.healthbridge.service;

import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthbridge.dao.AppointmentDao;
import com.healthbridge.entity.Appointment;

@Service
@Transactional
public class DefaultAppointmentService implements AppointmentService {

  @Autowired
  private AppointmentDao appointmentDao;
  
  
  @Override
  public void saveAppointment(Appointment appointment) {
    appointmentDao.saveAppointment(appointment);
  }

  @Override
  public void updateAppointment(Appointment appointment) {
    appointmentDao.updateAppointment(appointment);
  }

  @Override
  public void deleteAppointment(Appointment appointment) {
    appointmentDao.deleteAppointment(appointment);
  }

  @Override
  public Appointment getAppointmentById(int appointmentId) {
    
    return appointmentDao.getAppointmentById(appointmentId);
  }

  @Override
  public List<Appointment> getAppointmentsByPatientId(int patientId) {
    
    return appointmentDao.getAppointmentsByPatientId(patientId);
  }

  @Override
  public List<Appointment> getAppointmentsByPhysicianId(int physicianId) {
    
    return appointmentDao.getAppointmentsByPhysicianId(physicianId);
  }

  @Override
  public List<Appointment> getAppointmentsByPatientAndPhysicianId(int physicianId, int patientId) {
    
    return appointmentDao.getAppointmentsByPatientAndPhysicianId(physicianId, patientId);
  }

  @Override
  public List<Appointment> getAllAppointmentsByDate(LocalDate date) {
    
    return appointmentDao.getAllAppointmentsByDate(date);
  }

}
