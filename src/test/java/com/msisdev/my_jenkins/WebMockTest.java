package com.msisdev.my_jenkins;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
public class WebMockTest {
  
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private HelloService service;

  @Test
  void helloShouldReturnMessageFromService() throws Exception {
    when(service.hello("World"))
      .thenReturn("Hello, World!");

    this.mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("Hello, World!")));
  }
}
