<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
</head>
<body>
<h1>検索条件を入力してください</h1>
<c:if test="${not empty result}">
<p>${result}</p>
</c:if>
<form action="SearchResult" method="post">
<label>product_name：</label>
    <input type="text" name="product_name">
     <c:if test="${not empty nameErrMsg}">
            <span>${fn:escapeXml(nameErrMsg)}</span>
     </c:if>
    <br>
<label>price：</label>
    <input type="number" name="price">
    <c:if test="${not empty priceErrMsg}">
            <span>${fn:escapeXml(priceErrMsg)}</span>
     </c:if>
    <br>
    <button type="submit" value="search" name="btn">検索</button>
    <button type="submit" value="insert" name="btn">登録</button>
</form>
</body>
</html>