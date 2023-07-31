package com.bankx.newtrendsapplication.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SavingsAccount extends Account{

  private static final double BONUS=500;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Transaction> transactions = new ArrayList<Transaction>();


  public SavingsAccount(){

    balance += BONUS;
  }

  public SavingsAccount(Long accountNumber,String bankName){
    super(accountNumber, 0, bankName, null);
    balance+=BONUS;
  }

  public SavingsAccount(Customer customer){
    super(customer);
    balance+=BONUS;
  }


  public Customer getCustomer(){
    return customer;
  }

  public void setCustomer(Customer customer){
    this.customer = customer;
  }

  public List<Transaction> getTransactions(){
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions){
    this.transactions = transactions;
  }


  @Override
  public void add(Transaction transaction){
    this.transactions.add(transaction);
  }

  @Override
  public String toString(){
    return "SavingsAccount{" +
            "transactions=" + transactions +
            ", accountNumber=" + accountNumber +
            ", balance=" + balance +
            ", bankName='" + bankName + '\'' +
            ", customer=" + customer +
            '}';
  }
}
