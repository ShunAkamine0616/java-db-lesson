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
	<p>データを取得しました</p>
	<p>
		product_id:${product.getProduct_id()}<br>
		product_name:${product.getProduct_name()}<br>
		price:${product.getPrice()}
	</p>

	<div>
		<a href="top.jsp">戻る</a>
	</div>
</body>
</html>