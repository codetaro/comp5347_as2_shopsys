<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen" />
<title>Insert title here</title>
</head>
<body>
<h2>Amend purchase level: </h2>
<table>
<tr>
	<th></th>
	<th>Stock Level</th>
</tr>
<c:forEach var="cartItem" items="${info}">
<tr>
	<td>${cartItem.product.title}</td>
	<td>${cartItem.quantity}</td>
</tr>
</c:forEach>
</table>
</body>
</html>