package com.healthbridge.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appointment_id")
  private Integer id;
  
  @Column(name = "appointment_date")
  private LocalDate appointmentDate;
  
  @Column(name = "appointment_time")
  private LocalTime appointmentTime;
  
  @Column(name = "appointment_duration")
  private LocalTime appointmentDuration;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private AppointmentStatus Status;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "patient_id", nullable = false)
  private Patient patient;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "physician_id", nullable = false)
  private Physician physician;
  
}
