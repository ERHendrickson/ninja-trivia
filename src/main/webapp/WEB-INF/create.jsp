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
<title>Create Game</title>
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
		<h1 class="d-flex justify-content-center align-items-center" id="title">Create Game</h1>
		<div class="d-flex justify-content-center mt-2">
			<a href="/game/dash"><button class="button">Dashboard</button></a>
			<a href="/logout"><button class="button">Logout</button></a>		
		</div>
			<div class="d-flex justify-content-center">
				<div id="loginRegisForm" class="col-4 mt-3">
					<h3 class="logRegisTitle">MAKE A CATEGORY!</h3>
					<form:form action="/cat/new" method="post" modelAttribute="cat">
						<div>
							<form:label class="label" path="name">Name:</form:label>
							<form:input class="form-control" path="name"/>
							<form:errors class="text-danger" path="name"/>
						</div>
						<input type="submit" value="Add Category"  class="button mt-3"/>
					</form:form>
				</div>	
			</div>
			<div class="d-flex justify-content-center">
				<div id="loginRegisForm" class="col-4 mt-3">
					<h3 class="logRegisTitle">ASK A QUESTION!</h3>
					<form:form  action="/quest/new" method="post" modelAttribute="quest">
						<div>
							<form:label class="label" path="category">Category</form:label>
							<form:select class="form-control" path="category">
								<c:forEach var="eachCat" items="${catList }">
									<form:option value="${eachCat.id }">${eachCat.name }</form:option>
								</c:forEach> 
							</form:select>
						</div>
						<div>
							<form:label class="label" path="prompt">Prompt:</form:label>
							<form:input class="form-control" path="prompt"/>
							<form:errors class="text-danger" path="prompt"/>
						</div>
						<div>
							<form:label class="label" path="answer">Answer:</form:label>
							<form:input class="form-control" path="answer"/>
							<form:errors class="text-danger" path="answer"/>
						</div>
						<input type="submit" value="Add Quest" class="button m-3"/>
					</form:form>
				</div>
			</div>
			<div class="d-flex justify-content-center">
				<div id="loginRegisForm" class="col-4 mt-3">
					<h3 class="logRegisTitle">ADD A POSSIBLE</h3>
					<form:form action="/choice/new" method="post" modelAttribute="choice">
						<div>
							<form:label class="label" path="question">Question</form:label>
							<form:select class="form-control" path="question" id="question">
								<c:forEach var="eachQuest" items="${questList }">
									<form:option value="${eachQuest.id}">${eachQuest.prompt }</form:option>
								</c:forEach>
							</form:select>		
						</div>
						<div>
							<form:label class="label" path="possible">Possible</form:label>
							<form:input class="form-control" path="possible"/>
							<form:errors class="text-danger" path="possible"/>
						</div>
						<input type="submit" value="Add Possible" class="button mt-3"/>
					</form:form>
				</div>
			</div>
		</div>
</body>
</html>