<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>     
<%@ page import="com.dahirkanehl.olakoa.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Drink List</title>
    <script src="https://code.jquery.com/jquery-2.1.3.js"></script>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h1>Olakoa</h1>
			</div>
			<div class="col-md-6">
				<h1>Welcome ${user.firstName} ${user.lastName} <a href="logout" class="pull-right btn-sm btn-primary">logout</a></h1>
			</div>
		</div>
		<div class="row">
			<h1>Home</h1>
		</div>
		<div class="row">
			<h2>Sales list <a class="btn btn-primary" href="/home/drinks/create" role="button">+</a></h2> 
		</div>
	
	<table class="table table-striped">
		<% String order = request.getParameter("order");
			if(order == null)
				order = "DESC";
		%>
		<tr>
			<th><a href="/home/drinks?sort=NAME&order=${order}">Name</a></th>
			<th>Thumbnail</th>
			<th>Description</th>
			<th><a href="/home/drinks?sort=COST&order=${order}">Unit Cost</a></th>
			<th><a href="/home/drinks?sort=POSTED&order=${order}">Posted</a></th>
		</tr>
		<c:forEach items="${drinks}" var="task">
			<tr>
				<td>${drink.name}</td>
				<td><img src="${drink.thumbnail}" class="img-responsive" alt="${drink.name} image"></td>
				<td>\$${drink.description}</td>
				<td>${drink.unitCost}</td>
				<td><input type="checkbox" value="${drink.posted}" disabled></td>
			</tr>			
		</c:forEach>
	</table>
	</div>
</body>
</html>