<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Start packing Order#${order.orderId}</h3>
<form action="${pageContext.request.contextPath}/products/ship">
<table>
  <tr>
    <td>Select Warehouse:</td>
    <td>
      <input type="radio" name="warehouse_add" value="sydney"/>Sydney
      <input type="radio" name="warehouse_add" value="melbourne"/>Melbourne
    </td>
  </tr>
  <tr class="hidden">
    <td>
      <input type="hidden" name="orderId" value="${order.orderId}"/>
      <input type="hidden" name="delivery_add" value="${order.address}"/>
    </td>
  </tr>
  <tr>
    <td>Items Packed:</td>
    <td>
      <input type="text" name="items"/><br/>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <input type="submit" name="ship" value="Ship Partial" class="button"/>
      <input type="submit" name="ship" value="Ship All" class="button"/>
    </td>
  </tr>
</table>
</form>
</body>
</html>