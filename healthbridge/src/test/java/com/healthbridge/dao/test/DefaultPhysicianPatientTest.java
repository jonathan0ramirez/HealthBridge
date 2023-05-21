package com.healthbridge.dao.test;

import com.healthbridge.dao.DefaultPhysicianPatient;
import com.healthbridge.entity.Patient;
import com.healthbridge.entity.Physician;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultPhysicianPatientTest {

    @InjectMocks
    private DefaultPhysicianPatient physicianPatientDao;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testLinkPhysicianAndPatient() {
        Physician physician = new Physician();
        Patient patient = new Patient();

        physicianPatientDao.linkPhysicianAndPatient(physician, patient);

        // Verify that the physician and patient objects are updated and merged
        verify(entityManager, times(1)).merge(physician);
        verify(entityManager, times(1)).merge(patient);
    }
    
    @Test
    public void testGetPhysiciansByPatientId() {
        int patientId = 1;
        Patient patient = new Patient();
        physicianPatientDao.getPhysiciansByPatientId(patientId);

        // Verify that the entityManager.find() method is called with the correct arguments
        verify(entityManager, times(1)).find(Patient.class, patientId);
    }
    
    
}

