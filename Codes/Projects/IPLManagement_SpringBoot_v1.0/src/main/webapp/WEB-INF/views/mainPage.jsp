<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IPL Team Management</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: 'Segoe UI';
	background: #f2f2f2;
	color: #000000;
}

.container {
	/* width: 30%; */
	max-width: 400px;
	margin: 50px auto;
	background: rgba(255, 255, 255, 0.8);
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	display: flex;
	flex-direction: column;
	justify-content: center;
}

h1 {
	text-align: center;
	color: #1976D2;
}

form {
	display: grid;
	gap: 15px;
}

label {
	font-weight: bold;
	font-size: small;
}

input, select {
	width: 100%;
	padding: 10px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-family: 'Segoe UI';
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

#btn-cnt {
	display: flex;
	justify-content: center;
}

@media screen and (max-width: 600px) {
	.container {
		padding: 10px;
		width: 80%;
	}
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
	<div class="container">
		<h1>IPL Team Management</h1>
		<form action="player/add" method="post">
			
			<label for="team">Select Team:</label> 
			<select id="team" name="team">
			
			<!-- FOR EACH LOOP USING JSTL -->
			
				<c:forEach items= "${requestScope.abbrv}" var="item">
					<c:out value="${item}"></c:out>
					<option value= "${item}" >${item}</option>
				</c:forEach>
				
					
			</select> 
			
			<label for="name">Player Name:</label> 
			<input type="text" id="name" name="name" required> 
			<label for="dob">Date of Birth:</label> 
			<input type="date" id="dob" name="dob" required> 
			<label for="battingAvg"> Batting Average:</label> 
			<input type="number" id="battingAvg" name="battingAvg" step="0.01" min="0" max="100" required> 
			<label for="wickets">Wickets Taken:</label> 
			<input type="number" id="wickets" name="wickets" min="0" required>

			<div id="btn-cnt">
				<button type="submit">Add Player</button>
				&nbsp; &nbsp;
				<button type="reset">Clear</button>
			</div>
		</form>
	</div>
	<footer>
		<div>Designed by @Jayesh Murodiya</div>
	</footer>
</body>
</html>