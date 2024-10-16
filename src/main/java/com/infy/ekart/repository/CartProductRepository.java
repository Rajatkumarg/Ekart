package com.infy.ekart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.CartProduct;
@Repository
public interface CartProductRepository extends CrudRepository<CartProduct, Integer> {

}