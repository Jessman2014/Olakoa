<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>     
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Olakoa Login</title>
<script src="https://code.jquery.com/jquery-2.1.3.js"
	type="text/javascript"></script>
<link href="resources/drinks.css" rel="stylesheet">
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"
	type="text/javascript"></script>
</head>
<body>
	<div class="container">


		<form class="form-signin" action="login" method="POST">
			<h2 class="form-signin-heading">Please sign in</h2>
			<c:if test="${error}">
				<div class="label-md label-warning" style="margin:1em;padding:.5em;"><i>Unknown username, password combination. Please re-enter.</i></div>
			</c:if>			
			<label for="username" class="sr-only">Username</label> 
			<input type="text" id="username" class="form-control" placeholder="username" required autofocus name="username"> 
			<label for="inputPassword" class="sr-only">Password</label> 
			<input type="password" id="inputPassword" class="form-control" placeholder="password" required name="password">
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign	in</button>
		</form>
	</div>
</body>
</html>
