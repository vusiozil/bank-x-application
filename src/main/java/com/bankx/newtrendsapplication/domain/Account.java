package com.bankx.newtrendsapplication.domain;

import com.bankx.newtrendsapplication.exceptions.InsufficientBalanceException;

import javax.persistence.*;

@MappedSuperclass
public abstract class Account{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long accountNumber;

  protected double balance;

  protected String bankName;

  @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  protected Customer customer;


  public Account(){
  }

  public Account(Customer customer){
    this(customer,"Bank X");
    this.customer = customer;
  }

  public Account(Customer customer,String bankName){
    this.customer = customer;
    this.bankName = bankName;
  }

  public Account(Long accountNumber, double balance, String bankName, Customer customer){
    this(customer,bankName);
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

  public Long getAccountNumber(){
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber){
    this.accountNumber = accountNumber;
  }

  public String getBankName(){
    return bankName;
  }

  public void setBankName(String bankName){
    this.bankName = bankName;
  }

  public Customer getCustomer(){
    return customer;
  }

  public void setCustomer(Customer customer){
    this.customer = customer;
  }

  public double getBalance(){
    return balance;
  }

  public void setBalance(double balance){
    this.balance = balance;
  }

  public void deposit(double amount) {
    balance += amount;
  }

  public void withdraw(double amount) throws InsufficientBalanceException{
    if(balance<amount){
      throw new InsufficientBalanceException("Insufficient balance.");
    }
    balance-=amount;
  }

  public abstract void add(Transaction transaction);

}
