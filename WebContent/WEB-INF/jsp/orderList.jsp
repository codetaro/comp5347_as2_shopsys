<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen" />
<title>Order List</title>
</head>
<body>
<h3>Order List</h3>
<table>
  <tr>
    <th>ID</th>
    <th>Tracking Num</th>
    <th>Address</th>
    <th>Items</th>
    <th>Status</th>
    <th>User</th>
    <th>Operation</th>
  </tr>
  <c:forEach var="order" items="${orders}">
  <tr>
    <td>${order.orderId}</td>
    <td>${order.tracking_num}</td>
    <td>${order.address}</td>
    <td>${order.items}</td>
    <td>${order.status}</td>
    <td>${order.user}</td>
    <td>
      <a href="${pageContext.request.contextPath}/products/pack/${order.orderId}" class="pack">Pack</a>
    </td>
  </tr>
  </c:forEach>
</table>
</body>
</html>