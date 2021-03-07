<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes</title>
</head>
<body>
<jsp:include page="customer-header.jsp"></jsp:include>

<br><br>
<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Order ID</b></td>
 		<td><b>Product</b></td>
 		<td><b>Price</b></td>
 		<td><b>Quantity</b></td>
 	</tr>
 	<c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.orderId }</td>
 			<td>${mapItems.get(item.orderId)}</td>
 			<td>${item.price }</td>
 			<td>${item.qty }</td>
 	  	</tr>
 	  </c:forEach>
</table> 		

<br>

</body>
</html>