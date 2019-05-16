<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

.podNaslov{
	background-color: #FAEBD7;
	color:black;
	font-size: 100%;
}

.background1{
	margin-right: 70%;
	margin-left: 0%;
	padding:40px;
	background-color:#FFFAF0;
	border: 2px solid black;
	font-weight: 1000;
	font-style: italic;
}

.buttonSearch {
  background-color: #FFDEAD;
  width: 10%;
  font-size: 15px;
  text-align: center;
  padding: 5px 10px;
  margin: 15px;
  border-radius: 40%;
  font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
}

.buttonSearch:hover {
  background-color: #9B5151;
  color: white;
}

</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Comment</title>
</head>
<body>

<header>
	<div class="navbar" id="myNav">
    	<div>
        	<a href="/user/getUserWithMostSongsPosted">HOME</a>
          	<a href="/song/getAllSongs">MUSIC</a>
          	<a href="/Song/NewFile.jsp">FESTIVALS</a>
          	<a href="#">NEWS</a>
          	<a href="/topic/getAllTopics">FORUM</a>
        </div>
        <div class="nav-right">
        <c:if test="${!empty user}">
        	<a href="#">Profile</a>
            <a href="/user/logout">Logout</a>
        </c:if>
        <c:if test="${empty user}">
          	<a href="/unos/login.jsp">Sign in</a>
       		<a href="/unos/Registracija.jsp">Sign up</a>
       	</c:if>
        </div>
    </div>
</header>
<br><br><br><br>

<div class="background1">
	<div class="podNaslov">
	 	Category<br>
	   	${song.category.type}
	   	<br><br>
	   	Title<br>
	   	${song.title}
	   	<br><br>
	   	Author<br>
	   	${song.author}
	   	<br><br>
	   	Artist<br>
	   	${song.artist}
	   	<br><br>
	   	Release Year<br>
	   	${song.releaseYear}
	   	<br><br>
	</div>
	Comments<br>
	<c:forEach items="${comments}" var="c">
	${c.text}<br>
	Commented By: ${c.user.firstName} ${c.user.lastName}<br><br>
	</c:forEach>
	
	<div class="form">
		<div class="naslov">
			Add Comment<br>
		</div>
		<form action="/song/addCommentBySongAndUserId" method="POST">
			Text<br><input type="text" name="text"/><br> 
			<input type="hidden" name="song" value="${song.id}"/>
			<input type="hidden" name="user" value="${user.id}"/>
			<input type="submit" value="Add Comment"/>
		</form>
	</div>
	
</div>	

</body>
</html>