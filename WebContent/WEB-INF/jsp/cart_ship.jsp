<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Information</title>
</head>
<body>
<h3>Please confirm your order information.</h3>
<form:form action="makeOrder" commandName="order">
  <label>Delivery Address
    <form:input type="text" path="address"/><br/>
    <form:errors path="address" class="error"/>
  </label>
  <table>
  <c:forEach var="cartItem" items="${cart.getItems()}">
  <tr>
    <td>${cartItem.product.title}</td>
    <td>${cartItem.quantity}</td>
  </tr>
  </c:forEach>
  <form:input type="hidden" path="items"/>
  <form:input type="hidden" path="user"/>
  </table>
  <input type="submit" value="Purchase" class="button"/>
</form:form>
</body>
</html>