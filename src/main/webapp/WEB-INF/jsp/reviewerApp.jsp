<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
<b><h3>Application for Round 1</h3></b>
<table class="table table-bordered table table-striped">
<tr><th>Name</th><th>Profile</th><th>Review Application</th></tr>
<c:forEach items="${apps}" var="application">
<tr>
  <td>${application.applicant.firstName}</td><td><a href="<c:url value='/application/view.html?id=${application.id}' />">View</a></td><td><a href="<c:url value='/review.html?id=${application.id}' />">Review</a></td>
  
  </tr>
  </c:forEach>
  </table>
  
  <b><h3>Application for Round 2</h3></b>
<table class="table table-bordered table table-striped">
<tr><th>Name</th><th>Profile</th><th>Review Application</th></tr>
<c:forEach items="${apps2}" var="application">
<tr>
  <td>${application.applicant.firstName}</td><td><a href="<c:url value='/application/view.html?id=${application.id}' />">View</a></td><td><a href="<c:url value='/review.html?id=${application.id}' />">Review</a></td>
  
  </tr>
  </c:forEach>
  </table>
  <h3>Final Round</h3>
  <table class="table table-bordered table table-striped">
<tr><th>Name</th><th>Profile</th><th>Review Application</th></tr>
<c:forEach items="${apps3}" var="application">
<tr>
  <td>${application.applicant.firstName}</td><td><a href="<c:url value='/application/view.html?id=${application.id}' />">View</a></td><td><a href="<c:url value='/rank.html?id=${application.id}' />">Rank</a></td>
  
  </tr>
  </c:forEach>
  </table>
  </div>
</body>
</html>