<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- Styles -->
<style type="text/css">

body{
	margin:0;
	padding:0;
	 
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color:#232f69;
  height:50px;
}

li {
  float: left;
}

li a {
  display: flex;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}
.error{
	color:red;
	width: 100%;
	font-size: 12px;
}
.header_logo{
	font-size: 25px;
	font-weight: bolder;
	font-family: monospace;
}
.header_option{
	float: right;
	font-size: 18px;
	font-weight: 600;
	font-family: monospace;
}
.form-container{
  font-family: 'Averia Serif Libre';
  margin: auto; 
  margin-top: 25vh;
  width: 450px;
  height: auto;
}
.table{
	width:100%;
	height:150px;
}
input{ border: 1px solid black; }
td:first-child{ width: 120px; }
.input-opt{
	background-color: #dddeee;
	padding: 5px;
	font-weight: bold;
}
fieldset {
	border-color: #d6d7ef; 
	border-radius: 4px;
}
legend {
	color: blue;
	font-weight: bold;
}
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: #232f69;
   font-family:monospace;
   color: white;
   text-align: center;
}
</style>
</head>

<!-- body -->
<body>
<ul>
  <li>
  <a href="#home" class="header_logo">HRMPortal
  <%
  	if(session.getAttribute("loggedUser")!=null){ %>
  		, Welcome ${sessionScope.loggedUser.getUserId()}
  <% }
  %>
  </a>
  </li>
  <% if(session.getAttribute("loggedUser")==null) { %>
  <li class="header_option"><a href="/HRMPortal/login.htm">Login</a></li>
  <li class="header_option"><a  href="/HRMPortal/register.htm">Register</a></li>
  <%}else{ %>
  <li class="header_option"><form action="logout" method="post"><button type="submit">Logout</button></form></li>
  <%} %>
</ul>
</body>

</html>