package com.CRUD.service;

import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.CRUD.POJO.Product;

public interface ProductService {
	
	public ResponseEntity<String> addNewProduct(Map<String,String> requestMap);
	
	public ResponseEntity<List<Product>> getAllProducts();
	
	public ResponseEntity<String> updateProduct(Map<String,String> requestMap);
	
	public ResponseEntity<String> deleteProduct(int productId);
}
