<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>     
<!DOCTYPE html>
<html>
<head>
    <title>Create</title>
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
				<h1>
					Welcome ${user.firstName} ${user.lastName} <a href="/olakoa/logout"
						class="pull-right btn-sm btn-primary">logout</a>
				</h1>
			</div>
		</div>
		<div class="row">
			<h1>User</h1>
		</div>
		<div class="row">
			<h2>Update Beverage</h2>
		</div>
		<form action="/olakoa/home/drinks/edit" method="GET">
			<div class="form-group">
				<label for="name">Name</label> 
				<input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="${detail.name}" required>
				<input type="hidden" id="did" name="did" value="${detail.id}" />
			</div>
			<div class="form-group">
				<label for="desc">Description</label> 
				<textarea class="form-control" rows="3" id="desc" name="desc" required>${detail.description}</textarea>
			</div>
			<div class="form-group">
				<label for="thumb">Thumbnail</label>
				<input type="url" class="form-control" id="thumb" placeholder="Thumbnail URL" name="thumb" value="${detail.thumbnail}" required>
			</div>
			<div class="form-group">
				<label for="cost">Unit Price</label>
				<input type="number" class="form-control" id="cost" placeholder="Unit price (cents)" name="cost" value="${detail.unitCost}" required>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" id="posted" name="posted" checked="${detail.posted}" >Posted</label>
			</div>
			<a class="btn btn-warning" href="/olakoa/user/drinks" role="button">Cancel</a>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</body>
</html>