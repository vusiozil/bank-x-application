package com.bankx.newtrendsapplication.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  public BaseEntity(){
  }

  public BaseEntity(Long id){
    this.id = id;
  }

  public Long getId(){
    return id;
  }

  public void setId(Long id){
    this.id = id;
  }

  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(!(o instanceof BaseEntity)) return false;
    BaseEntity that = (BaseEntity) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode(){
    return Objects.hash(id);
  }
}
