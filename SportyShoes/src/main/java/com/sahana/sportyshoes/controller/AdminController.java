package com.sahana.sportyshoes.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sahana.sportyshoes.model.Admin;
import com.sahana.sportyshoes.model.Category;
import com.sahana.sportyshoes.model.OrderDetails;
import com.sahana.sportyshoes.model.Products;
import com.sahana.sportyshoes.model.Users;
import com.sahana.sportyshoes.service.AdminService;
import com.sahana.sportyshoes.service.CategoryService;
import com.sahana.sportyshoes.service.OrderDetailsService;
import com.sahana.sportyshoes.service.ProductService;
import com.sahana.sportyshoes.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
    private AdminService adminService; 
	
	@Autowired
    private UserService userService; 
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	
	@RequestMapping(value="/adminlogin",  method = RequestMethod.POST)
	public String adminLogin(ModelMap map, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="emailId", required=true) String emailId,
   		 	@RequestParam(value="pwd", required=true) String pwd) throws IOException{
		 Admin admin = adminService.authenticate(emailId, pwd);
		 if(admin!= null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			session.setMaxInactiveInterval(250);
			
			return "redirect:adminproducts";
		 }
			 return "index";
	
	}
	
	  @RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
	    public String logout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  	HttpSession session = request.getSession();
		  	session.invalidate();
		  	
	        return "admin-login"; 
	    }
	  
	
	  @RequestMapping(value = "/adminchangepwdaction", method = RequestMethod.POST)
	    public String updatePassword(ModelMap map,  @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2, 
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  map.addAttribute("error", "Error , Incomplete passwords submitted.");
			  return "change-password";
		  }
		  if (!pwd.equals(pwd2)) {
			  map.addAttribute("error", "Passwords do not match.");
			  return "change-password";
		  }
		  Admin sessionAdmin= (Admin)session.getAttribute("admin");
		  Admin admin = adminService.getAdminById(sessionAdmin.getId()); 
		  admin.setAdminPwd(pwd);
		  adminService.updatePwd(admin);
		  
	        return "redirect:adminproducts";
	    }	
	  
	  
	  @RequestMapping(value = "/adminmembers", method = RequestMethod.GET)
	    public String members(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  List<Users> list = userService.getAllUsers();
		  
		  map.addAttribute("list", list);
	        return "admin-members"; 
	    }
	  @RequestMapping(value = "/adminproducts", method = RequestMethod.GET)
	    public String products(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  List<Products> list = productService.getAllProducts();

		  HashMap<Integer, String> mapCats = new HashMap<Integer, String>();
		  
		  for(Products product: list) {
			  Category category = categoryService.getCategoryById(product.getCategoryId());
			  if (category != null)
				  mapCats.put(product.getProductId(), category.getName());
		  }
		  map.addAttribute("list", list);
		  map.addAttribute("mapCats", mapCats);

	        return "admin-products"; 
	    }

	  @RequestMapping(value = "/editproduct",  method = RequestMethod.GET)
	    public String editProduct(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  
		  int idValue = Integer.parseInt(id);
		  Products product = new Products();
		  if (idValue > 0) {
			  product = productService.getProductById(idValue);
		  } else {
			  product.setProductId(0);
			  product.setCategoryId(0);
		  }
		  String dropDown = categoryService.getCategoriesDropDown(product.getCategoryId());
		  map.addAttribute("product", product);
		  map.addAttribute("catDropdown", dropDown);
	        return "edit-product"; 
	    }	
	  
	  @RequestMapping(value = "/admineditproduct", method = RequestMethod.POST)
	    public String updateProduct(ModelMap map, javax.servlet.http.HttpServletRequest request,
	    		 @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name,
	    		 @RequestParam(value="price", required=true) String price,
	    		 @RequestParam(value="category", required=true) String categoryId) 
	    {
		 int productId = Integer.parseInt(id); 
		  int categoryIdValue = Integer.parseInt(categoryId);
		  float priceValue =0;
		  
		  if (name == null || name.equals("") ) { 
			  map.addAttribute("error", "Error, A product name must be specified");
			  return "redirect:editproduct?id="+id;
		  }
		  try {
			  priceValue = Float.parseFloat(price);
			  
		  } catch (Exception ex) {
			  map.addAttribute("error", "Error, Price is invalid");
			  return "redirect:editproduct?id="+id;
		  }
		  
		  	Products product = new Products();
		  	product.setProductId(productId);
		  	product.setCategoryId(categoryIdValue);
		  	product.setName(name);
		  	product.setPrice(priceValue);
		  	
		  	productService.updateProduct(product); 
		  	
	        return "redirect:adminproducts"; 
	    }	
	   
	  @RequestMapping(value = "/deleteproduct",  method = RequestMethod.GET)
	    public String deleteProduct(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  int productId = Integer.parseInt(id);
		 
			  productService.deleteProduct(productId);
		  
		  return "redirect:adminproducts";
	    }	
	  @RequestMapping(value = "/adminpurchases", method = RequestMethod.GET)
	    public String purchases(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		 
		  float total = 0;
		  HashMap<Integer, String> mapItems = new HashMap<Integer, String>();
			
				  List<OrderDetails> orders = orderDetailsService.getAllOrders();
				
				  map.addAttribute("list", orders);	
				 
			
				  return "admin-purchases"; 
		  
	    }	  
	  @RequestMapping(value = "/searchuser",  method = RequestMethod.GET)
	    public String searchUser(ModelMap map,  @RequestParam(value="username") String username,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  List<Users> users = userService.getUser(username);
		  map.addAttribute("users", users);	
		  return "admin-users";
	    }
}
