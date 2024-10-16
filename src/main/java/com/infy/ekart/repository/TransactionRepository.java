package com.infy.ekart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.Transaction;
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}