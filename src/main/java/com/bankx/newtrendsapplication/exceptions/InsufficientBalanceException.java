package com.bankx.newtrendsapplication.exceptions;

public class InsufficientBalanceException extends Exception {

  public InsufficientBalanceException(String message){
    super(message);
  }
}
