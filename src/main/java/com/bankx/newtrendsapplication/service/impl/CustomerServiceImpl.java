package com.bankx.newtrendsapplication.service.impl;

import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.exceptions.ResourceNotFoundException;
import com.bankx.newtrendsapplication.repository.CustomerRepository;
import com.bankx.newtrendsapplication.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer findById(Long ownerId) throws ResourceNotFoundException{
    return customerRepository
            .findById(ownerId)
            .orElseThrow(()->new ResourceNotFoundException("Customer was not found"));
  }

  @Override
  public Customer save(Customer object){
    return customerRepository.save(object);
  }

  @Override
  public Set<Customer> findAll(){
    return customerRepository.findAll();
  }

  @Override
  public void update(Customer object){
    customerRepository.save(object);
  }

  @Override
  public void delete(Customer object){
    customerRepository.delete(object);

  }

  @Override
  public void deleteById(Long Id){
      customerRepository.deleteById(Id);
  }

  @Override
  public Customer findByEmail(String email){
    return customerRepository
            .findByEmail(email).orElse(null);
  }
}
