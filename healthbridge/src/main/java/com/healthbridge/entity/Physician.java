package com.healthbridge.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  
  @ManyToMany(mappedBy = "physicians")
  @JsonIgnore
  private List<Patient> patients;
   
  public List<Patient> getPatients(){
    if (patients == null) {
      patients = new ArrayList<>();
    }
    return patients;
  }
}
