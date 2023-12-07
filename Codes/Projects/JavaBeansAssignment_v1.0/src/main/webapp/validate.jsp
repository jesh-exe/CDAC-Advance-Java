<%@page import="beans.UserBean"%>
<%@page import="pojos.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Validate User</title>
</head>
<body>

<jsp:useBean id="user" class="beans.UserBean" scope="session"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>

<!-- 
${sessionScope.user.setEmail(param.email)}
${sessionScope.user.setPassword(param.password)}
 -->
 
<% 
	User verifiedUser = ((UserBean)session.getAttribute("user")).authenticate();
	if(verifiedUser==null)
		response.sendRedirect("invalidUserPage.html");		
	else if(verifiedUser.getRole().equals("admin"))
	{
		%> <h1>You are Administrator!</h1> <%
		out.print(verifiedUser);
	}
	else{
		%> <h1>You are Voter!</h1> <%
		out.print(verifiedUser);
	}
%>

</body>
</html>