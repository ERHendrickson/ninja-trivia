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
<title>Game Over</title>
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
	<a href="/game/dash"><button class="button">Dashboard</button></a>
	<a href="/logout"><button class="button">Logout</button></a>
	<div class="border border-light mt-5 mb-5"></div>
		<div class="row">
			<div class="label col-4">
				<h1>Game Over!</h1>
				<h3>Final Score</h3>
				<h2>${game.score}</h2>
			</div>
			<div class="label col-4">
				<h2>High Scores for ${game.category.name}</h2>
				<table class="table label">
					<thead>
						<tr>
							<td>User</td>
							<td>Score</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="eachGame" items="${allGames }">
							<tr>
								<td>${eachGame.user.userName}</td>
								<td>${eachGame.score }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>