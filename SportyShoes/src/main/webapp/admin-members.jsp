<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>

<jsp:include page="admin-header.jsp" ></jsp:include>

<form action = "searchuser">
	Search User: <input type = "text" name = "username"/>
	<input type = "submit" value = "Search"/>
</form>
<br/>
<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>User Id</b></td>
 		<td><b>Name</b></td>
 		<td><b>Email</b></td>
 		<td><b>Address</b></td>
 	</tr>
 	<c:forEach items="${list}" var="item">
 	  	<tr>
 	  		<td>${item.userId}</td>
	 		<td>${item.fname}&nbsp;${item.lname }</td>
 			<td>${item.email}</td>
 			<td>${item.address}</td>
 			
 	  	</tr>
 	  </c:forEach>
</table> 		
</body>
</html>