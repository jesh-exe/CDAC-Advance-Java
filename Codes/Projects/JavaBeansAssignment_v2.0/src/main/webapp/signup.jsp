<%@page import="java.sql.SQLException"%>
<%@page import="beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
</head>

<body>
<jsp:useBean id="user" class="beans.UserBean" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>
<%	
	UserBean usd =  (UserBean)pageContext.getAttribute("user");
	boolean userAdded = usd.addVoter();
	if(!userAdded)
	{
		%> <h4>Not Added!</h4> <%
	}
	else
	{	
		response.sendRedirect("registered.html");
	}
%>
<%
	
%>
</body>

</html>