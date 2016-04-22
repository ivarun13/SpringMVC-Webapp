<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>CS Jobs - Reviewer</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>

<p>You are logged in as <em><security:authentication property="principal.name" /></em>.</p>
<form action="<c:url value='/logout' />" method="post">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input name="submit" type="submit" value="Logout" />
</form>
<p>
  <security:authorize access="hasRole('ROLE_ADMIN')">
    [<a href="<c:url value='/admin.html' />">Admin Home</a>]
  </security:authorize>
  <security:authorize access="hasRole('ROLE_REVIEWER')">
    [Reviewer Home]
  </security:authorize>
    [<a href="<c:url value='/applicant.html' />">Applicant Home</a>]
</p>

<form action="reviewer.html" method="get">
<table>
<tr>
<td>Search CV:</td><td><input type="text" name="term" style="width: 100%;" value="${term}" /></td>
<td colspan="2"><input type="submit" name="Search" value="Search CV" /></td>
</tr>
<tr>

</tr>
</table>
<input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
<c:if test="${empty term}">
<p>no result</p>
</c:if>
<c:if test="${not empty files}">
<div class="container col-md-4">
<table class="table table-bordered table table-striped">
<c:forEach items="${files}" var="file">
<tr>
<td>${file.owner.firstName}</td><td><a href="<c:url value='/download.html?fileId=${file.id}' />">${file.name}</a></td></tr>
</c:forEach>
</table>
</div>
</c:if>

<br>

<%-- <h3>Reviewer Home</h3>
<div class="container col-md-6 center">
<table class="table table-bordered table table-striped">
<tr><th>Job</th><th>Applicants</th></tr>
<c:forEach items="${reviewer_job}" var="job">
<tr>
  <td><a href="<c:url value='/job/view.html?id=${job.id}' />">${job.title}</a></td>
  <td><a href="<c:url value='/reviewerApp.html?id=${job.id}' />">See Applications</a></td>
  </tr>
  
  </c:forEach>
  </table>
  

<h3>Committee Chair Home</h3>
<table class="table table-bordered table table-striped">
<tr><th>Job</th><th>Applicants</th></tr>
<c:forEach items="${chair_job}" var="job">
<tr>
  <td><a href="<c:url value='/job/view.html?id=${job.id}' />">${job.title}</a></td>
  <td><a href="<c:url value='/chairApp.html?id=${job.id}' />">See Applications</a></td>
  </tr>
  
  </c:forEach>
  </table>
</div> --%>
</body>
</html>
