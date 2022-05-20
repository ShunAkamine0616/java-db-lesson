<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>更新対象のproduct_idを入力してください</h1>

	<c:if test="${not empty result}">
		<p>${result}</p>
	</c:if>
	<form action="updateResult" method="post">
		<label>product_id：</label> <input type="text" name="product_id">
		<c:if test="${not empty idErrMsg}">
			<span>${fn:escapeXml(idErrMsg)}</span>
		</c:if>
		<br> <label>product_name：</label> <input type="text"
			name="product_name">
		<c:if test="${not empty nameErrMsg}">
			<span>${fn:escapeXml(nameErrMsg)}</span>
		</c:if>
		<br><label>price：</label> <input type="text"
			name="price">
		<c:if test="${not empty priceErrMsg}">
			<span>${fn:escapeXml(priceErrMsg)}</span>
		</c:if>
		<button type="submit" value="update" name="btn">更新</button>
	</form>
</body>
</html>