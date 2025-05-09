package com.msisdev.my_jenkins;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
  
  @Autowired  // Inect before test
  private HelloController controller;

  @Test
  void contextLoads() throws Exception {
    assertThat(controller).isNotNull();
  }
}
