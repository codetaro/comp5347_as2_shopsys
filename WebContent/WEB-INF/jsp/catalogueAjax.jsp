<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Catalogue Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".add, .remove").click(function(e) {
			e.preventDefault();
			$("#cart").load($(this).attr("href"));
		});
		$(".checkOut").click(function(e) {
			e.preventDefault();
			$("#cart").load($(this).attr("href"));
		});
	});
</script>
</head>
<body>
<div id="header">
	<h2>shopping cart page</h2>
</div>

<div id="catalogue">
	<table>
	<tr>
		<th>Title</th>
		<th>ISBN</th>
<!-- 		<th>Description</th> -->
		<th>Cover</th>
		<th>Inventory Level</th>
	</tr>
	<c:forEach var="product" items="${products}" varStatus="productCount">
	<tr>
		<td>${product.title}</td>
		<td>${product.isbn_10}</td>
<%-- 		<td>${product.description}</td> --%>
		<td><img alt="${product.title}" src="${pageContext.request.contextPath}/resources/img/${product.imgUrl}"/></td>
		<td>${product.inventory_level}</td>
		<td>
			<a href="${pageContext.request.contextPath}/cartsAjax/add/${product.productId}" class="add">add</a>
			<a href="${pageContext.request.contextPath}/cartsAjax/remove/${product.productId}" class="remove">remove</a>
		</td>
	</tr>
	</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}/cartsAjax/checkOut" class="checkOut">Check out</a><br />
	<a href="${pageContext.request.contextPath}">Back to home</a>
</div>

<div id="cart">
<jsp:include page="cart_partial.jsp"></jsp:include>
</div>
</body>
</html>