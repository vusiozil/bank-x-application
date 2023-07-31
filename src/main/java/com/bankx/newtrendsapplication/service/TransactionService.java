package com.bankx.newtrendsapplication.service;

import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.domain.Transaction;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService extends CrudService<Transaction,Long> {
  List<Transaction> findAll(Pageable pageable);

  List<Transaction> findAllByFromAccountAndCustomer(String fromAccount,Customer customer);

  List<Transaction> findAllByCustomer(Customer customer);

  List<Transaction> findAllByCustomerOrderByCreatedAtDesc(Customer customer,Pageable pageable);

}
