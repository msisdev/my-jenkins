package com.msisdev.my_jenkins;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

  private static final String template = "Hello, %s!";

  public String hello(String name) {
    return String.format(template, name);
  }
}
