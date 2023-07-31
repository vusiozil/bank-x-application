package com.bankx.newtrendsapplication.service.impl;

import com.bankx.newtrendsapplication.domain.CurrentAccount;
import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.exceptions.ResourceNotFoundException;
import com.bankx.newtrendsapplication.repository.CurrentAccountRepository;
import com.bankx.newtrendsapplication.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CurrentAccountService implements CrudService<CurrentAccount,Long> {

  private final CurrentAccountRepository currentAccountRepository;

  public CurrentAccountService(CurrentAccountRepository currentAccountRepository){
    this.currentAccountRepository = currentAccountRepository;
  }

  @Override
  public CurrentAccount findById(Long ownerId) throws ResourceNotFoundException{
    return currentAccountRepository.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Could not find"));
  }

  @Override
  public CurrentAccount save(CurrentAccount object){
    return currentAccountRepository.save(object);
  }

  @Override
  public Set<CurrentAccount> findAll(){
    return null;
  }

  @Override
  public void update(CurrentAccount object){
    currentAccountRepository.save(object);
  }

  @Override
  public void delete(CurrentAccount object){
    currentAccountRepository.delete(object);
  }

  @Override
  public void deleteById(Long Id){
    currentAccountRepository.deleteById(Id);
  }

  public CurrentAccount findByCustomer(Customer customer){
    return currentAccountRepository.findByCustomer(customer);
  }
}
