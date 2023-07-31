package com.bankx.newtrendsapplication.service;

import com.bankx.newtrendsapplication.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

  @Autowired
  private  JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String sender;



  public void sendEmail(Transaction transaction,String receiverEmail,double balance) {

      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom(sender);
      mailMessage.setTo(receiverEmail);
      mailMessage.setSubject("Transaction");
      String message = "";


    switch(transaction.getTransactionType()){
      case DEPOSIT:
          message+="+R"+transaction.getAmount()+" into ";
        break;
      case WITHDRAWAL:
        message+="-R"+transaction.getAmount()+" from ";

        break;
    }

    message+= transaction.getFromAccount()+";Available ;Balance is R"+balance+";"+transaction.getCreatedAt()+";"+transaction.getDescription();

      mailMessage.setText(message);

      mailSender.send(mailMessage);
  }

}
