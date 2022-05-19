<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>検索結果</h1>
	<table border="1">
		<tr>
		<th>product_id</th>
		<th>product_name</th>
		<th>price</th>
		</tr>
		<c:forEach var="product" items="${productList}">
		<tr>
			<td>${product.getProduct_id()}</td>
			<td>${product.getProduct_name()}</td>
			<td>${product.getPrice()}</td>
		</tr>
		</c:forEach>
		
	</table>
	

	<div>
		<a href="top.jsp">戻る</a>
	</div>
</body>
</html>