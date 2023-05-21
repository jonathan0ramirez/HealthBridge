package com.healthbridge.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import com.healthbridge.controller.DefaultPhysicianController;
import com.healthbridge.entity.Physician;
import com.healthbridge.errorhandler.ErrorResponse;
import com.healthbridge.service.PhysicianService;

@ExtendWith(MockitoExtension.class)
public class DefaultPhysicianControllerTest {
  
  @Mock
  private PhysicianService physicianService;
  
  @InjectMocks
  private DefaultPhysicianController controller;
  
  @Test
  public void testFindPhysicianByFirstName() {
    String firstName = "testFirstName";
    Physician physician = new Physician();
    physician.setId(1);
    physician.setFirstName("testFirstName");
    physician.setLastName("testLastName");
    List<Physician> physicianList = new ArrayList<>();
    physicianList.add(physician);
    
    when(physicianService.findByFirstName(firstName)).thenReturn(physicianList);
    
    ResponseEntity<?> responseEntity = controller.getPhysicianByFirstName(firstName);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(physicianList, responseEntity.getBody());
  }
  
  @Test
  public void testFindPhysicianByFirstNameNoMatches() {
    String firstName = "testFirstName";
    List<Physician> physicianList = new ArrayList<>();
    when(physicianService.findByFirstName(firstName)).thenReturn(physicianList);
    
    ResponseEntity<?> responseEntity = controller.getPhysicianByFirstName(firstName);
    ErrorResponse errorResponse = (ErrorResponse)responseEntity.getBody();
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals("No matches found", errorResponse.getMessage());
   
  }
  
  @Test
  public void testFindPhysiciansByLastName() {
    String lastName = "test";
    Physician physician1 = new Physician();
    Physician physician2 = new Physician();
    physician1.setFirstName("testFirstName");
    physician1.setLastName(lastName);
    physician1.setId(1);
    physician2.setFirstName("testFirstName");
    physician2.setLastName("testXYZ");
    List<Physician> physicians = new ArrayList<>();
    physicians.add(physician1);
    physicians.add(physician2);
    when(physicianService.findByLastName(lastName)).thenReturn(physicians);
    assertNotNull(physicians);
    assertEquals(2, physicians.size());
    ResponseEntity<?> responseEntity = controller.getPhysicianByLastName(lastName);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(physicians, responseEntity.getBody());
  }
  
  @Test
  public void testFindPhysicianByLastNameNoMatches() {
    String lastName = "test";
    List<Physician> physician = new ArrayList<>();
    when(physicianService.findByLastName(lastName)).thenReturn(physician);
    ResponseEntity<?> responseEntity = controller.getPhysicianByLastName(lastName);
    ErrorResponse errorResponse = (ErrorResponse)responseEntity.getBody();
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals("No matches found", errorResponse.getMessage());
  }
  
  @Test
  public void testGetAllPhysicians() {
    Physician physician1 = new Physician();
    Physician physician2 = new Physician();
    physician1.setFirstName("testFirstName");
    physician1.setLastName("testLastName");
    physician1.setId(1);
    physician2.setFirstName("testFirstNames");
    physician2.setLastName("testXYZ");
    List<Physician> physicians = new ArrayList<>();
    physicians.add(physician1);
    physicians.add(physician2);
    
    assertNotNull(physicians);
    assertEquals(2, physicians.size());
    
    when(physicianService.getAll()).thenReturn(physicians);
    ResponseEntity<List<Physician>> responseEntity = controller.getAllPhysician();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(physicians, responseEntity.getBody());
  }
}
