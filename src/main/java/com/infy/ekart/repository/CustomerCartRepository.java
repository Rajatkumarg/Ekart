package com.infy.ekart.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.CustomerCart;
@Repository
public interface CustomerCartRepository extends CrudRepository<CustomerCart, Integer> {
	Optional<CustomerCart> findByCustomerEmailId(String customerEmailId);
}