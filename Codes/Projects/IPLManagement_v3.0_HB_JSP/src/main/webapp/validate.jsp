<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authentication Page</title>
</head>

<body>
<jsp:useBean id="playBean" class="beans.PlayerBean" scope="session"></jsp:useBean>
<jsp:setProperty property="*" name="playBean" />
<c:redirect url="${sessionScope.playBean.authenticateAndAddPlayer()}.jsp"></c:redirect>
</body>
</html>