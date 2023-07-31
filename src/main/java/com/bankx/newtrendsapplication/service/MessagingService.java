package com.bankx.newtrendsapplication.service;

import com.bankx.newtrendsapplication.domain.Transaction;
import com.bankx.newtrendsapplication.exceptions.InsufficientBalanceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingService {

  @Value("${activemq.queue.name}")
  String destination;

  @Autowired
  private JmsTemplate jmsTemplate;


  private final TransferMoneyService transferMoneyService;

  Logger log = LoggerFactory.getLogger(MessagingService.class);


  public MessagingService(TransferMoneyService transferMoneyService){
    this.transferMoneyService = transferMoneyService;
  }

  //THE SEND METHODS ARE FOR TESTING WHERE THE APPLICATION CAN RECEIVE NOT NEEDED FOR Bank X
  public void sendTransaction(Transaction transaction) {
    jmsTemplate.convertAndSend(destination,transaction);
    log.info("Sent transaction='{}'", transaction);
  }

  public void sendTransaction(List<Transaction> transactions) {
    jmsTemplate.convertAndSend(destination,transactions);
    log.info("Sent transactions='{}'", transactions);
  }

  @JmsListener(destination = "${activemq.queue.name}")
  public void receiveSingleTransaction(Transaction transaction) throws InsufficientBalanceException{
    log.info("Received transaction='{}'", transaction);

    transferMoneyService.pay(transaction);
  }

  @JmsListener(destination = "${activemq.queue.name}")
  public void receiveListOfTransactions(List<Transaction> transactions) throws InsufficientBalanceException{
    log.info("Received transactions='{}'", transactions);

    for (Transaction tx : transactions) {
      transferMoneyService.pay(tx);
    }
  }


}
