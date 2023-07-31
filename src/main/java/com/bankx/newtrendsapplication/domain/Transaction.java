package com.bankx.newtrendsapplication.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Transaction extends BaseEntity {


  @DateTimeFormat(pattern = "dd MMMM yyyy")
  private LocalDate createdAt = LocalDate.now();

  private String fromAccount; //  @ManyToOne private Account currentAccount; had problems

  private double amount;

  private String description;

  private TransactionType transactionType;

  @ManyToOne(cascade = CascadeType.ALL)
  private Customer customer;


  public Transaction(){
  }

  public Transaction( String fromAccount, double amount, String description,
                     TransactionType transactionType){
    this.fromAccount = fromAccount;
    this.amount = amount;
    this.description = description;
    this.transactionType = transactionType;
  }

  public Transaction( String fromAccount, double amount, String description,
                     TransactionType transactionType,Customer customer){
   this(fromAccount, amount, description, transactionType);
   this.customer = customer;
  }



  public LocalDate getCreatedAt(){
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt){
    this.createdAt = createdAt;
  }

  public double getAmount(){
    return amount;
  }

  public void setAmount(double amount){
    this.amount = amount;
  }

  public String getDescription(){
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public String getFromAccount(){
    return fromAccount;
  }

  public void setFromAccount(String fromAccount){
    this.fromAccount = fromAccount;
  }

  public TransactionType getTransactionType(){
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType){
    this.transactionType = transactionType;
  }


  public Customer getCustomer(){
    return customer;
  }

  public void setCustomer(Customer customer){
    this.customer = customer;
  }

}
