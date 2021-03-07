<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
 <jsp:include page="header.jsp"></jsp:include>
 <div class = "container">
    	${error}
    	<form name=frmLogin action="loginaction" method="post">
				
			<table border=1 cellspacing=2 cellpadding=4>
			
				
 				<tr>
 					<td width=25%>Email id*</td>
 					<td><input name=emailId maxlength=50></td>
 				</tr>
 				<tr>
 					<td width=25%>Password*</td>
 					<td><input name=pwd maxlength=10 type="password"></td>
 				</tr>
 				<tr>
 					<td colspan=2><input type = "submit" value = "Login"/>
 						<a href="signup">Not a member? Signup</a>
 					</td>
 				</tr>
 			</table>
		</form>
		</div>
    </body>
</html>
