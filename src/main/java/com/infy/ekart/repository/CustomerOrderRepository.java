package com.infy.ekart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.Order;
@Repository
public interface CustomerOrderRepository extends CrudRepository<Order, Integer> {
	List<Order> findByCustomerEmailId(String customerEmailId);
}