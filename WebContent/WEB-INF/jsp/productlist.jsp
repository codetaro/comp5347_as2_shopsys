<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen" />
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".pack").click(function(e) {
		e.preventDefault();
		$("#package").load($(this).attr("href"));
	});
});
</script>
</head>
<body>

<div id="inventory">
<h3>Inventory List</h3>
<table>
<tr>
  <th>ID</th>
  <th>Title</th>
  <th>ISBN10</th>
  <th>Sydney Stock Level</th>
  <th>Melbourne Stock Level</th>
</tr>
<c:forEach var="product" items="${products}" varStatus="productCount">
<tr>
  <td>${product.productId}</td>
  <td>${product.title}</td>
  <td>${product.isbn_10}</td>
  <td>${product.sydney_stock_level}</td>
  <td>${product.melbourne_stock_level}</td>
</tr>
</c:forEach>
</table>
</div>
<a href="${pageContext.request.contextPath}">Back to home</a>
<div id="order">
  <jsp:include page="orderList.jsp"/>
  <div id="package"></div>
</div>

</body>
</html>