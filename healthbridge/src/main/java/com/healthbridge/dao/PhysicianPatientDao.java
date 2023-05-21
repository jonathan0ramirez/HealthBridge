package com.healthbridge.dao;

import java.util.List;
import com.healthbridge.entity.Patient;
import com.healthbridge.entity.Physician;

public interface PhysicianPatientDao {

  void linkPhysicianAndPatient(Physician physician, Patient patient);
  void unlinkPhysicianAndPatient(Physician physician, Patient patient);
  List<Physician> getPhysiciansByPatientId(int patientId);
  List<Patient> getPatientsByPhysicianId(int physicianId);
}
