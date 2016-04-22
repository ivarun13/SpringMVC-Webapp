<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CS Jobs - Application</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>
<h3>${application.job.title}</h3>

<p>Applicant: ${application.applicant.firstName} ${application.applicant.lastName}</p>
<p>Submitted: <fmt:formatDate value="${application.submitDate}" pattern="M/d/yyyy" /></p>
<p>Current Position: ${application.currentJobTitle} at ${application.currentJobInstitution}
since ${application.currentJobYear}</p>

<table border="1">
<tr><th>Degree</th><th>School</th><th>Year</th></tr>
<c:forEach items="${application.degrees}" var="degree">
<tr>
  <td>${degree.name}</td>
  <td>${degree.school}</td>
  <td>${degree.year}</td>
</tr>
</c:forEach>
</table>

<br />

<table border="1">
<tr>
  <th>CV</th>
  <td>
    <c:if test="${not empty application.cv}">
    <a href="<c:url value='/download.html?fileId=${application.cv.id}' />">View</a>
    </c:if>
  </td>
</tr>
<tr>
  <th>Research Statement</th>
  <td>
    <c:if test="${not empty application.researchStatement}">
    <a href="<c:url value='/download.html?fileId=${application.researchStatement.id}' />">View</a>
    </c:if>
  </td>
</tr>
<tr>
  <th>Teaching Statement</th>
  <td>
    <c:if test="${not empty application.teachingStatement}">
    <a href="<c:url value='/download.html?fileId=${application.teachingStatement.id}' />">View</a>
    </c:if>
  </td>
</tr>

</table>
</body>
</html>
