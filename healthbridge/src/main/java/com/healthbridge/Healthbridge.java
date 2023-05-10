package com.healthbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class Healthbridge {

  public static void main(String[] args) {
    SpringApplication.run(Healthbridge.class, args);

  }
  
}
