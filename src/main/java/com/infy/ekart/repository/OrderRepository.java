package com.infy.ekart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.Order;
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

	// add methods if required

}