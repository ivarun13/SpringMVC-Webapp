<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS Jobs - Applicant</title>
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
    [<a href="<c:url value='/reviewer.html' />">Reviewer Home</a>]
  </security:authorize>
    [Applicant Home]
</p>

<h3>Job Positions Applied</h3>
<c:if test="${user.applications.size() > 0}">
<ul>
  <c:forEach items="${user.applications}" var="application">
  <li><a href="<c:url value='/job/view.html?id=${application.job.id}' />">${application.job.title}</a>
    [<a href="<c:url value='/application/view.html?id=${application.id}' />">Application</a>]
  </li>
  </c:forEach>
</ul>
</c:if>

<h3>Job Positions Available</h3>
<c:if test="${availableJobs.size() > 0}">
<ul>
  <c:forEach items="${availableJobs}" var="job">
  <li><a href="<c:url value='/job/view.html?id=${job.id}' />">${job.title}</a>
    [<a href="<c:url value='/application/apply.html?jobId=${job.id}' />">Apply</a>]
  </li>
  </c:forEach>
</ul>
</c:if>
</body>
</html>
