package com.bankx.newtrendsapplication.controller.web;

import com.bankx.newtrendsapplication.domain.CurrentAccount;
import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.domain.SavingsAccount;
import com.bankx.newtrendsapplication.domain.Transaction;
import com.bankx.newtrendsapplication.exceptions.ResourceNotFoundException;
import com.bankx.newtrendsapplication.service.TransactionService;
import com.bankx.newtrendsapplication.service.impl.CurrentAccountService;
import com.bankx.newtrendsapplication.service.impl.SavingsAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

  private final SavingsAccountService savingsAccountService;

  private final CurrentAccountService currentAccountService;

  private final TransactionService transactionService;

  public AccountController(SavingsAccountService savingsAccountService,
                           CurrentAccountService currentAccountService,
                           TransactionService transactionService){
    this.savingsAccountService = savingsAccountService;
    this.currentAccountService = currentAccountService;
    this.transactionService = transactionService;
  }

  @GetMapping("/currentAccount/{accountNumber}")
   ModelAndView getCurrentAccountTransaction(@PathVariable("accountNumber") Long accountNumber) throws ResourceNotFoundException{
      ModelAndView mav = new ModelAndView("account_transactions");

    CurrentAccount currentAccount = currentAccountService.findById(accountNumber);
    mav.addObject("account", "Current Account");
    mav.addObject("balance", currentAccount.getBalance());


    List<Transaction> transactions = transactionService.findAllByFromAccountAndCustomer("Current Account", currentAccount.getCustomer());
    mav.addObject("transactions", transactions);

      return mav;
  }

  @GetMapping("/savingsAccount/{accountNumber}")
   ModelAndView getSavingsAccountTransaction(@PathVariable("accountNumber") Long accountNumber) throws ResourceNotFoundException{
      ModelAndView mav = new ModelAndView("account_transactions");

    SavingsAccount savingsAccount = savingsAccountService.findById(accountNumber);
    Customer customer = savingsAccount.getCustomer();

    List<Transaction> transactions = transactionService.findAllByFromAccountAndCustomer("Savings " +
            "Account",customer);
    mav.addObject("transactions", transactions);


    mav.addObject("account", "Savings Account");
    mav.addObject("balance", savingsAccount.getBalance());

      return mav;
  }
}
