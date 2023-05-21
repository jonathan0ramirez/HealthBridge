package com.healthbridge.dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.healthbridge.entity.Appointment;
import lombok.Data;


@Repository
@Transactional
@Data
public class DefaultAppointmentDao implements AppointmentDao {

  @PersistenceContext
  EntityManager entityManager; 
  
  
  @Override
  public void saveAppointment(Appointment appointment) {
    entityManager.persist(appointment);
  }

  @Override
  public void updateAppointment(Appointment appointment) {
    entityManager.merge(appointment);
  }

  @Override
  public void deleteAppointment(Appointment appointment) {
    String jpql = "DELETE FROM Appointment WHERE id = :appointmentId";
    Query query = entityManager.createQuery(jpql);
    
    query.setParameter("appointmentId", appointment.getId());
    query.executeUpdate();
  }

  @Override
  public Appointment getAppointmentById(int appointmentId) {
      return entityManager.find(Appointment.class, appointmentId);
  }

  @Override
  public List<Appointment> getAppointmentsByPatientId(int patientId) {
    String sql = "SELECT * FROM appointment WHERE patient_id = :patientid";
    Query query = entityManager.createNativeQuery(sql, Appointment.class);
    query.setParameter("patientid", patientId);
    List<Appointment> byPatientId = query.getResultList();
    return byPatientId;
  }

  @Override
  public List<Appointment> getAppointmentsByPhysicianId(int physicianId) {
    String sql = "SELECT * FROM appointment WHERE physician_id = :physicianid";
    Query query = entityManager.createNativeQuery(sql, Appointment.class);
    query.setParameter("physicianid", physicianId);
    List<Appointment> byPhysicianId = query.getResultList();
    return byPhysicianId;
  }

  @Override
  public List<Appointment> getAllAppointmentsByDate(LocalDate date) {
    String sql = "SELECT * FROM appointment WHERE DATE(appointment_date) = :appointmentdate";
    Query query = entityManager.createNativeQuery(sql, Appointment.class);
    query.setParameter("appointmentdate", date.toString());
    List<Appointment> appointmentByDate = query.getResultList();
    return appointmentByDate;
  }

  @Override
  public List<Appointment> getAppointmentsByPatientAndPhysicianId(int physicianId, int patientId) {
    String sql = "SELECT * FROM appointment WHERE physician_id = :physicianid AND patient_id = :patientid";
    Query query = entityManager.createNativeQuery(sql, Appointment.class);
    query.setParameter("physicianid", physicianId);
    query.setParameter("patientid", patientId);
    List<Appointment> byPatientAndPhysicianId = query.getResultList();
    
    return byPatientAndPhysicianId;
  }

}
