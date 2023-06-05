package com.CRUD.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.POJO.Product;
import com.CRUD.rest.ProductRest;
import com.CRUD.service.ProductService;

@RestController
public class ProductRestImpl implements ProductRest {
	
	@Autowired
	ProductService productService;

	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {
			System.out.println("Product Added");
			return productService.addNewProduct(requestMap);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("{\"message\":\""+"Something Went Wrong"+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/")
	String returnHello() {
		return "Hello World";
	}

	@Override
	public ResponseEntity<List<Product>> getAllProduct() {
		try {
			return productService.getAllProducts();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Product>>(new ArrayList<Product>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
		try {
			System.out.println("Product Updated");
			return productService.updateProduct(requestMap);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("{\"message\":\""+"Something Went Wrong"+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteProduct(int productId) {
		try {
			System.out.println("Product Deleted");
			return productService.deleteProduct(productId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("{\"message\":\""+"Something Went Wrong"+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
