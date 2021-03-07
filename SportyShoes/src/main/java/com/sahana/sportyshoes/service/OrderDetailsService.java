package com.sahana.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sahana.sportyshoes.dao.OrderDetailsDAO;
import com.sahana.sportyshoes.model.OrderDetails;


@Component
public class OrderDetailsService {
	@Autowired
	public OrderDetailsDAO orderDetailsDAO;
	
	

	public void setOrderDetailsDAO(OrderDetailsDAO orderDetailsDAO) {
		this.orderDetailsDAO = orderDetailsDAO;
	}


	@Transactional
	public void updateItem(OrderDetails item) {
		orderDetailsDAO.updateItem(item);
		
	}


	public List<OrderDetails> getOrderDetails(int userId) {
		
		return orderDetailsDAO.getOrderDetails(userId);
	}


	public List<OrderDetails> getAllOrders() {
		return orderDetailsDAO.getOrderDetails();
	}
	
}
