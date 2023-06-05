package com.CRUD.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CRUD.POJO.Product;
import com.CRUD.dao.ProductDao;
import com.CRUD.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;

	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {
			
			if(validateAddNewProduct(requestMap,false)) {
				Product product = getProductFromMap(requestMap, false);
				productDao.save(product);
				return new ResponseEntity<String>("{\"message\":\""+"Product Added."+"\"}",HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("{\"message\":\""+"Invalid Data."+"\"}",HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("{\"message\":\""+"Something Went Wrong"+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private boolean validateAddNewProduct(Map<String,String> requestMap,boolean validateProductId) {
		
		if(requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description") && !validateProductId) {
			return true;
		}
		
		if(requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description") && requestMap.containsKey("productId") && validateProductId) {
			return true;
		}
		
		return false;
	}
	
	private Product getProductFromMap(Map<String,String> requestMap, boolean isAdd) {
		Product product = new Product();
		
		if(isAdd) {
			product.setId(Integer.parseInt(requestMap.get("productId")));
		}
		
		product.setName(requestMap.get("name"));
		product.setPrice(Float.parseFloat(requestMap.get("price")));
		product.setDescription(requestMap.get("description"));
		
		return product;
	}

	@Override
	public ResponseEntity<List<Product>> getAllProducts() {
		
		try {
			return new ResponseEntity<List<Product>>(productDao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Product>>(new ArrayList<Product>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateProduct(Map<String,String> requestMap) {
		try {
			
			if(validateAddNewProduct(requestMap,true)) {
				Optional optional = productDao.findById(Integer.parseInt(requestMap.get("productId")));
				
				if(optional.isPresent()) {
					productDao.save(getProductFromMap(requestMap, true));
					return new ResponseEntity<String>("{\"message\":\""+"Product Id updated."+"\"}",HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("{\"message\":\""+"Product Id does not exist."+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}else {
				return new ResponseEntity<String>("{\"message\":\""+"Invalid Data."+"\"}",HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("{\"message\":\""+"Something Went Wrong"+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteProduct(int productId) {
		try {
			if(productId>0) {
				productDao.deleteById(productId);
				return new ResponseEntity<String>("{\"message\":\""+"Product is deleted"+"\"}",HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("{\"message\":\""+"Invalid Product Id."+"\"}",HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("{\"message\":\""+"Something Went Wrong"+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
