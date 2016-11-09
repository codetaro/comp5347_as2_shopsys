<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen" />
<title>packageList.jsp</title>
</head>
<body>
<h3>Package List</h3>
<table>
  <tr>
    <th>ID</th>
    <th>Tracking Number</th>
    <th>Warehouse Address</th>
    <th>Items</th>
    <th>Status</th>
  </tr>
<c:forEach var="pac" items="${pacs}">
  <tr>
    <td>${pac.package_id}</td>
    <td>${pac.tracking_num}</td>
    <td>${pac.warehouse_add}</td>
    <td>${pac.items}</td>
    <td>${pac.status}</td>
  </tr>
</c:forEach>
</table>
</body>
</html>