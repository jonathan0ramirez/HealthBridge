package com.healthbridge.errorhandler;

import java.util.List;
import com.healthbridge.entity.Appointment;

public class AppointmentResponse {
  private List<Appointment> listAppointments;
  private Appointment appointment;
  private String message;
  
  public AppointmentResponse(Appointment appointment) {
    this.appointment = appointment;
  }
  
  public AppointmentResponse(List<Appointment> listAppointments) {
    this.listAppointments = listAppointments;
  }
  
  public AppointmentResponse(String message) {
    this.message = message;
  }
  
  public Appointment getAppointment() {
    return appointment;
  }
  
  public String getMessage() {
    return message;
  }
  
  public List<Appointment> getListAppointments(){
    return listAppointments;
  }

}

