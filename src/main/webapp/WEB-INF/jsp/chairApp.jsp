<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<h3>Select Applications (Round 1) :</h3>
<table class="table table-bordered table table-striped">
<tr><th>Select</th><th>Name</th><th>Reviewer</th><th>Comment</th></tr>
<form:form modelAttribute="job" method="post">
<c:forEach items="${apps}" var="application">
<c:forEach items="${ application.rounds.get(0).reviews}" var="review">
<tr>

 <td><input type = "checkbox" name = "applications" value = "${application.id}" /> <td>${application.applicant.firstName}</td><td>${review.reviewer.firstName}</td><td>${review.comments}</td>
  
  </tr>
 </c:forEach>
  <%-- c:forEach items="${application.rounds.get(0).reviews.get}" var="review">
  <b>${review.comments}</b>
  </c:forEach> --%>
  
  </c:forEach>
  <input type="submit" name="Done" value="Done" />
  </form:form> 
  </table>
  
  
  <h3>Select Applications (Round 2) :</h3>
  <c:if test="${not empty apps2}">
<table class="table table-bordered table table-striped">
<tr><th>Select</th><th>Name</th><th>Reviewer</th><th>Comment</th></tr>
<form:form modelAttribute="job" method="post">
<c:forEach items="${apps2}" var="application">
<c:forEach items="${ application.rounds.get(1).reviews}" var="review">
<tr>
 <td><input type = "checkbox" name = "applications2" value = "${application.id}" /> <td>${application.applicant.firstName}</td><td>${review.reviewer.firstName}</td><td>${review.comments}</td>
  
  </tr>
 
  <%-- c:forEach items="${application.rounds.get(0).reviews.get}" var="review">
  <b>${review.comments}</b>
  </c:forEach> --%>
  </c:forEach>
  </c:forEach>
  <input type="submit" name="Done" value="Done" />
  </form:form> 
  </table>
 </c:if> 
 
 <h3>final round:</h3>
 <table class="table table-bordered table table-striped">
<tr><th>Select</th><th>Name</th><th>Reviewer</th><th>Rank</th></tr>
<form:form modelAttribute="job" method="post">
<c:forEach items="${apps3}" var="application">
<c:forEach items="${ application.rounds.get(2).reviews}" var="review">
<tr>
 <td><input type = "checkbox" name = "applications3" value = "${application.id}" /> <td>${application.applicant.firstName}</td><td>${review.reviewer.firstName}</td><td>${review.rank}</td>
 
  
  </tr>
  </c:forEach>
  </c:forEach>
  
  <input type="submit" name="Done" value="Done" />
  </form:form> 
  </table>
</body>
</html>