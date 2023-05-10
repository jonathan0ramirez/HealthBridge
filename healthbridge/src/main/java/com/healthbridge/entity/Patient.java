package com.healthbridge.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "patient")
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patient_id")
  private Integer id;
  
  @Column(name = "first_name")
  private String firstname;

  @Column(name = "last_name")
  private String lastName;
  
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;
  
  @Column(name = "race_ethnicity")
  private String raceEthnicity;
  
  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender; 
  
  @Column(name = "mailing_address")
  private String mailingAddress;
  
  @Column(name = "residential_address")
  private String residentialAddress;
  
  @Column(name = "contact_number")
  private String contactNumber;

  public void setDateOfBirth(String dateOfBirth){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateOfBirth, formatter);
    this.dateOfBirth = localDate;
  }
  
  
  
}
