<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head >
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container text-center">
		<div>
			<h1>Welcome to User Registration Page</h1>
		</div>
		<div>
			<h3><a th:href="@{/register}">Register</a></h3>
			<h3><a th:href="/@{/users}">List of Users</a></h3>
			<h3><a th:href="/@{/login}">Login</a></h3>
		</div>
		
        <!-- <h3><a th:href="/@{/register}">Register</a></h3> -->
        
		
	</div>
</body>
</html>