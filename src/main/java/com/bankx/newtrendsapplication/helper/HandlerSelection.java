package com.bankx.newtrendsapplication.helper;

public class HandlerSelection {

  String from;
  String to;

  double amount;

  public String getTo(){
    return to;
  }

  public void setTo(String to){
    this.to = to;
  }

  public String getFrom(){
    return from;
  }

  public void setFrom(String from){
    this.from = from;
  }

  public double getAmount(){
    return amount;
  }

  public void setAmount(double amount){
    this.amount = amount;
  }

  @Override
  public String toString(){
    return "HandlerSelection{" +
            "from='" + from + '\'' +
            ", to='" + to + '\'' +
            ", amount=" + amount +
            '}';
  }
}
