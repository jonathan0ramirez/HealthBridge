package com.healthbridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http
       .authorizeRequests().antMatchers("/staff/home/appointments/newAppointment")
       .hasRole("staff")
       .anyRequest().permitAll()
       .and()
     .formLogin()
       .loginPage("/login")
       .permitAll()
       .and()
     .httpBasic()
       .and()
     .csrf().disable();
    return http.build();
  }
  
  @Bean
  public UserDetailsService user() {
    String encodedPassword = new BCryptPasswordEncoder().encode("testpassword");
    UserDetails user = User.builder()
        .username("testuser")
        .password(encodedPassword)
        .roles("staff")
        .build();
    
    return new InMemoryUserDetailsManager(user);
    
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  
}
