package com.healthbridge.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff/home")
public class StaffController {

  @GetMapping
  public ResponseEntity<List<String>> staffHome(){
    List<String> options = new ArrayList<>();
      options.add("/staff/home/patients/{id}");
      options.add("/staff/home/patients/getall");
      options.add("/staff/home/appointments");
      options.add("/staff/home/physicians");
      options.add("/physician-patient");
      return ResponseEntity.ok(options);
  }
  
}
