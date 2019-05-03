<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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


/*Form*/
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

/*Input boxes*/
select{
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

option{
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

input[type=number]{
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

/*Titles*/
.naslov{
	color:white;
	font-size: 150%;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Song</title>
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
        <a href="#">Sign in</a>
        <a href="#">Sign up</a>
       </div>
    </div>
</header>

<br><br><br><br>

	<div class="form">
		<div class="naslov">
			Add Song<br>
			${message}<br>
		</div>
		<form action="/song/addSong" method="POST">
			Select Category<br>
			<select name="categoryId">
  				<c:forEach items="${categories}" var="c">
 					 <option value="${c.id}">${c.type}</option>
  				</c:forEach>
			</select><br>
			${message1}<br>
			Title<br><input type="text" name="title" value="${song.title}"/><br> 
			${message2}<br>
			Author<br><input type="text" name="author" value="${song.author}"/><br><br>
			Artist<br><input type="text" name="artist" value="${song.artist}"/><br><br>
			Release Year<br><input type="number" min="1950" max="2019" value="2019" name="releaseYear" path="releaseYear"/><br>
			<input type="submit" value="Add Song"/>
		</form>
	</div>

</body>
</html>