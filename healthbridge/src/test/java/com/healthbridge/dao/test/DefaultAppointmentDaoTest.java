package com.healthbridge.dao.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.healthbridge.dao.DefaultAppointmentDao;
import com.healthbridge.entity.Appointment;
import com.healthbridge.entity.AppointmentStatus;
import com.healthbridge.entity.Patient;
import com.healthbridge.entity.Physician;

@ExtendWith(MockitoExtension.class)
class DefaultAppointmentDaoTest {

  @InjectMocks
  DefaultAppointmentDao appointmentDao;
  
  @Mock
  EntityManager entityManager;
  
  private Appointment appointment;
  private Patient patient;
  private Physician physician;
  
  @BeforeEach
  void setup() {
    appointment = new Appointment();
    appointment.setId(1);
    appointment.setAppointmentDate(LocalDate.of(2023, 1, 1));
    appointment.setAppointmentTime(LocalTime.of(9, 0));
    appointment.setStatus(AppointmentStatus.scheduled);
    
    patient = new Patient();
    patient.setId(1);
    appointment.setPatient(patient);
    
    physician = new Physician();
    physician.setId(1);
    appointment.setPhysician(physician);
  }
    @Test
    public void saveAppointmentTest(){
      doNothing().when(entityManager).persist(appointment);
      
      assertNotNull(appointment);
      appointmentDao.saveAppointment(appointment);
      verify(entityManager, times(1)).persist(appointment);
    }
    
    @Test
    public void testUpdateAppointment(){
      appointmentDao.updateAppointment(appointment);
      //Argumentcaptor captures Appointment Object that is passed on the .merge
      ArgumentCaptor<Appointment> appointmentCaptor = ArgumentCaptor.forClass(Appointment.class);
      verify(entityManager).merge(appointmentCaptor.capture());
      //the captured object can be obtained .getValue then compare
      Appointment capturedAppointment = appointmentCaptor.getValue();
      assertEquals(appointment, capturedAppointment);
    }
    
    @Test
    public void deleteAppointmentTest() {
      Query query = Mockito.mock(Query.class);
      
      Mockito
      .when(entityManager
          .createQuery(Mockito.anyString()))
            .thenReturn(query);      
      Mockito
      .when(query
          .setParameter(Mockito.anyString(), Mockito.anyInt()))
            .thenReturn(query);
      Mockito
      .when(query
          .executeUpdate())
            .thenReturn(1);
     
      appointmentDao.deleteAppointment(appointment);
        
      Mockito.verify(entityManager)
      .createQuery(Mockito.eq("DELETE FROM appointment a WHERE a.id = :appointmentId"));
      Mockito.verify(query).setParameter("appointmentId", 1);
      Mockito.verify(query).executeUpdate();
      
    }
    
    @Test
    public void getAppointmentByIdTest() {
      when(entityManager.find(Appointment.class, 1)).thenReturn(appointment);
      
      Appointment result = appointmentDao.getAppointmentById(1);
      
      assertEquals(appointment, result);
      verify(entityManager).find(Appointment.class, 1);
      
    }
    
    @Test
    public void testAppointmentsByPatientId() {
      Query query = Mockito.mock(Query.class);
      
      List<Appointment> resultList = new ArrayList<>();
      resultList.add(appointment);
      
      Mockito
        .when(entityManager
          .createNativeQuery(Mockito.anyString(), Mockito.eq(Appointment.class)))
            .thenReturn(query);
      Mockito
        .when(query
          .setParameter(Mockito.anyString(), Mockito.anyInt()))
            .thenReturn(query);
      Mockito.when(query.getResultList()).thenReturn(resultList);
      
      List<Appointment> appointments = appointmentDao.getAppointmentsByPatientId(1);
      assertThat(appointments).isNotNull().hasSize(1);
      Appointment resultAppointment = appointments.get(0);
      assertThat(resultAppointment.getId()).isEqualTo(1);
      assertThat(resultAppointment.getPatient().getId()).isEqualTo(1);   
      assertThat(resultAppointment.getPhysician().getId()).isEqualTo(1);
      assertThat(resultAppointment.getAppointmentDate()).isEqualTo(LocalDate.of(2023, 1, 1));
      assertThat(resultAppointment.getAppointmentTime()).isEqualTo(LocalTime.of(9, 0));
      assertThat(resultAppointment.getStatus()).isEqualTo(AppointmentStatus.scheduled);
      
      Mockito.verify(entityManager)
        .createNativeQuery
        (Mockito.eq("SELECT * FROM appointment WHERE patient_id = :patientid"), 
            Mockito.eq(Appointment.class));
      Mockito.verify(query).setParameter("patientid", 1);
      Mockito.verify(query).getResultList();
      
    }
    
