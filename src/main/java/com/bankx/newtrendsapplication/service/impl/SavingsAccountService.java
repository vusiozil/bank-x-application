package com.bankx.newtrendsapplication.service.impl;

import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.domain.SavingsAccount;
import com.bankx.newtrendsapplication.exceptions.ResourceNotFoundException;
import com.bankx.newtrendsapplication.repository.SavingsAccountRepository;
import com.bankx.newtrendsapplication.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SavingsAccountService implements CrudService<SavingsAccount,Long> {

  private final SavingsAccountRepository savingsAccountRepository;

  public SavingsAccountService(SavingsAccountRepository savingsAccountRepository){
    this.savingsAccountRepository = savingsAccountRepository;
  }

  @Override
  public SavingsAccount findById(Long ownerId) throws ResourceNotFoundException{
    return savingsAccountRepository.findById(ownerId).orElseThrow(()->new ResourceNotFoundException(
            "Savings Account was not found"));
  }

  @Override
  public SavingsAccount save(SavingsAccount object){
    return savingsAccountRepository.save(object);
  }

  @Override
  public Set<SavingsAccount> findAll(){
    return null;
  }

  @Override
  public void update(SavingsAccount object){
        savingsAccountRepository.save(object);
  }

  @Override
  public void delete(SavingsAccount object){
    savingsAccountRepository.delete(object);
  }

  @Override
  public void deleteById(Long Id){
    savingsAccountRepository.deleteById(Id);
  }

  public SavingsAccount findByCustomer(Customer customer){
    return savingsAccountRepository.findByCustomer(customer);
  }
}
