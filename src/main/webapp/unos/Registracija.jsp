<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
	<form action="/users/save" method="post">
		<div class="kontejner">
			<p>Ime: <input name="first_name"></p>
			<p>Prezime: <input name="last_name"></p>
			<p>Email: <input name="email"></p>
			<p>Username: <input name="username"></p>
			<p>Password:<input type="password" name="password"></p>
			<input type="submit" value="Sacuvaj">
		</div>
	</form>
</body>
</html>