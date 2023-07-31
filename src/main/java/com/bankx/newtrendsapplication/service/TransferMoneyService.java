package com.bankx.newtrendsapplication.service;

import com.bankx.newtrendsapplication.domain.*;
import com.bankx.newtrendsapplication.exceptions.InsufficientBalanceException;
import com.bankx.newtrendsapplication.repository.TransactionRepository;
import com.bankx.newtrendsapplication.service.impl.CurrentAccountService;
import org.springframework.stereotype.Service;

@Service
public class TransferMoneyService {

  private final TransactionRepository transactionRepository;

  private final SendEmailService sendEmailService;

  private final CurrentAccountService currentAccountService;

  public TransferMoneyService(TransactionRepository transactionRepository,
                              SendEmailService sendEmailService,
                              CurrentAccountService currentAccountService){
    this.transactionRepository = transactionRepository;
    this.sendEmailService = sendEmailService;
    this.currentAccountService = currentAccountService;
  }

  // Assuming there NO CHARGES or credits  move money between Savings and Current Account as 
  // it is not clear on the questions
  public void transferMoneyBetweenAccountsOnBankX(Account from, Account to, double amount) throws InsufficientBalanceException{
    payment(from,amount,"Transfer",TransactionType.WITHDRAWAL);

    payment(to,amount,"Transfer",TransactionType.DEPOSIT);
    
    
    if(from.getCustomer()!= to.getCustomer()){
      double charges = 0.005* amount;
      payment(from,charges,"Charges",TransactionType.WITHDRAWAL);
      
      if(to instanceof SavingsAccount ){
        double credit = 0.05* to.getBalance();
        payment(from,credit,"Credit",TransactionType.DEPOSIT);
        
      }
    }


  }

  private void payment(Account account, double amount,String description, TransactionType type) throws InsufficientBalanceException{
    String fromAccount = getAccountName(account);
    Transaction transaction;

    switch(type){
      case DEPOSIT:
        transaction = new Transaction(fromAccount,amount, description, TransactionType.DEPOSIT,account.getCustomer());
        account.deposit(amount);
        account.add(transaction);
        transactionRepository.save(transaction);
        sendEmailService.sendEmail(transaction,account.getCustomer().getEmail(), account.getBalance());
        break;
      case WITHDRAWAL:

        account.withdraw(amount);
        transaction = new Transaction(fromAccount,amount, description, TransactionType.WITHDRAWAL,account.getCustomer());
        account.add(transaction);
        transactionRepository.save(transaction);
        sendEmailService.sendEmail(transaction,account.getCustomer().getEmail(), account.getBalance());
        
        break;
      default:
        throw new IllegalStateException("Unknown transaction");
    }

  }


  public void pay(Transaction transaction) throws InsufficientBalanceException{
    Customer customer = transaction.getCustomer();
    CurrentAccount currentAccount = currentAccountService.findByCustomer(customer);

    switch(transaction.getTransactionType()){
      case DEPOSIT:

        currentAccount.deposit(transaction.getAmount());
        currentAccount.add(transaction);
        transactionRepository.save(transaction);
        sendEmailService.sendEmail(transaction,customer.getEmail(), currentAccount.getBalance());
        break;
      case WITHDRAWAL:

        currentAccount.withdraw(transaction.getAmount());
        currentAccount.add(transaction);
        transactionRepository.save(transaction);
        sendEmailService.sendEmail(transaction,customer.getEmail(), currentAccount.getBalance());

        //Charges for every withdrawal
        double charges = 0.005* transaction.getAmount();
        currentAccount.withdraw(charges);
        transaction =new Transaction("Current Account",charges, "Charges",
                TransactionType.WITHDRAWAL, customer);
        currentAccount.add(transaction);
        transactionRepository.save(transaction);
        sendEmailService.sendEmail(transaction,customer.getEmail(), currentAccount.getBalance());

        break;
      default:
        throw new IllegalStateException("Unknown transaction");
    }

  }

  private String getAccountName(Account account){

    if(account instanceof SavingsAccount){
      return "Savings Account";
    }
    else if(account instanceof CurrentAccount){
      return "Current Account";
    }
    return "Unknown";
  }

}
