package com.sahana.sportyshoes.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sahana.sportyshoes.model.OrderDetails;
import com.sahana.sportyshoes.model.Products;
import com.sahana.sportyshoes.model.Purchase;
import com.sahana.sportyshoes.model.Users;
import com.sahana.sportyshoes.service.OrderDetailsService;
import com.sahana.sportyshoes.service.ProductService;
import com.sahana.sportyshoes.service.PurchaseService;


@Controller
public class OrderController {
	
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public String memberpurchases(ModelMap map, javax.servlet.http.HttpServletRequest request) 
    {
	  HttpSession session = request.getSession();
	  if (session.getAttribute("user") == null) {
		  return "index";
	  }
	  Users user = (Users) session.getAttribute("user");
	  
	  float total = 0;
	  HashMap<Integer, String> mapItems = new HashMap<Integer, String>();
		  
		  List<OrderDetails> orders = orderDetailsService.getOrderDetails(user.getUserId());
		  StringBuilder sb = new StringBuilder("");
		  for(OrderDetails item: orders) {
			  Products product = productService.getProductById(item.getProductId());
			  
			  mapItems.put(item.getOrderId(),product.getName());
			  
		  }
		 
		  map.addAttribute("list", orders);	
	  map.addAttribute("mapItems", mapItems);	  
       return "purchases"; 
    }	
}
