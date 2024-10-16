package com.infy.ekart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

	List<Customer> findByPhoneNumber(String phoneNumber);

}