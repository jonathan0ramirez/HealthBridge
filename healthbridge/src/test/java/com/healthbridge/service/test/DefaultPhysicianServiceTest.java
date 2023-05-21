package com.healthbridge.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.healthbridge.dao.PhysicianDao;
import com.healthbridge.entity.Physician;
import com.healthbridge.service.DefaultPhysicianService;

@ExtendWith(MockitoExtension.class)
class DefaultPhysicianServiceTest {

  @Mock
  private PhysicianDao physicianDao;
  
  @InjectMocks
  private DefaultPhysicianService physicianService;
  
  private Physician physician1;
  private Physician physician2;
  
  
  @BeforeEach
  public void setup() {
    physician1 = new Physician();
    physician2 = new Physician();
    physician1.setId(1);
    physician1.setFirstName("testSubjectOne");
    physician1.setLastName("testOne");
    physician1.setContactNumber("1234567890");
    physician1.setWorkEmail("im@testsubject.com");
    physician2.setId(2);
    physician2.setFirstName("testSubjectTwo");
    physician2.setLastName("testTwo");
    physician2.setContactNumber("0123456789");
    physician2.setWorkEmail("imalso@testsubject.com");
    
    List<Physician> physicians = new ArrayList<>();
    physicians.add(physician1);
    physicians.add(physician2);
    
    physicianService = Mockito.mock(DefaultPhysicianService.class);
    
    Mockito.when(physicianService.findByFirstName(anyString())).thenReturn(physicians);

  }
  
    @Test
    public void testFindPhysiciansByFirstName() {
      
      List<Physician> physician = physicianService.findByFirstName("test");
      System.out.println(physician);
      assertEquals(2, physician.size());
      assertEquals("testSubjectOne", physician.get(0).getFirstName());
      assertEquals("testSubjectTwo", physician.get(1).getFirstName());
  }
    
  

}

