<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title> HRPortal | Register </title>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
	<div>
		<div class="form-container">
		<%
				if(session.getAttribute("message")!=null){
			%>	
				<span style="color:red"><%= session.getAttribute("message") %></span>	
			<% 
				session.removeAttribute("message");
				} %>		
		<fieldset>
		  <legend>Register</legend> 
		  <form action="register" onsubmit="return validate()" method="post">
	  		  <table class="table">
	  		  <tbody>
			    <tr><td><label class="input-opt">User Id:</label></td><td><input id="user-id" required="required" name="userId" type="text"><br><span class="error" style="display: none;" id="error-userId">User Id should be between 5 to 50 characters long!</span></td></tr>
			    <tr><td><label class="input-opt">Password:</label></td><td><input id="password" required="required" name="password" type="password"><br><span class="error" style="display: none;" id="error-password">Password should be between 5 to 50 characters long!</span></td></tr>
			    <tr><td colspan="2" style="text-align: center;"><button style="border: 2px solid #85a3a7;" type="submit">Register</button></td></tr>
	  		  
	  		  </tbody>
			  </table>
		  </form>
		</fieldset>
		</div>
	</div>
	
	<!-- Script -->
	<script type="text/javascript">
		function validate(){
			let userId = document.getElementById("user-id").value;
			let password = document.getElementById("password").value;
			let errorUserId = document.getElementById("error-userId");
			let errorPassword = document.getElementById("error-password");
			console.log("User Id = ",userId," Password = ",password);
			if(userId.length<5 || userId.length>50){
				errorUserId.style.display = "inline";
				return false;
			}else{
				errorUserId.style.display = "none";
			}		
			if(password.length<5 || password.length>50){
				errorPassword.style.display = "inline";
				return false;
			}else{
				errorPassword.style.display = "none";
			}
			return true;
		}
	</script>
</body>
</html>