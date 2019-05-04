<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<style>
body { 
  margin: 0;
  font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
  background-image: url("../images/musicmax1.jpg");
}

header {
  background-color: #333333;
  position: fixed;
  width: 100%;
  z-index: 5;
}

nav {
  margin: 0;
  padding: 20px 0;
}

nav li {
  display: inline-block;
  width: 80px;
  color: #fff;
  font-weight: 600;
  font-size: 12px;
}

.navbar {
    background-color: #333;
    position: fixed;
    top: 0;
    width: 100%;
    transition: top 0.3s;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
}

.navbar a {
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 15px;
    text-decoration: none;
}

.navbar a:hover {
    background-color: #ddd;
    color: black;
    text-decoration: none;
}

.nav-right {
  float: right;
}

.form{
	margin: auto;
    width: 60%;
    text-align: center;
	font-weight: 1000;
	font-style: italic;
	background-color: black;
	color:white;
	margin: auto;
    width: 25%;
    border: 10px solid white;
}

input[type=text]{
    width: 50%;
	background-color: white;
    padding: 12px 10px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
	color:black;
	font-style: italic;
	font-weight: 900;
	font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
}

input[type=password]{
    width: 50%;
	background-color: white;
    padding: 12px 10px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
	color:black;
	font-style: italic;
	font-weight: 900;
	font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
}

input[type=submit]{
	padding-right:20px;
	padding-left:20px;
	padding-top:10px;
	padding-bottom:10px;
	width: 90%;
	font-weight: 900;
    background-color: #CC6600;
    color: white;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
}

input[type=submit]:hover {
    background-color: #FFDEAD;
}

.naslov{
	color:white;
	font-size: 150%;
}
</style>

<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

  <header>
		<div class="navbar" id="myNav">
        <div>
          <a href="/home.jsp">HOME</a>
          <a href="/song/getAllSongs">MUSIC</a>
          <a href="/Song/NewFile.jsp">FESTIVALS</a>
          <a href="#">NEWS</a>
          <a href="#">FORUM</a>
        </div>
        <div class="nav-right">
          <a href="/unos/login.jsp">Sign in</a>
          <a href="/unos/Registracija.jsp">Sign up</a>
        </div>
    </div>
  </header>
 <br><br><br><br>

<div class="form">
	<div class="naslov">
		Login<br>
	</div>
	${messagee}
	<br><br>
	<form action="/user/login" method="post">
		Username<br><input type="text" name="username"><br>
		Password<br><input type="password" name="password"><br>
		<input type="submit" value="Login">
	</form>
	</div>

</body>
</html>