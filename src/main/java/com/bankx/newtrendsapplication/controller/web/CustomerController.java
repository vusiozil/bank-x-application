package com.bankx.newtrendsapplication.controller.web;

import com.bankx.newtrendsapplication.domain.CurrentAccount;
import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.domain.SavingsAccount;
import com.bankx.newtrendsapplication.domain.Transaction;
import com.bankx.newtrendsapplication.exceptions.ResourceNotFoundException;
import com.bankx.newtrendsapplication.helper.DetailsToAnotherAccount;
import com.bankx.newtrendsapplication.helper.HandlerSelection;
import com.bankx.newtrendsapplication.service.CustomerService;

import com.bankx.newtrendsapplication.service.MessagingService;
import com.bankx.newtrendsapplication.service.TransactionService;
import com.bankx.newtrendsapplication.service.impl.CurrentAccountService;
import com.bankx.newtrendsapplication.service.impl.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService customerService;

  private final TransactionService transactionService;

  private final SavingsAccountService savingsAccountService;

  private final CurrentAccountService currentAccountService;

  @Autowired
  private MessagingService messagingService;



  public CustomerController(CustomerService customerService, TransactionService transactionService, SavingsAccountService savingsAccountService, CurrentAccountService currentAccountService){
    this.customerService = customerService;
    this.transactionService = transactionService;
    this.savingsAccountService = savingsAccountService;
    this.currentAccountService = currentAccountService;
  }

  @GetMapping("/register")
  public ModelAndView register(){
    ModelAndView mav = new ModelAndView("new_customer");
    mav.addObject("newCustomer", new Customer());

    return mav;
  }

  @GetMapping("{Id}")
  public ModelAndView showCustomerInformation(@PathVariable("Id")Long Id) throws ResourceNotFoundException{

    ModelAndView modelAndView = new ModelAndView("insideView");

    Customer customer = customerService.findById(Id);
    modelAndView.addObject("customer", customer);

    int pageSize = 7;
    Pageable pageable = PageRequest.of(0, pageSize);

    List<Transaction> transactions = transactionService.findAllByCustomerOrderByCreatedAtDesc(customer,pageable);
    modelAndView.addObject("transactions", transactions);

    CurrentAccount currentAccount = currentAccountService.findByCustomer(customer);
    modelAndView.addObject("currentAccount", currentAccount);

    SavingsAccount savingsAccount = savingsAccountService.findByCustomer(customer);
    modelAndView.addObject("savingsAccount", savingsAccount);

    modelAndView.addObject("handleInputs",new HandlerSelection());

    modelAndView.addObject("details",new DetailsToAnotherAccount());

    modelAndView.addObject("success","Please click the Account Card to see transaction for that account");



    return modelAndView;
  }

  @GetMapping("/delete/{Id}")
  public String deleteCustomer(@PathVariable("Id") Long Id ,
                               RedirectAttributes redirectAttributes) throws ResourceNotFoundException{

    Customer customer = customerService.findById(Id);
    if (customer == null) {
      throw new ResourceNotFoundException("Customer with customer Number :" + Id + " Not " +
              "Found!");
    }

   customerService.deleteById(Id);


    redirectAttributes.addFlashAttribute("success", "User was successfully deleted.");

    return "redirect:/";
  }


  @PostMapping("/save")
  public String save(@Valid @ModelAttribute("newCustomer") Customer customer,
                     @RequestParam("initialAmount") double initialAmount,
                      BindingResult result,
                      RedirectAttributes redirectAttributes) {

    System.out.println("initialAmount = " + initialAmount);

    Customer customerx = customerService.findByEmail(customer.getEmail());
    if (customerx != null)
      result.rejectValue("email", "error.user", "Email Already Exist");


    customerService.save(customer);
    savingsAccountService.save(new SavingsAccount(customer));
    currentAccountService.save(new CurrentAccount(initialAmount,customer));
    redirectAttributes.addFlashAttribute("success", "Customer was successfully Added.");

    return "redirect:/customer/register";

  }


}
