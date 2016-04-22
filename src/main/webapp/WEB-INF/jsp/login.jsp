<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CS Jobs - Login</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>
<form action="<c:url value='/login' />" method="post">
<table border="1">
<tr>
  <th>Email:</th>
  <td><input type="text" name="username" /></td>
</tr>
<tr>
  <th>Password:</th>
  <td><input type="password" name="password" /></td>
</tr>
<tr>
  <th><br /></th>
  <td><input type="submit" name="login" value="Login"/></td>
</tr>
</table>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>
