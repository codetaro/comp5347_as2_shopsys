<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart View</title>
</head>
<body>
<h2>Item in cart: </h2>
<table>
<tr>
	<th>Title</th>
	<th>Purchase Level</th>
</tr>
<c:forEach var="cartItem" items="${cart.getItems()}">
<tr>
	<td>${cartItem.product.title}</td>
	<td>${cartItem.quantity}</td>
</tr>
</c:forEach>
<%-- <tr>
	<th>Total: </th>
	<th>${cart.getTotal()} items</th>
</tr> --%>
</table>
</body>
</html>