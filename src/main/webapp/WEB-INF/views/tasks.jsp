<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>     
<%@ page import="com.hunt.tasker.tasks.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task List</title>
    <script src="https://code.jquery.com/jquery-2.1.3.js"></script>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<h1>Welcome ${user.name} <a href="logout" class="pull-right btn-sm btn-primary">logout</a></h1>
	<br>
	<form class="form-inline" action="tasks/create" method="POST">		
		<input type="text" placeholder="description" name="description" class="form-control">
		<input type="color" value="black" name="color" class="form-control" style="min-width:50px;">
		<input type="date" placeholder="due date" name="due" class="form-control">
		<input type="hidden" value="false" name="completed">
		<input type="submit" class="form-control">
	</form>
	
	<br/>
	
	<table class="table table-striped">
		<tr><th>Description</th><th>Color</th><th>Due</th><th>Delete</th></tr>
		<c:forEach items="${tasks}" var="task">
			<tr><td>${task.description}</td><td><input type="color" value="${task.color}" disabled></td><td>${task.due}</td><td><a href="tasks/delete?tid=${task.id}" class="btn-xs btn-warning">X</a></td></tr>			
		</c:forEach>
	</table>
	</div>
</body>
</html>