package com.healthbridge.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testLoginEndpoint() throws Exception{
      String username = "testuser";
      String password = "testpassword";
      
      mockMvc.perform(MockMvcRequestBuilders.get("/login")
      .param("username", username)
      .param("password", password))
      .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
      .andExpect(MockMvcResultMatchers.redirectedUrl("/staff/home"));
    }
    
    
}
