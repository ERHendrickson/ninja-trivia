<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Regis</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="ninja container mt-5 p-5">
		<h1 class="d-flex justify-content-center align-items-center" id="title">Ninja Trivia</h1>		
		<div class="d-flex justify-content-around">
			<div id="loginRegisForm" class="col-4 mt-3">
				<h2 class="logRegisTitle">Register</h2>
				<form:form action="/regis" method="post" modelAttribute="newUser">
					<div>
						<label class="label">User Name:</label>
						<form:input class="form-control" path="userName"/>
						<form:errors class="text-danger" path="userName"/>
					</div>
					<div>
						<label class="label">Email:</label>
						<form:input class="form-control" path="email"/>
						<form:errors class="text-danger" path="email"/>
					</div>
					<div>
						<label class="label">Password:</label>
						<form:password class="form-control" path="password"/>
						<form:errors class="text-danger" path="password"/>
					</div>
					<div>
						<label class="label">Confirm Password:</label>
						<form:password class="form-control" path="confirm"/>
						<form:errors class="text-danger" path="confirm"/>
					</div>
					<div class="buttonContainer">
						<input type="submit" value="Register" class="button"/>
					</div>			
					
				</form:form>			
			</div>
			<div id="loginRegisForm" class="col-4 mt-3">
				<h2 class="logRegisTitle">Login</h2>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div>
						<label class="label">Email:</label>
						<form:input class="form-control" path="email"/>
						<form:errors class="text-danger" path="email"/>
					</div>
					<div>
						<label class="label">Password:</label>	
						<form:password class="form-control" path="password"/>
						<form:errors class="text-danger" path="password"/>
					</div>
					<div class="buttonContainer">
						<input type="submit" value="Login" class="button"/>										
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>