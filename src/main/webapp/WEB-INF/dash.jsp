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
<title>Dashboard</title>
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
		<h1 class="d-flex justify-content-center align-items-center" id="title">Dashboard</h1>
		<div class="border border-light mt-5 mb-5"></div>
		<div class="mt-3 m-auto w-25" id="dashboard">
			<div>
				<a class="dashAnc" href="/game/new"><button class="btn button">New Game</button></a>
			</div>
			<div>
				<a class="dashAnc" href="/quest/new"><button class="btn button">Create Quest</button></a>
			</div>
			<div>
				<a class="dashAnc" href="/leaderboard"><button class="btn button">Leader Board</button></a>
			</div>
			<div>
				<a class="dashAnc" href="/logout"><button class="btn button">Logout</button></a>
			</div>		
		</div>		
	</div>
</body>
</html>