    @Test
    public void testAppointmentsByPhysicianId() {
      Query query = Mockito.mock(Query.class);
      
      List<Appointment> resultList = new ArrayList<>();
      resultList.add(appointment);
      Mockito
        .when(entityManager
          .createNativeQuery(Mockito.anyString(), Mockito.eq(Appointment.class)))
            .thenReturn(query);
      Mockito
        .when(query
          .setParameter(Mockito.anyString(), Mockito.anyInt()))
            .thenReturn(query);
      Mockito
        .when(query
            .getResultList())
              .thenReturn(resultList);
      
      List<Appointment> appointments = appointmentDao.getAppointmentsByPhysicianId(1);
      assertThat(appointments).isNotNull().hasSize(1);
      Appointment resultAppointment = appointments.get(0);
      assertThat(resultAppointment.getId()).isEqualTo(1);
      assertThat(resultAppointment.getPatient().getId()).isEqualTo(1);   
      assertThat(resultAppointment.getPhysician().getId()).isEqualTo(1);
      assertThat(resultAppointment.getAppointmentDate()).isEqualTo(LocalDate.of(2023, 1, 1));
      assertThat(resultAppointment.getAppointmentTime()).isEqualTo(LocalTime.of(9, 0));
      assertThat(resultAppointment.getStatus()).isEqualTo(AppointmentStatus.scheduled);
    
      Mockito.verify(entityManager)
      .createNativeQuery
      (Mockito.eq("SELECT * FROM appointment WHERE physician_id = :physicianid"), 
          Mockito.eq(Appointment.class));
      Mockito.verify(query).setParameter("physicianid", 1);
      Mockito.verify(query).getResultList();
    }
    
    @Test
    public void getAllAppointmentsByDate() {
      Appointment appointment2 = new Appointment();
      appointment2.setId(2);
      appointment2.setPatient(patient);
      appointment2.setPhysician(physician);
      appointment2.setAppointmentDate(LocalDate.of(2023, 1, 1));
      appointment2.setAppointmentTime(LocalTime.of(12, 0));
      appointment2.setStatus(AppointmentStatus.scheduled);
      
      String sql = "SELECT * FROM appointment WHERE DATE(appointment_date) = :appointmentdate";
      Query query = Mockito.mock(Query.class);
      
      List<Appointment> resultList = new ArrayList<>();
      resultList.add(appointment);
      resultList.add(appointment2);
      
      Mockito
        .when(entityManager
          .createNativeQuery(sql, Appointment.class))
            .thenReturn(query);
      Mockito
        .when(query
            .setParameter("appointmentdate", LocalDate.of(2023, 1, 1).toString()))
            .thenReturn(query);
      Mockito
        .when(query
            .getResultList())
              .thenReturn(resultList);
      
      List<Appointment> appointments = appointmentDao.getAllAppointmentsByDate(LocalDate.of(2023, 1, 1));
      assertThat(appointments).isNotNull().hasSize(2);
      Appointment resultAppointment = appointments.get(0);
      Appointment resultAppointment2 = appointments.get(1);
      assertThat(resultAppointment.getId()).isEqualTo(1);
      assertThat(resultAppointment2.getId()).isEqualTo(2);
      assertThat(resultAppointment.getAppointmentDate()).isEqualTo(LocalDate.of(2023, 1, 1));
      assertThat(resultAppointment.getStatus()).isEqualTo(AppointmentStatus.scheduled);
      assertThat(resultAppointment2.getAppointmentDate()).isEqualTo(LocalDate.of(2023, 1, 1));
      assertThat(resultAppointment.getAppointmentTime()).isEqualTo(LocalTime.of(9, 0));
      assertThat(resultAppointment2.getAppointmentTime()).isEqualTo(LocalTime.of(12, 0));
      
      
    }
    
    @Test
    public void testGetAppointmentByPatiendAndPhysicianId() {
      Appointment appointment2 = new Appointment();
      appointment2.setId(2);
      appointment2.setPatient(patient);
      appointment2.setPhysician(physician);
      appointment2.setAppointmentDate(LocalDate.of(2023, 1, 1));
      appointment2.setAppointmentTime(LocalTime.of(12, 0));
      appointment2.setStatus(AppointmentStatus.scheduled);
      
      Query query = Mockito.mock(Query.class);
      
      List<Appointment> resultList = new ArrayList<>();
      resultList.add(appointment);
      resultList.add(appointment2);
      
      Mockito
        .when(entityManager
            .createNativeQuery(Mockito.anyString(), Mockito.eq(Appointment.class)))
              .thenReturn(query);
      Mockito
        .when(query
            .setParameter(Mockito.anyString(), Mockito.anyInt()))
              .thenReturn(query);
      Mockito
        .when(query
            .getResultList())
              .thenReturn(resultList);
      
      List<Appointment> appointments = 
          appointmentDao.getAppointmentsByPatientAndPhysicianId(physician.getId(), patient.getId());
    
      assertNotNull(appointments);
      assertThat(appointments).hasSize(2);
      Appointment resultAppointment1 = appointments.get(0);
      Appointment resultAppointment2 = appointments.get(1);
      assertThat(resultAppointment1.getId()).isEqualTo(1);
      assertThat(resultAppointment2.getId()).isEqualTo(2);
      assertThat(resultAppointment1.getAppointmentDate()).isEqualTo(LocalDate.of(2023, 1, 1));
      assertThat(resultAppointment2.getAppointmentDate()).isEqualTo(LocalDate.of(2023, 1, 1));
      assertThat(resultAppointment1.getAppointmentTime()).isEqualTo(LocalTime.of(9, 0));
      assertThat(resultAppointment2.getAppointmentTime()).isEqualTo(LocalTime.of(12, 0));
    }
    
    
    
    
}
