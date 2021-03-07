package com.sahana.sportyshoes.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sahana.sportyshoes.model.Cart;
import com.sahana.sportyshoes.model.OrderDetails;
import com.sahana.sportyshoes.model.Products;
import com.sahana.sportyshoes.model.Users;
import com.sahana.sportyshoes.service.OrderDetailsService;
import com.sahana.sportyshoes.service.ProductService;
import com.sahana.sportyshoes.service.PurchaseService;

 
@Controller
public class CartController {
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private PurchaseService purchaseService; 

	@Autowired
	private OrderDetailsService purchaseProductService; 

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			map.addAttribute("error", "Error, You need to login before adding items to cart");
		}
		else {
			List<Cart> cartItems = new ArrayList<Cart>();
			if (session.getAttribute("cart_items") != null)
				cartItems = (List<Cart>) session.getAttribute("cartItems");
			 map.addAttribute("cartItems", cartItems);
 		  }
		  
		map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");
	    return "cart";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addCart", method = RequestMethod.GET)
	public String cartAddItem(ModelMap map, javax.servlet.http.HttpServletRequest request, 
	@RequestParam(name="uid") String userId, @RequestParam("pid") String pid) 
	{
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			map.addAttribute("error", "Please login to add items to cart");
		}
		else{
			int productId = Integer.parseInt(pid);
			List<Cart> cartItems = new ArrayList<Cart>();
			if (session.getAttribute("cartItems") != null)
				cartItems = (List<Cart>) session.getAttribute("cartItems");
			if (isItemInCart(cartItems, productId)) {
				map.addAttribute("error", "This item is already in your cart");
			}
			else {
				Products product = productService.getProductById(productId);
				Cart item = new Cart();
				item.setProductId(productId);
				item.setQty(2);
				item.setRate(product.getPrice());
				float dprice = item.getRate()*item.getQty(); 
				item.setPrice(dprice); 
				item.setProductName(product.getName()); 
				cartItems.add(item);
				float totalPrice = getTotalPrice(cartItems);
				session.setAttribute("totalPrice", totalPrice);
				session.setAttribute("cartItems", cartItems);
			  }
		  }
	      return "redirect:cart"; 
	    }	  
	
	  @RequestMapping(value = "/gateway", method = RequestMethod.GET)
	    public String gateway(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before making payment");
		  } 
			  /*List<Cart> cartItems = new ArrayList<Cart>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<Cart>) session.getAttribute("cartItems");
			  BigDecimal totalValue = getTotalPrice(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);

		  }
*/
	        return "gateway";
		  
	    }
	  
	  @SuppressWarnings("unchecked")
	@RequestMapping(value = "/completeorder", method = RequestMethod.GET)
	    public String completePurchase(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user") == null) {
			  map.addAttribute("error", "Error, You need to login before completing purchase");
		  } else {
			  List<Cart> cartItems = new ArrayList<Cart>();
			  if (session.getAttribute("cartItems") != null)
				  cartItems = (List<Cart>) session.getAttribute("cartItems");
			  float totalValue = getTotalPrice(cartItems);
			  
			  Users user = (Users) session.getAttribute("user") ;
			  /*
			  Purchase purchase = new Purchase();
			  purchase.setUserId(user.getUserId());
			  purchase.setDate(Calendar.getInstance().getTime());
			  purchase.setTotal(totalValue);
			  int purchaseId = purchaseService.updatePurchase(purchase);
			  */
			  for(Cart item: cartItems) {
				  OrderDetails pItem = new OrderDetails();
				  pItem.setProductId(item.getProductId());
				  pItem.setUserId(user.getUserId());
				  pItem.setRate(item.getRate());
				  pItem.setQty(item.getQty());
				  pItem.setPrice(item.getPrice());
				  
				  purchaseProductService.updateItem(pItem);
			  }
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);

		  }
		  
	        return "redirect:confirm";  
	    }
	  @RequestMapping(value = "/confirm", method = RequestMethod.GET)
	    public String confirm(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before completing the purchase");
		  } else {
			  // clear items from cart as order has completed 
			  List<Cart> cartItems = new ArrayList<Cart>();
			  if (session.getAttribute("cartItems") != null)
				  cartItems = (List<Cart>) session.getAttribute("cartItems");
			  float totalValue = getTotalPrice(cartItems);
			  map.addAttribute("cartValue", totalValue);

			  
			  cartItems.clear();
			  session.setAttribute("cartItems", null);
		  }
		
	        return "confirm"; 
	    }	  
	private float getTotalPrice(List<Cart> list) {
		float total = 0;
		for(Cart item: list) {
			float price = item.getRate()*item.getQty();
			total= total+price;
		}
		return total;
	}
	  
	  private boolean isItemInCart(List<Cart> list, int item) {
		  boolean retVal = false;
		  for(Cart thisItem: list) {
			  if (item == thisItem.getProductId()) {
				  retVal = true;
				  break;
			  }
		  }
		  return retVal;
	  } 

	 

}
