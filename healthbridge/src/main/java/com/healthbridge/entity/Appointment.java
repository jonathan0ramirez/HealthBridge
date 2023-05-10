package com.healthbridge.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appointment_id")
  private Integer id;
  
  @Column(name = "appointment_date_time")
  private LocalDateTime appointmentDateTime;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private AppointementStatus Status;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "patient_id", nullable = false)
  private Patient patient;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "physician_id", nullable = false)
  private Physician physician;
  
}
