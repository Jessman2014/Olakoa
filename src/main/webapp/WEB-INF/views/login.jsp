<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Login</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
		<script src="login.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<h1>Please sign in</h1>
					<span class="label label-warning">Incorrect username or
						password. Please try again.</span>
					<form role="form" method="post" action="/tasker/Login">
						<div class="form-group">
							<input type="text" name="username" class="form-control"
								placeholder="Username" required>
						</div>
						<div class="form-group">
							<input type="password" name="password" class="form-control"
								placeholder="Password" required>
						</div>
						<button type="submit" class="btn">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>