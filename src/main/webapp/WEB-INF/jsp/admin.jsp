<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS Jobs - Admin</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>

<p>You are logged in as <em><security:authentication property="principal.name" /></em>.</p>
<form action="<c:url value='/logout' />" method="post">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input name="submit" type="submit" value="Logout" />
</form>
<p>
  [Admin Home]
  <security:authorize access="hasRole('ROLE_REVIEWER')">
    [<a href="<c:url value='/reviewer.html' />">Reviewer Home</a>]
  </security:authorize>
    [<a href="<c:url value='/applicant.html' />">Applicant Home</a>]
</p>

<h3>Job Positions</h3>

<p><a href="<c:url value='/job/create.html' />">Create Job Position</a></p>

<table border="1">
<tr><th>Job</th><th>Publish Date</th><th>Close Date</th><th>Operations</th></tr>
<c:forEach items="${jobs}" var="job">
<tr>
  <td><a href="<c:url value='/job/view.html?id=${job.id}' />">${job.title}</a></td>
  <td><fmt:formatDate value="${job.publishDate}" pattern="M/d/yyyy" /></td>
  <td><fmt:formatDate value="${job.closeDate}" pattern="M/d/yyyy" /></td>
  <td><a href="<c:url value='/job/edit.html?id=${job.id}' />">Edit</a></td>
</tr>
</c:forEach>
</table>

</body>
</html>
