<%@page import="pojos.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.main-container {
	width: 40%;
	text-align: center;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	color: #007BFF;
}

p {
	color: #555;
}

a {
	color: #007BFF;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

footer {
	text-align: center;
	width: 100%;
	background-color: rgb(84, 147, 255);
	color: white;
	font-size: medium;
	position: fixed;
	bottom: 0;
}
</style>
</head>


<body>

	<%
	User loggedUser = (User) session.getAttribute("loggedInUser");
	%>
	<div class="main-container">
		<h1>Authentication Successful!</h1>
		<p>You have been logged in to the system.</p>
		<p>${sessionScope.loggedInUser}</p>
		<p>
			Return to <a href="loginPage.jsp">Login Page</a>
		</p>
	</div>
	<footer>
		<div>Designed by @Jayesh Murodiya</div>
	</footer>

</body>
</html>