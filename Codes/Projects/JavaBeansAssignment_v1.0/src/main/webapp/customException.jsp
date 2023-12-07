<!-- isErrorPage attribute is used to tell Web Container that the page will catch all the excpetions defines in xml file -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
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
            color: #ff0000;
            text-decoration: underline;
        }

        p,
        div{
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
            color: rgb(255, 255, 255);
            font-size: medium;
            position: fixed;
            bottom: 0;
        }
    </style>
</head>

<body>
    <div class="logout-container">
        <h1>Failed To Register!</h1>
        <div style="font-weight: bolder;">Reason: ${pageContext.exception.message} </div>
        <div>Please Retry!.</div>
        <p><button type="button" onclick="history.back()">Go Back</button></p>
    </div>
    <footer>
        Designed by @Jayesh Murodiya
    </footer>
</body>

</html>