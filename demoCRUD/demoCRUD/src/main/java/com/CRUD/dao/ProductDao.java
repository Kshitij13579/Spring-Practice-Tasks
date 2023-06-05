package com.CRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUD.POJO.Product;

public interface ProductDao extends JpaRepository<Product,Integer> {

}
