package com.bankx.newtrendsapplication.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Customer extends BaseEntity{

  @NotNull
  private String fullNames;

  @NotNull
  private String surname;

  @NotNull
//  @Email
  private String email;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dob;

  public String getFullNames(){
    return fullNames;
  }

  public void setFullNames(String fullNames){
    this.fullNames = fullNames;
  }

  public String getSurname(){
    return surname;
  }

  public void setSurname(String surname){
    this.surname = surname;
  }

  public String getEmail(){
    return email;
  }

  public void setEmail(String email){
    this.email = email;
  }


  public LocalDate getDob(){
    return dob;
  }

  public void setDob(LocalDate dob){
    this.dob = dob;
  }



  @Override
  public String toString(){
    return "Customer{" +
            "fullNames='" + fullNames + '\'' +
            ", surname='" + surname + '\'' +
            ", email='" + email + '\'' +
            ", dob=" + dob +
            ", id=" + id +
            '}';
  }
}
