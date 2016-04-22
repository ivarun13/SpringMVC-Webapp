<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CS Jobs - Registration</title>
</head>
<body>
<form:form modelAttribute="user">
<table border="1">
<tr>
  <th>Email:</th>
  <td><form:input path="email" /> <b><form:errors path="email" /></b></td>
</tr>
<tr>
  <th>Password:</th>
  <td><form:password path="password" /> <b><form:errors path="password" /></b></td>
</tr>
<tr>
  <th>Password again:</th>
  <td><form:password path="password2" /> <b><form:errors path="password2" /></b></td>
</tr>
<tr>
  <th>Last Name:</th>
  <td><form:input path="lastName" /> <b><form:errors path="lastName" /></b></td>
</tr>
<tr>
  <th>First Name:</th>
  <td><form:input path="firstName" /> <b><form:errors path="firstName" /></b></td>
</tr>
<tr>
  <th>Phone:</th>
  <td><form:input path="phone" /> <b><form:errors path="phone" /></b></td>
</tr><tr>
  <th>Address:</th>
  <td><form:input path="address" /> <b><form:errors path="address" /></b></td>
</tr>
<tr>
  <td><br /></td><td><input type="submit" name="register" value="register" /></td>
</tr>
</table>
</form:form>
</body>
</html>
