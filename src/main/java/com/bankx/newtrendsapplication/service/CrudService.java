package com.bankx.newtrendsapplication.service;

import com.bankx.newtrendsapplication.exceptions.ResourceNotFoundException;

import java.util.Set;

public interface CrudService <T,ID>{

  T findById(ID ownerId) throws ResourceNotFoundException;

  T save(T object);

  Set<T> findAll();

  void update(T object);

  void delete(T object);
  void deleteById(ID Id);

}
