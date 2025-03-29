package com.msisdev.my_jenkins;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

  private final HelloService service;

  public HelloController(HelloService service) {
    this.service = service;
  }
  
  @GetMapping("/")
  public @ResponseBody String hello(@RequestParam(defaultValue = "World") String name) {
    return service.hello(name);
  }
  
}
