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
<title>New Game</title>
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
		<h1 class="d-flex justify-content-center align-items-center" id="title">New Game</h1>
		<div class="border border-light mt-5 mb-5"></div>
		<div class="d-flex justify-content-center">
			<div class="mt-4 col-4" id="loginRegisForm">
				<form:form action="/game/new" method="post" modelAttribute="game">
				 	<form:hidden path="user" value="${user.id }"/>
				 	<form:hidden path="score" value="0"/>
					<div class="d-flex justify-content-center m-3">
						<form:label class="label" path="category">Choose a Category</form:label>
						<form:select path="category">
							<c:forEach var="eachCat" items="${catList}">
								<form:option value="${eachCat.id }">${eachCat.name}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div class="d-flex justify-content-center">
						<input type="submit" value="Play Ninja Trivia" class="button" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>