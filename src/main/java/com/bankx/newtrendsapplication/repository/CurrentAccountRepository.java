package com.bankx.newtrendsapplication.repository;

import com.bankx.newtrendsapplication.domain.CurrentAccount;
import com.bankx.newtrendsapplication.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CurrentAccountRepository extends CrudRepository<CurrentAccount,Long> {
  CurrentAccount findByCustomer(Customer customer);
}
