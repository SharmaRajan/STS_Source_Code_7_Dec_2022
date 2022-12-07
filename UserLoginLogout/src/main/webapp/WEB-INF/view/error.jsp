<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ page import =  "java.util.HashMap,java.util.TreeMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 

<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="ISO-8859-1">
	<title>Sorry!! Page not Found...</title>
	<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container p-3 text-center" >
		<img src="<%=request.getContextPath()%>/img/error-4.jpg" class="img-fluid" />
		<h1 class="display-3">
			Sorry! Page not Found
		</h1>
		
		<a class="btn btn-outline-primary mt-3" th:href="@{/}">Home Page</a>
	</div>
</body>
</html>