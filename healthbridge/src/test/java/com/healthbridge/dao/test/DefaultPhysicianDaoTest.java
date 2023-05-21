package com.healthbridge.dao.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.healthbridge.dao.DefaultPhysicianDao;
import com.healthbridge.entity.Physician;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class DefaultPhysicianDaoTest {

  @Autowired
  private DefaultPhysicianDao physicianDao;
  
  @PersistenceContext
  private EntityManager entityManager;
  
  @Test
  public void testGetAll() {
    List<Physician> physicians = physicianDao.getAll();
    assertNotNull(physicians);
    assertEquals(6, physicians.size());
  }
  
  @Test
  public void testGetByFirstNameWithFullName() {
    List<Physician> physicians = physicianDao.findByFirstName("Stephen");
    assertNotNull(physicians);
    assertEquals(1, physicians.size());
    assertEquals("Strange", physicians.get(0).getLastName());
  }
  
  @Test
  public void testFindByFirstNameNotFullName() {
    List<Physician> physicians = physicianDao.findByFirstName("Ste");
    System.out.println("test first name" + physicians.get(0));
    System.out.println(physicians.get(1));
    assertNotNull(physicians);
    assertEquals(2, physicians.size());
    assertEquals("Stephen", physicians.get(0).getFirstName());
    assertEquals("Steven", physicians.get(1).getFirstName());

  }
    @Test
    public void testFindByLastName() {
      List<Physician> physicians = physicianDao.findByLastName("Grey");
      assertNotNull(physicians);
      assertEquals(2, physicians.size());
      System.out.println("test last name" + physicians.get(0));
      System.out.println(physicians.get(1));
      assertEquals("Jean", physicians.get(0).getFirstName());
      assertEquals("Steven", physicians.get(1).getFirstName());
    }
  
}
