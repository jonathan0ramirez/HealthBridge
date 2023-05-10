package com.healthbridge.entity;

import java.math.BigInteger;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity //to tell that this class is entity and to be mapped to database table
@Table(name = "login")
public class Login {
    
    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "staff_role", columnDefinition = "VARCHAR(50) default 'staff'")
    private String staffRole;
    
    @Column(name = "staff_id", columnDefinition = "VARCHAR(50) default 'staffId'")
    private String staffId;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
    
    public Login(String username, String password, String staff_role, String staff_id) {
      
      this.username = username;
      this.password = password;
      this.staffRole = staff_role;
      this.staffId = staff_id;
    }
    
    public Login() {
    }
    
    public String getPassword() {
      return this.password;
    }

}
