<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
    <title>HRMPortal | Employee Details</title>
    
    <!-- styles -->	
    <style>
	    *{
	    box-sizing: border-box;
	    -webkit-box-sizing: border-box;
	    -moz-box-sizing: border-box;
		}
		body{
		    font-family: Helvetica;
		    -webkit-font-smoothing: antialiased;
		}
		/* Table Styles */
		.edit-table td{
			padding: 10px;
		}
		.upload-grid{
			display: none;
		}
		.table-wrapper{
		    margin: 30px 70px 70px ;
		    border:1px solid black;
		    padding:20px;
		    box-shadow: 0px 35px 50px rgba( 0, 0, 0, 0.2 );
		    overflow-y: auto; 
		}
		.btn-grid{
		display:flex;
		justify-content: flex-end;
		margin-bottom: 10px
		}
		
		.fl-table {
		    border-radius: 5px;
		    font-size: 12px;
		    font-weight: normal;
		    border: none;
		    border-collapse: collapse;
		    width: 100%;
		    max-width: 100%;
		    white-space: nowrap;
		    background-color: white;
		}
		
		.fl-table td, .fl-table th {
		    text-align: center;
		    padding: 8px;
		}
		
		.fl-table td {
		    border-right: 1px solid #f8f8f8;
		    font-size: 12px;
		}
		
		.fl-table thead th {
		    color: #ffffff;
		    background: #4FC3A1;
		}
		
		
		.fl-table thead th:nth-child(odd) {
		    color: #ffffff;
		    background: #324960;
		}
		
		.fl-table tr:nth-child(even) {
		    background: #F8F8F8;
		}
		
		.fl-table thead th{
			  position: sticky;
			  top: 0;
		}
		
		/* Responsive */
		
		@media (max-width: 767px) {
		    .fl-table {
		        display: block;
		        width: 100%;
		    }
		    .table-wrapper:before{
		        content: "Scroll horizontally >";
		        display: block;
		        text-align: right;
		        font-size: 11px;
		        color: white;
		        padding: 0 0 10px;
		    }
		    .fl-table thead, .fl-table tbody, .fl-table thead th {
		        display: block;
		    }
		    .fl-table thead th:last-child{
		        border-bottom: none;
		    }
		    .fl-table thead {
		        float: left;
		    }
		    .fl-table tbody {
		        width: auto;
		        position: relative;
		        overflow-x: auto;
		    }
		    .fl-table td, .fl-table th {
		        padding: 20px .625em .625em .625em;
		        height: 60px;
		        vertical-align: middle;
		        box-sizing: border-box;
		        overflow-x: hidden;
		        overflow-y: auto;
		        width: 120px;
		        font-size: 13px;
		        text-overflow: ellipsis;
		    }
		    .fl-table thead th {
		        text-align: left;
		        border-bottom: 1px solid #f7f7f9;
		    }
		    .fl-table tbody tr {
		        display: table-cell;
		    }
		    .fl-table tbody tr:nth-child(odd) {
		        background: none;
		    }
		    .fl-table tr:nth-child(even) {
		        background: transparent;
		    }
		    .fl-table tr td:nth-child(odd) {
		        background: #F8F8F8;
		        border-right: 1px solid #E6E4E4;
		    }
		    .fl-table tr td:nth-child(even) {
		        border-right: 1px solid #E6E4E4;
		    }
		    .fl-table tbody td {
		        display: block;
		        text-align: center;
		    }
			}  
        </style>
	</head>
	
	<!-- body -->
	<body>
	<%@include file="header.jsp" %>
	<%@include file="footer.jsp" %>
	<div id="edit-details" style="display:none;"> 
				<div class="form-container">		
				<a id="back" href="#">&#8592; back</a>
				<fieldset>
				  <legend>Edit details</legend> 
				  <form action="update" method="post">
				  	  <table class="edit-table">
					    <tr><td><label class="input-opt">Code:</label></td><td><input readonly="readonly" id="edit-code" name="empCode" type="text"></td></tr>
					    <tr><td><label class="input-opt">Name :</label></td><td><input required="required" maxlength="100" id="edit-name" name="empName" type="text"></td></tr>
					    <tr><td><label class="input-opt">DOB:</label></td><td><input id="edit-dob" required="required" name="empDOB" type="date"></td></tr>
					    <tr><td><label class="input-opt">Email:</label></td><td><input id="edit-email" maxlength="100" required="required" name="empEmail" type="email"></td></tr>
					    <tr><td><label class="input-opt">Location:</label></td><td><input id="edit-location" required="required" name="empLocation" maxlength="500"  type="text"></td></tr>
					    <tr><td colspan="2" style="text-align: center;"><button type="submit" >Submit</button></td></tr>
					  </table>
				  </form>
				</fieldset>
				</div>
			</div>
	<div id="table-details" class="table-wrapper">
		<div id="upload" class="upload-grid">
			<form action="upload" method="post" enctype="multipart/form-data">
				<input name="file"  type="file" accept=".csv" />
				<button type="submit">Upload</button>
			</form>
		</div>
		<div class="btn-grid">
			<button id="upload-btn">Upload Employee Details</button>
			<form action="http://localhost:9090/api/v1/employees/export" method="get">
				<button type="submit" style="margin-left: 5px">Download Employee Details</button>	
		</form>
		</div>
	    <table class="fl-table">
	        <thead>
	        <tr>
	            <th>Employee Code</th>
	            <th>Employee Name</th>
	            <th>Location</th>
	            <th>Email</th>
	            <th>Date of Birth</th>
	            <th>Action</th>
	        </tr>
	        </thead>
	        <tbody>
	        <%
	        	JSONArray js=(JSONArray)session.getAttribute("employeesData");
	        	for(int i=0;i<js.size();i++){
	        		JSONObject obj = (JSONObject)js.get(i);	
	        %>
	        <tr>
	            <td name="employeeCode"><%= obj.get("empCode") %></td>
	            <td name="name"><%= obj.get("empName") %></td>
	            <td name="location"><%= obj.get("empLocation") %></td>
	            <td name="email"><%= obj.get("empEmail") %></td>
	            <td name="dob"><%= obj.get("empDOB") %></td>
	            <td><button class="edit-btn">edit</button></td>
	        </tr>
	       <% } %>
	        <tbody>
	    </table>
	</div>
	
	<!-- script -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
		let editBtn = $(".edit-btn");
		editBtn.on("click",onEditHandler);
		function onEditHandler(obj) {
			$("#table-details").toggle();
			$("#edit-details").toggle();
			let inputCode = $("#edit-code");
			let inputName = $("#edit-name");
			let inputEmail = $("#edit-email");
			let inputLocation = $("#edit-location");
			let inputDOB = $("#edit-dob");
			
			let x = $(this).parent().parent() ;
			inputCode.val(x.find('td[name="employeeCode"]').html());
		
			inputName.val(x.find('td[name="name"]').html());
			inputLocation.val(x.find('td[name="location"]').html());
			inputEmail.val(x.find('td[name="email"]').html());
			inputDOB.val(x.find('td[name="dob"]').html());
			
			console.log("Object >> ",obj);
		};
		
		$("#back").on("click",function(){
			$("#table-details").toggle();
			$("#edit-details").toggle();
		});
		
		$("#download").on("click",function(){
			console.log("in method");
			$.get("http://localhost:9090/api/v1/employees/export", function (data, textStatus, jqXHR) {
		          alert('status: ' + textStatus + ', data:' + data);
		    });
		});
		
		$("#upload-btn").on("click",function(){
			$("#upload").toggle();
		});
	});
		
	</script>
    </body>
</html>