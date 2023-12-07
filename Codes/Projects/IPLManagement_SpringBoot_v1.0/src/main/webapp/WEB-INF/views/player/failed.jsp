<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Failed</title>
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
	color: red;
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

button {
	width: 30%;
	background: #1976D2;
	color: #fff;
	padding: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background: #1565C0;
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
	<div class="logout-container">
		<h1>Registration Failed!</h1>
		<p> You are not registered to the team <br> </p>
		<div style="color: red;"> Reason: ${requestScope.errorMsg} </div>
		<p>Thank you for using our services.</p>
		<p>
			<button onclick="history.back()">Go Back</button>
		</p>
	</div>
	<footer>
		<div>Designed by @Jayesh Murodiya</div>
	</footer>
</body>

</html>
