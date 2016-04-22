<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CS Jobs - Create Job</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>
<h3>Edit Job</h3>
<form:form modelAttribute="job">
<table border="1">
<tr>
  <th>Title</th>
  <td><form:input path="title" /></td>
</tr>
<tr>
  <th>Description</th>
  <td><form:textarea path="description" /></td>
</tr>
<tr>
  <th>Publish Date</th>
  <td><form:input path="publishDate" /></td>
</tr>
<tr>
  <th>Close Date</th>
  <td><form:input path="closeDate" /></td>
</tr>
<tr>
  <th>Committee Chair</th>
  <td>
    <form:select items="${reviewers}" path="committeeChair" itemValue="id" itemLabel="name" />
  </td>
</tr>
<tr>
  <th>Committee Members</th>
  <td>
    <c:forEach items="${reviewers}" var="reviewer">
    <form:checkbox path="committeeMembers" value="${reviewer}" />
    ${reviewer.name} <br />
    </c:forEach>
  </td>
</tr>
<tr>
  <td colspan="2"><input type="submit" name="create" value="Save" /></td>
</tr>
</table>
</form:form>
</body>
</html>
