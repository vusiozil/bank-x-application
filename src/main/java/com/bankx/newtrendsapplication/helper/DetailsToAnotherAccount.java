package com.bankx.newtrendsapplication.helper;

public class DetailsToAnotherAccount {

  private Long accountNumber;

  private double amount;

  private String bankName;

  private String accountType;

  private String description;

  public Long getAccountNumber(){
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber){
    this.accountNumber = accountNumber;
  }

  public double getAmount(){
    return amount;
  }

  public void setAmount(double amount){
    this.amount = amount;
  }

  public String getBankName(){
    return bankName;
  }

  public void setBankName(String bankName){
    this.bankName = bankName;
  }

  public String getAccountType(){
    return accountType;
  }

  public void setAccountType(String accountType){
    this.accountType = accountType;
  }

  public String getDescription(){
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }

  @Override
  public String toString(){
    return "DetailsToAnotherAccount{" +
            "accountNumber=" + accountNumber +
            ", amount=" + amount +
            ", bankName='" + bankName + '\'' +
            ", accountType='" + accountType + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
