package com.bankx.newtrendsapplication.controller.web;

import com.bankx.newtrendsapplication.service.CustomerService;
import com.bankx.newtrendsapplication.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {

  private final CustomerService customerService;

  @Autowired
  private MessagingService messagingService;

  public HomeController(CustomerService customerService){
    this.customerService = customerService;
  }

  @GetMapping
  ModelAndView index(){
//    messagingService.sendMessage("Hello World");

    ModelAndView mav = new ModelAndView("index");
    mav.addObject("customers", customerService.findAll());

    return mav;
  }
}
