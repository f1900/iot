package com.mit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.mit.model.Product;
import com.mit.model.User;
@Repository
public interface ProductDao extends JpaSpecificationExecutor<Product>,JpaRepository<Product, Integer> {

}
