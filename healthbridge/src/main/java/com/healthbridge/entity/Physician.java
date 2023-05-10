package com.healthbridge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
