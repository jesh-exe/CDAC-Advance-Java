<%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%>
<%@page import="java.util.HashMap"%>
<%@page import="pojos.User"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authentication</title>

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

        footer{
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

<%!Map<String, User> map = new HashMap<>();

	public void jspInit() {
		System.out.println("Inside INIT of authenticate!");
		map.put("jrmurodiya", new User("jrmurodiya", "jay@123", 24));
		map.put("ayushi", new User("ayushi", "ayu@123", 21));
		map.put("arpit", new User("arpit", "arp@123", 18));
		map.put("kartik", new User("kartik", "kar@123", 24));
	}%>

<body>

	<%
	System.out.println("Inside Service of authenticate");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	User enteredUser = map.get(username);
	if (enteredUser != null) {
		if (enteredUser.getPassword().equals(password)) {
			session.setAttribute("loggedInUser", enteredUser);	
			response.sendRedirect("showUser.jsp");
		}
		else
		{
			%>
			<div class="main-container">
				<h1>Password Not Correct!</h1>
				<p>
					You have entered the wrong password for the Username: <% out.print(username.toUpperCase()); %> </p>
				<p>Please enter the correct Password!.</p>
				<p>
					Return to <a href="loginPage.jsp">Login Page</a>
				</p>
			</div>
			<%
		}
	}
	else
	{
		%>
		<div class="main-container">
			<h1> Invalid User!</h1>
			<p>
				Entered Username and Password does not match any User.
			</p>
			<p>New to the Portal?</p>
			<p>
				Click to <a href="register.jsp">Sign Up</a> or <a href="loginPage.jsp">Login</a>
			</p>
		</div>
		<%
	}
		%>
	
	<footer>
		<div>Designed by @Jayesh Murodiya</div>
	</footer>

</body>
</html>




