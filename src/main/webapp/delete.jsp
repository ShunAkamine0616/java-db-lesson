<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除画面</title>
</head>
<body>
<h1>削除対象のproduct_idを入力してください</h1>

<c:if test="${not empty result}">
<p>${result}</p>
</c:if>
<form action="deleteResult" method="post">
<label>product_id：</label>
    <input type="text" name="product_id">
     <c:if test="${not empty idErrMsg}">
            <span>${fn:escapeXml(idErrMsg)}</span>
     </c:if>
    <br>
    <button type="submit" value="delete" name="btn">削除</button>
</form>
</body>
</html>