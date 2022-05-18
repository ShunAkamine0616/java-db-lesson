<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<label>product_id：</label>
    <input type="text" name="product_id">
    <br>
    <button type="submit">検索</button>
</form>
</body>
</html>