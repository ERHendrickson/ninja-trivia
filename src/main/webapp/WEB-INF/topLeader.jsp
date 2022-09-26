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
<title>Leader Board</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<div>
			<h1 class="label">${cat.name }</h1>
			<div>
			</div>
			<table class="table table dark label">
				<thead>
					<tr>
						<th>User</th>
						<th>Score</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="eachGame" items="${leaders}">
						<tr>
							<td>${eachGame.user.userName }</td>
							<td>${eachGame.score}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/game/dash"><button class="button">Dashboard</button></a>
			<a href="/logout"><button class="button">Logout</button></a>
		</div>
	</div>
</body>
</html>