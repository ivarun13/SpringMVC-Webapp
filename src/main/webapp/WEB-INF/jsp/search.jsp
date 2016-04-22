<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Search</title>
</head>
<body>
<form action="search.html" method="get">
<table>
<tr>
<td>Search Job:</td><td><input type="text" name="term" style="width: 100%;" /></td>
</tr>
<tr>
<td colspan="2"><input type="submit" name="Search" value="Search" /></td>
</tr>
</table>
<input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
<br>

<c:if test="${not empty jobs}">
<div class="container col-md-4">
<table class="table table-bordered table table-striped">
<c:forEach items="${jobs}" var="job">
<tr>
<td>${job.title}</td></tr>
</c:forEach>
</table>
</div>
</c:if>
</body>
</html>