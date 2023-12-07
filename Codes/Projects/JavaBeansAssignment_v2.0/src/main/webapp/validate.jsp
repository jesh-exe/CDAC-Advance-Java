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

<!-- This is the jsp action tag which is used for making Java Bean object  
	 It takes unique id, class fully qualified name and scope where its access limitation
	 should be.
	 By default scope is page-->
<jsp:useBean id="user" class="beans.UserBean" scope="session"></jsp:useBean>
<!-- Set Property of JSP Action Tag is used to call the setters of Java Bean class we have made in beans
	 package, we need to make useBean before making the set property.
	 it take property name (* for all the setter) or specific name can be given,
	 Other parameter it takes is the name of the useBean tag we made just above.
	 
	 ***** If we want to call all the setters, then the attribute name/value in the tag must 
	 	   be same in the Java beans class, or else it won't set the values properly.******* -->
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