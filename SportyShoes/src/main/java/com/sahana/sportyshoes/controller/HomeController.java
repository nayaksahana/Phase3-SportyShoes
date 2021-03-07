package com.sahana.sportyshoes.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.sahana.sportyshoes.model.Products;
import com.sahana.sportyshoes.model.Users;
import com.sahana.sportyshoes.service.ProductService;
import com.sahana.sportyshoes.service.UserService;

@Controller
public class HomeController {
	@Autowired
	public UserService userService; 
	
	@Autowired
	public ProductService productService; 
	

	@RequestMapping(value="/")
	public ModelAndView loginPage(HttpServletResponse response) throws IOException{
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/signup")
	public String signup(HttpServletResponse response, HttpServletRequest request) {
		return "signup";
	}
	
	@RequestMapping(value="/loginaction",  method = RequestMethod.POST)
	public String login(ModelMap map, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="emailId", required=true) String emailId,
   		 	@RequestParam(value="pwd", required=true) String pwd) throws IOException{
		 Users user = userService.authenticate(emailId, pwd);
		 if(user!= null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(250);
			List<Products> products = productService.getAllProducts();
			session.setAttribute("products", products);
			return "customer-dashboard";
		 }
			 return "index";
	
	}
	
	

	  @RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public String logout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  	HttpSession session = request.getSession();
		  	session.invalidate();
		  	
	        return "index"; 
	    }
	  
	
	@RequestMapping(value="/signin",  method = RequestMethod.POST)
	public String signin(ModelAndView model, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="fname", required=true) String firstName,
			@RequestParam(value="lname", required=true) String lastName,
			@RequestParam(value="address", required=true) String address,
			@RequestParam(value="emailId", required=true) String emailId,
   		 	@RequestParam(value="pwd", required=true) String pwd) throws IOException{
		Users user = new Users(firstName, lastName, address, emailId, pwd);
		int userId = userService.insertUser(user);
		if(userId != 0) {
			model.addObject("message", "Signed up successfully. Please login");
			return "index";
		}
		else {
			model.addObject("message", "Signup failed. Please try after some time");
			return "signup";
		}
	}
	
//	}
}
