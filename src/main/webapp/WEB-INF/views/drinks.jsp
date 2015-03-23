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
     <link href="/olakoa/resources/drinks.css" rel="stylesheet">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h1>Olakoa</h1>
			</div>
			<div class="col-md-6">
				<h1>Welcome ${user.firstName} ${user.lastName} <a href="/olakoa/logout" class="pull-right btn-sm btn-primary">logout</a></h1>
			</div>
		</div>
		<div class="row">
			<h1>Home</h1>
		</div>
		<div class="row">
			<c:choose>
				<c:when test="${user.role == 'USER'}">
					<h2>Sales list <a class="btn btn-primary" href="/olakoa/home/drinks/create" role="button">+</a></h2> 
				</c:when>
				<c:otherwise>
					<h2>Drinks</h2>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<tr>
						<c:choose>
							<c:when test="${user.role == 'USER'}">
								<% String order = request.getParameter("order");
									if(order != null && order.equals("DESC"))
										order = "ASC";
									else
										order = "DESC";
								%>
								<th><a href="/olakoa/home/drinks?sort=NAME&order=<%=order%>">Name</a></th>
								<th>Thumbnail</th>
								<th>Description</th>
								<th><a href="/olakoa/home/drinks?sort=COST&order=<%=order%>">Unit Cost</a></th>
								<th><a href="/olakoa/home/drinks?sort=POSTED&order=<%=order%>">Posted</a></th>
							</c:when>
							<c:otherwise>
								<th>Name</th>
								<th>Thumbnail</th>
								<th>Description</th>
								<th>Unit Cost</th>
							</c:otherwise>
						</c:choose>
					</tr>
					<c:forEach items="${drinks}" var="drink">
						<tr>
							<td>${drink.name}</td>
							<td><img class="img-responsive" src="${drink.thumbnail}" alt="${drink.name} image"></td>
							<td>${drink.description}</td>
							<td>$${drink.unitCost/100}</td>
							<c:if test="${user.role == 'USER'}">
								<td><input type="checkbox" <c:if test="${drink.posted}">checked</c:if> disabled></td>
							</c:if>
						</tr>			
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>