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

</style>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <header>
		<div class="navbar" id="myNav">
        <div>
          <a href="home.jsp">HOME</a>
          <a href="/song/getAllSongs">MUSIC</a>
          <a href="/Song/NewFile.jsp">FESTIVALS</a>
          <a href="#">NEWS</a>
          <a href="#">FORUM</a>
        </div>
        <div class="nav-right">
          <a href="#">Profile</a>
          <a href="index.html">Logout</a>
        </div>
    </div>
  </header>

</body>
</html>