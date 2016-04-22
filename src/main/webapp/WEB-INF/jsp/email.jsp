<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Email</title>
</head>
<body>
<form action="email.html" method="post">
<table>
<tr>
<td>From:</td><td><input type="text" name="from" style="width: 100%;" /></td>
</tr>
<tr>
<td>To:</td><td><input type="text" name="to" style="width: 100%;" /></td>
</tr>
<tr>
<td>Subject:</td><td><input type="text" name="subject" style="width: 100%;" /></td>
</tr>
<tr>
<td colspan="2">
<textarea name="content" rows="5" cols="60"></textarea>
</td>
</tr>
<tr>
<td colspan="2"><input type="submit" name="send" value="send" /></td>
</tr>
</table>
<input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>

</body>
</html>