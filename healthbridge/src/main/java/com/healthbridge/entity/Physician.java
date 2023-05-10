package com.healthbridge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "physician")
public class Physician {

  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "physician_id")
  private Integer id;
  
  @Column(name = "first_name")
  String firstName;
  
  @Column(name = "last_name")
  String lastName;
  
  @Column(name = "contact_number")
  String contactNumber;
  
  @Column(name = "work_email")
  String workEmail;
   
}
