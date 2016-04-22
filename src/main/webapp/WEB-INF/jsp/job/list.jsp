<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS Jobs</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>

<security:authorize access="not authenticated">
<p>
  <a href="<c:url value='/register.html' />">Register</a> |
  <a href="<c:url value='/login.html' />">Login</a>
</p>
</security:authorize>

<security:authorize access="authenticated">
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
    [<a href="<c:url value='/reviewer.html' />">Reviewer Home</a>]
  </security:authorize>
    [<a href="<c:url value='/applicant.html' />">Applicant Home</a>]
</p>
</security:authorize>

<h3>Open Job Postions</h3>
<ul>
<c:forEach items="${openJobs}" var="job">
  <li><a href="<c:url value='/job/view.html?id=${job.id}' />">${job.title}</a></li>
</c:forEach>
</ul>
</body>
</html>
