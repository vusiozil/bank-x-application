package com.bankx.newtrendsapplication.repository;

import com.bankx.newtrendsapplication.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@RequestMapping
public interface CustomerRepository extends CrudRepository<Customer,Long> {

  Set<Customer> findAll();

  Optional<Customer> findByEmail(String email);
}
