package com.bankx.newtrendsapplication.repository;

import com.bankx.newtrendsapplication.domain.CurrentAccount;
import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.domain.SavingsAccount;
import com.bankx.newtrendsapplication.domain.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface TransactionRepository extends CrudRepository<Transaction,Long>  {

  List<Transaction> findAll(Pageable pageable);

  List<Transaction> findAllByCustomer(Customer customer);

  List<Transaction> findAllByCustomerOrderByCreatedAtDesc(Customer customer,Pageable pageable);

  List<Transaction> findAllByFromAccountAndCustomer(String fromAccount,Customer customer);

  Set<Transaction> findAll();
}
