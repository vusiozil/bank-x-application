package com.bankx.newtrendsapplication.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CurrentAccount extends Account{

  private double initialAmount;

  @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  private List<Transaction> transactions = new ArrayList<Transaction>();

  public CurrentAccount(){

  }

  public CurrentAccount(double initialAmount, Customer customer){
    super(customer);
    this.balance += initialAmount;

  }

  public CurrentAccount(double initialAmount, Customer customer,String bankName){
    super(customer,bankName);
    this.balance += initialAmount;

  }

  public CurrentAccount(Long accountNumber,String bankName){
    super(accountNumber, 0, bankName, null);

  }

  public double getInitialAmount(){
    return initialAmount;
  }

  public void setInitialAmount(double initialAmount){
    this.initialAmount = initialAmount;
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
  public String toString(){
    return "CurrentAccount{" +
            "initialAmount=" + initialAmount +
//            ", transactions=" + transactions +
            ", accountNumber=" + accountNumber +
            ", balance=" + balance +
            ", bankName='" + bankName + '\'' +
            ", customer=" + customer +
            '}';
  }

  @Override
  public void add(Transaction transaction){
    this.transactions.add(transaction);
  }
}
