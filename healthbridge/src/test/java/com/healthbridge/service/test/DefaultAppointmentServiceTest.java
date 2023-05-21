package com.healthbridge.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.healthbridge.dao.AppointmentDao;
import com.healthbridge.entity.Appointment;
import com.healthbridge.entity.Patient;
import com.healthbridge.entity.Physician;
import com.healthbridge.service.DefaultAppointmentService;

@ExtendWith(MockitoExtension.class)
class DefaultAppointmentServiceTest {

  @Mock
  private AppointmentDao appointmentDao;
  
  @InjectMocks
  private DefaultAppointmentService appointmentService;
  
  private Appointment appointment;
  private Patient patient;
  private Physician physician;
  private List<Appointment> appointments;
  
  @BeforeEach
  public void setup() {
  appointment = new Appointment();
  appointments = new ArrayList<>();
  appointment.setId(1);
  
  patient = new Patient();
  physician = new Physician();
  patient.setId(1);
  physician.setId(1);
  
  appointment.setPatient(patient);
  appointment.setPhysician(physician);
  appointment.setAppointmentDate(LocalDate.of(2023, 1, 1));
  appointment.setAppointmentTime(LocalTime.of(12, 0));
  appointments.add(appointment);
  }
  
  @Test
  public void testSaveAppointment() {
    appointmentService.saveAppointment(appointment);
    verify(appointmentDao, times(1)).saveAppointment(appointment);
  }

  @Test
  public void testUpdateAppointment() {
    appointmentService.updateAppointment(appointment);
    verify(appointmentDao, times(1)).updateAppointment(appointment);
  }
  
  @Test
    public void testDeleteAppointment() {
    
    appointmentService.deleteAppointment(appointment);
    
    verify(appointmentDao).deleteAppointment(appointment);
  }
  
  @Test
   public void testGetAppointmentById() {
    
    when(appointmentDao.getAppointmentById(1)).thenReturn(appointment);
    Appointment actualAppointment = appointmentService.getAppointmentById(1);
    assertEquals(appointment, actualAppointment);
  }
  
  @Test
  public void testGetAppointmentsByPatientId() {
    Appointment appointment2 = new Appointment();
    appointment2.setPatient(patient);
    appointment2.setPhysician(physician);
    appointment2.setAppointmentDate(LocalDate.of(2023, 1, 1));
    appointment2.setAppointmentTime(LocalTime.of(8, 0));
    appointments.add(appointment2);
    when(appointmentDao.getAppointmentsByPatientId(1)).thenReturn(appointments);
    List<Appointment> actualAppointments = appointmentService.getAppointmentsByPatientId(1);
    System.out.println(actualAppointments.get(0).getAppointmentTime().toString() + "&" + actualAppointments.get(1).getAppointmentTime().toString());
    assertEquals(appointments, actualAppointments);
  }
  
  @Test
  public void testGetAppointmentsByPhysicianId() {
    Appointment appointment2 = new Appointment();
    appointment2.setPatient(patient);
    appointment2.setPhysician(physician);
    appointment2.setAppointmentDate(LocalDate.of(2023, 1, 1));
    appointment2.setAppointmentTime(LocalTime.of(8, 0));
    appointments.add(appointment2);
    when(appointmentDao.getAppointmentsByPhysicianId(1)).thenReturn(appointments);
    List<Appointment> actualAppointments = appointmentService.getAppointmentsByPhysicianId(1);
    assertNotNull(actualAppointments);
    assertEquals(2, actualAppointments.size());
    assertEquals(appointments, actualAppointments);
  }
  
  @Test
  public void testGetAppointmentsByPatientAndPhysicianId() {
    Appointment appointment2 = new Appointment();
    appointment2.setPatient(patient);
    appointment2.setPhysician(physician);
    appointment2.setAppointmentDate(LocalDate.of(2023, 1, 1));
    appointment2.setAppointmentTime(LocalTime.of(8, 0));
    Appointment appointment3 = new Appointment();
    Patient patient2 = new Patient();
    patient2.setId(2);
    appointment3.setPatient(patient2);
    appointment3.setPhysician(physician);
    appointment3.setAppointmentDate(LocalDate.of(2023, 1, 1));
    appointment3.setAppointmentTime(LocalTime.of(8, 0));
    
    appointments.add(appointment2);
    appointments.add(appointment3);
    
    when(appointmentDao.getAppointmentsByPatientAndPhysicianId(1, 1))
      .thenReturn(appointments.stream()
          .filter(appointment -> appointment.getPatient().getId() == 1)
            .collect(Collectors.toList()));
    List<Appointment> actualAppointments = appointmentService.getAppointmentsByPatientAndPhysicianId(1, 1);
    assertEquals(3, appointments.size());
    assertEquals(2, actualAppointments.size());
    System.out.println(appointments.get(0).getPatient().getId() 
        + "&" + appointments.get(1).getPatient().getId() 
        + "&" + appointments.get(2).getPatient().getId());
    System.out.println(actualAppointments.get(0).getPatient().getId() 
        + "&" + actualAppointments.get(1).getPatient().getId());
   }
    
   @Test
   public void getAllAppointmentsByDate() {
     Appointment appointment2 = new Appointment();
     appointment2.setPatient(patient);
     appointment2.setPhysician(physician);
     appointment2.setAppointmentDate(LocalDate.of(2023, 1, 1));
     appointment2.setAppointmentTime(LocalTime.of(8, 0));
     appointments.add(appointment2);
     when(appointmentDao.getAllAppointmentsByDate(LocalDate.of(2023, 1, 1))).thenReturn(appointments);
     List<Appointment> actualAppointments = appointmentService.getAllAppointmentsByDate(LocalDate.of(2023, 1, 1));
     assertNotNull(actualAppointments);
     assertEquals(2, actualAppointments.size());
     assertEquals(appointments, actualAppointments);
   }
}
