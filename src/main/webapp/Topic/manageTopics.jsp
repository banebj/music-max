<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

.submitComment{
	padding-right:20px;
	padding-left:20px;
	padding-top:10px;
	padding-bottom:10px;
	width: 30%;
	font-weight: 900;
    background-color: #DE5555;
    color: white;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
}

#customers {
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #751313;}
#customers tr{background-color: #9E3A3A;}

#customers tr:hover {background-color: #946161;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: black;
  color: white;
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

.button {
  background-color: #FFDEAD;
  width: 100%;
  font-size: 20px;
  text-align: center;
  padding: 10px 58px;
  font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
}

.button:hover {
  background-color: #9B5151;
  color: white;
}
</style>

<meta charset="ISO-8859-1">
<title>Forum</title>
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

<c:if test="${!empty user}">
<a href="/Topic/addTopic.jsp" style="text-decoration:none;">
<div class="button">
Add Topic
</div>
</a>
</c:if>

<table id="customers">
  <tr>
    <th>Title</th>
	<c:if test="${!empty user}">
    <th></th>
	</c:if> 
  </tr>
  <c:forEach items="${topics}" var="t">
  <tr>
    <td>${t.title}</td>
	<c:if test="${!empty user}">
    <td>
    <form action="/topic/getAllCommentsByTopicId" method="GET">
		<input type="hidden" name="id" value="${t.id}"/> 
		<input class="submitComment" type="submit" value="Open discussion"/>
	</form>
	</td>
	</c:if>
  </tr>
  </c:forEach>
 </table>


</body>
</html>