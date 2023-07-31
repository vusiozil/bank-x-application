package com.bankx.newtrendsapplication.service;

import com.bankx.newtrendsapplication.domain.Customer;

public interface CustomerService extends CrudService<Customer,Long> {

  Customer findByEmail(String email) ;
}
