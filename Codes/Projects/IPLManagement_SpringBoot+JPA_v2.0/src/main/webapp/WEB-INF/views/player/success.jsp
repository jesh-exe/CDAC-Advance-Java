<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Player Added!</title>
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

        .logout-container {

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

<body>
<!-- For definition go to mainPage.jsp
	 Because of spring tags, we can not use / as a root path -->
<spring:url var="url" value="/"></spring:url>

    <div class="logout-container">
        <h1>Player Added Successfully!</h1>
        <p>Congratulations! You have been added to TEAM: ${sessionScope.playerTeam}]</p>
        <p>Thank you for using our services.</p>
        <p>Return to <a href="${url}">Main Page</a></p>
    </div>
    <footer>
		<div>Designed by @Jayesh Murodiya</div>
	</footer>
</body>

</html>
