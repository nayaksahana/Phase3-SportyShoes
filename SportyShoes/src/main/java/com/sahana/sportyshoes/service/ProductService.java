package com.sahana.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sahana.sportyshoes.dao.ProductDao;
import com.sahana.sportyshoes.dao.UserDAO;
import com.sahana.sportyshoes.model.Products;


@Component
public class ProductService {
	 @Autowired
	 private ProductDao productDAO;

	public void setProductDAO(ProductDao productDAO) {
		this.productDAO = productDAO;
	}

	@Transactional
	public List<Products> getAllProducts() {
		return productDAO.getAllProducts();
	}

	@Transactional
	public Products getProductById(int productId) {
		return productDAO.getProductById(productId);
	}

	@Transactional
	public void deleteProduct(int productId) {
		productDAO.deleteProduct(productId);
	}
	
	@Transactional
	public void updateProduct(Products product) {
		productDAO.updateProduct(product);
		
	}

}
