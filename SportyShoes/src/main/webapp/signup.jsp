<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign up</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<br/>
<div class = "container">
<form name=frmLogin action="signin" method="post">
			<table border=1 cellspacing=2 cellpadding=4>
 				<tr>
 					<td width=25%>First Name*</td>
 					<td><input name=fname maxlength=50 required></td>
 				</tr>
 				<tr>
 					<td width=25%>Last Name*</td>
 					<td><input name=lname maxlength=50 required></td>
 				</tr>
 				<tr>
 					<td width=25%>Address</td>
 					<td><input name=address maxlength=150 required></td>
 				</tr>
 				
 				
 				<tr>
 					<td width=25%>Email id*</td>
 					<td><input type="email" name=emailId maxlength=50 required></td>
 				</tr>
 				<tr>
 					<td width=25%>Password*</td>
 					<td><input name=pwd maxlength=10 type="password"></td>
 				</tr>
 				<tr>
 					<td colspan=2><input type = "submit" value = "Register"/>
 						
 					</td>
 				</tr>
 			</table>
		</form>
		</div>
</body>
</html>