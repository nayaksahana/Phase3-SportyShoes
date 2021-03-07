


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page import="com.sahana.sportyshoes.model.Products"%>
<%@page import="com.sahana.sportyshoes.model.Users"%>
<%@page import="com.sahana.sportyshoes.dao.ProductDao" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />

</head>

 
    
    
<body>
<div class ="container">
<jsp:include page="customer-header.jsp"></jsp:include>
<br/><br/>
<%
	/* Checking the user credentials */
		HttpSession httpSession = request.getSession(true);
		Users user = (Users)httpSession.getAttribute("user");
		if(user== null){
			response.sendRedirect("index.jsp");
		}	
		List<Products> products = (List<Products>)httpSession.getAttribute("products");
		
	%>


  

	<div class="container">
	  	<div class="row">
	  	<%
	  	for(Products product : products){ 
	  	%>
    		<div class="col-sm">
    		<form method = "get" <%-- action="addCart?uid=<%user.getUserId(); %>&pid=<%product.getProductId(); %>" --%>>
     			<div class="thumbnail">
      				<img src="ShowImage.jpg?pid=<%=product.getProductId() %>" style="height:200px; max-width:200px">
      				<p class="productname" id = "productname"><%=product.getName() %> </p>
      				<p class="price" id = "price">Rs <%=product.getPrice() %> </p>
      				
      					<label for="qty">Quantity</label>
					<select name="qty" id ="quantity">
  						<option value="1">1</option>
  						<option value="2">2</option>
  						<option value="3">3</option>
  						<option value="4">4</option>
  						<option value="5">5</option>
  
					</select>
      					<br/>
      					<a href="addCart?uid=${user.getUserId()}&pid=<%=product.getProductId()%>">Add To Cart</a>
      					<!--  <button type = "submit" id ="cart">Add To Cart</button> -->
      				
    			</div>
    			</form>
    		</div>
    		<%} %>
  		</div>
  		
	</div>
	 
 </div>
</body>
</html>