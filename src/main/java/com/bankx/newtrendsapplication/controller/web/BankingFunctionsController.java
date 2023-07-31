package com.bankx.newtrendsapplication.controller.web;

import com.bankx.newtrendsapplication.domain.*;
import com.bankx.newtrendsapplication.exceptions.InsufficientBalanceException;
import com.bankx.newtrendsapplication.exceptions.ResourceNotFoundException;
import com.bankx.newtrendsapplication.helper.DetailsToAnotherAccount;
import com.bankx.newtrendsapplication.helper.HandlerSelection;
import com.bankx.newtrendsapplication.service.CustomerService;
import com.bankx.newtrendsapplication.service.TransferMoneyService;
import com.bankx.newtrendsapplication.service.impl.CurrentAccountService;
import com.bankx.newtrendsapplication.service.impl.SavingsAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/banking")
public class BankingFunctionsController {

 private final TransferMoneyService transferMoneyService;

 private final CustomerService customerService;

 private final SavingsAccountService savingsAccountService;

 private final CurrentAccountService currentAccountService;

 public BankingFunctionsController(TransferMoneyService transferMoneyService,
                                   CustomerService customerService, SavingsAccountService savingsAccountService, CurrentAccountService currentAccountService){
  this.transferMoneyService = transferMoneyService;
  this.customerService = customerService;
  this.savingsAccountService = savingsAccountService;
  this.currentAccountService = currentAccountService;
 }

 @PostMapping("/pay/{Id}")
 public String pay(@PathVariable("Id") Long Id,
                   @ModelAttribute("details") DetailsToAnotherAccount details,
                   RedirectAttributes redirectAttributes){

  Customer mySelf =null;
    try {

      mySelf = customerService.findById(Id);
     Account sendingAccount = currentAccountService.findByCustomer(mySelf);
     //The other account
     Account receivingAccount = null;

     //Check if we are within the same bank
     if(details.getBankName().equalsIgnoreCase("Bank X")){

      if(details.getAccountType().equals("Savings Account")){
       receivingAccount = savingsAccountService.findById(details.getAccountNumber());
      }
      else if(details.getAccountType().equals("Current Account")){
       receivingAccount = currentAccountService.findById(details.getAccountNumber());
      }
      transferMoneyService.transferMoneyBetweenAccountsOnBankX(sendingAccount,receivingAccount,details.getAmount());
     } else {
      // This is what happens on my account when send money another

      transferMoneyService.pay(new Transaction("Current Account",details.getAmount(), details.getDescription(),
              TransactionType.WITHDRAWAL,mySelf));
     }

     return "redirect:/customer/"+mySelf.getId();

    } catch(ResourceNotFoundException | InsufficientBalanceException e) {
     redirectAttributes.addFlashAttribute("error",e.getMessage());
     return "redirect:/customer/"+mySelf.getId();
    }
 }


 @PostMapping("/transfer/{Id}")
  public String transfer(@PathVariable("Id") Long Id,
                         @ModelAttribute("handleInputs") HandlerSelection handleInputs, RedirectAttributes redirectAttributes) throws ResourceNotFoundException, InsufficientBalanceException{

   System.out.println("Id ============ " + Id);
   System.out.println("handleInputs = " + handleInputs);

  Customer customer = customerService.findById(Id);
  CurrentAccount currentAccount = currentAccountService.findByCustomer(customer);
  SavingsAccount savingsAccount = savingsAccountService.findByCustomer(customer);

  System.out.println("handleInputs.getFrom().equals(handleInputs.getTo()) = " + handleInputs.getFrom().equals(handleInputs.getTo()));

  if (handleInputs.getFrom().equals(handleInputs.getTo())){

   redirectAttributes.addFlashAttribute("error", "Not allowed ");

   return "redirect:/customer/"+customer.getId();

  }
  else {

   if(handleInputs.getTo().equals("Savings Account")){
    transferMoneyService.transferMoneyBetweenAccountsOnBankX(currentAccount, savingsAccount,handleInputs.getAmount());
   }
   else{
    transferMoneyService.transferMoneyBetweenAccountsOnBankX(savingsAccount, currentAccount,handleInputs.getAmount());
   }
  }

  return "redirect:/customer/"+customer.getId();
 }

}
