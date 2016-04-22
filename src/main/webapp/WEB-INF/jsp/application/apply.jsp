<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CS Jobs - Apply</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>
<h3>${application.job.title}</h3>
<p>Applicant: ${application.applicant.firstName} ${application.applicant.lastName}</p>

<form:form action="apply.html?${_csrf.parameterName}=${_csrf.token}"
  modelAttribute="application" enctype="multipart/form-data">
<p>Current Job Position:</p>
<table border="1">
<tr>
  <th>Title</th>
  <td><form:input path="currentJobTitle" /></td>
</tr>
<tr>
  <th>Institution or Company</th>
  <td><form:input path="currentJobInstitution" /></td>
</tr>
<tr>
  <th>Starting Year</th>
  <td><form:input path="currentJobYear" /></td>
</tr>
<tr>
  <th>CV</th>
  <td><input type="file" name="cvfile" /></td>
</tr>
<tr>
  <th>Research Statement</th>
  <td><input type="file" name="rsfile" /></td>
</tr>
<tr>
  <th>Teaching Statement</th>
  <td><input type="file" name="tsfile" /></td>
</tr>
<tr>
  <th><br /></th>
  <td><input type="submit" name="next" value="Next" /></td>
</tr>
</table>
</form:form>
</body>
</html>
