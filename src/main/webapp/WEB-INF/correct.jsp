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
<title>Insert title here</title>
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
		<div id="loginRegisForm">
			<h1 class="label d-flex justify-content-center">Correct!</h1>
			<div class="d-flex justify-content-center">
				<p class="label">Question:</p>			
				<p class="label">${quest.prompt}</p>
			</div>
			<div class="d-flex justify-content-center">
				<p class="label">Answer:</p>						
				<p class="label">${quest.answer}</p>
			</div>
			<div class="d-flex justify-content-center">
				<p class="label">Score:</p>
				<p class="label">${game.score}</p>
			</div>
			<div class="d-flex justify-content-center">
				<a href="/quest/${game.id}"><button class="button">Next Question</button></a>			
			</div>
		</div>
	</div>
</body>
</html>