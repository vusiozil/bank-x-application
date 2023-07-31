package com.bankx.newtrendsapplication.repository;

import com.bankx.newtrendsapplication.domain.Customer;
import com.bankx.newtrendsapplication.domain.SavingsAccount;
import com.bankx.newtrendsapplication.service.CrudService;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountRepository extends CrudRepository<SavingsAccount,Long> {

  SavingsAccount findByCustomer(Customer customer);
}
