<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Your Cart</title>
</head>
<body>
<jsp:include page="customer-header.jsp"></jsp:include>
<br/><br/>

<%
if (request.getParameter("error") != null)
	out.print(request.getParameter("error") + "<br>");
else{
%>
<div class = "container">
<form action="completeorder" method = "GET">
<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Product</b></td>
 		<td><b>Qty</b></td>
 		<td><b>Rate</b></td>
 		<td><b>Price</b></td>
 		<td></td>
 	</tr>
 	<c:forEach items="${cartItems}" var="item">
 	  	<tr>
	 		<td>${item.productName }</td>
 			<td>${item.qty }</td>
 			<td>${item.rate}</td>
 			<td>${item.price}</td>
 	  		<td>
 	  			<a href="cartdeleteitem?id=${item.productId}">Remove</a>
 	  		</td>
 	  		
 	  	</tr>
 	  </c:forEach>
</table> 
<c:if test = "${cartItems.size() > 0}">
	<h3>Total Amount: ${totalPrice}</h3>
	<input type = "submit" value = "Buy Now"/>
</c:if>		
</form>
</div>
<br>
 <%} %>

</body>
</html>