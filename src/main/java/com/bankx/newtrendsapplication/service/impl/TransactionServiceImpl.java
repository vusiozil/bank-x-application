package com.bankx.newtrendsapplication.service.impl;

import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.domain.Transaction;
import com.bankx.newtrendsapplication.exceptions.ResourceNotFoundException;
import com.bankx.newtrendsapplication.repository.TransactionRepository;
import com.bankx.newtrendsapplication.service.TransactionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  public TransactionServiceImpl(TransactionRepository transactionRepository){
    this.transactionRepository = transactionRepository;
  }

  @Override
  public Transaction findById(Long ownerId) throws ResourceNotFoundException{
    return transactionRepository.findById(ownerId).orElseThrow(()->new ResourceNotFoundException(
            "Transaction was not found"));
  }

  @Override
  public Transaction save(Transaction object){
    return transactionRepository.save(object);
  }

  @Override
  public Set<Transaction> findAll(){
    return transactionRepository.findAll();
  }

  @Override
  public void update(Transaction object){
    transactionRepository.save(object);
  }

  @Override
  public void delete(Transaction object){
    transactionRepository.delete(object);
  }

  @Override
  public void deleteById(Long Id){
  transactionRepository.deleteById(Id);
  }

  @Override
  public List<Transaction> findAll(Pageable pageable){
    return transactionRepository.findAll(pageable);
  }

  @Override
  public List<Transaction> findAllByFromAccountAndCustomer(String fromAccount,Customer customer){
    return transactionRepository.findAllByFromAccountAndCustomer(fromAccount,customer);
  }

  @Override
  public List<Transaction> findAllByCustomer(Customer customer){
    return transactionRepository.findAllByCustomer(customer);
  }

  @Override
  public List<Transaction> findAllByCustomerOrderByCreatedAtDesc(Customer customer, Pageable pageable){
    return transactionRepository.findAllByCustomerOrderByCreatedAtDesc(customer, pageable);
  }
}
