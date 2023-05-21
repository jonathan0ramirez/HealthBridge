package com.healthbridge.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.healthbridge.entity.Login;
import lombok.extern.slf4j.Slf4j;

@Repository //this is DAO class
@Transactional //all public methods should run within a transaction
public class DefaultLoginDao implements LoginDao {

  @PersistenceContext //inject jpa entity manager  
  private EntityManager entityManager;
  
  @Override
  public void save(Login login) {
    entityManager.persist(login); //save the login to the database using the EntityManager.
  }

  @Override
  public void updatePassword(String username, String password) {
    Login login = entityManager.find(Login.class, username);
 //retrieve the Login from the database using the EntityManager.
    if (login == null) {
      //if null then we throw that can't find the login
      throw new RuntimeException("Unable to find login with the username" + username);
    }
  //if username found we update the password for the retrieved login
    login.setPassword(password); 
   
  }

  @Override
  public List<Login> getAllLogins(){
   String sql = "SELECT * FROM Login";
   Query query = entityManager.createNativeQuery(sql, Login.class);
   return query.getResultList();
 //retrieves a list of Login from database using EntityManager and returns it.
  }

  @Override
  public Login findByUsername(String username) {
    Query query = 
     entityManager.createNativeQuery("SELECT * FROM Login WHERE username = :username", Login.class);
    query.setParameter("username", username);
    List<Login> results = query.getResultList();
    if (!results.isEmpty()) {
      return results.get(0);
    } else {
      return null;
    }
    }
  
  
}
