package com.healthbridge.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
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
