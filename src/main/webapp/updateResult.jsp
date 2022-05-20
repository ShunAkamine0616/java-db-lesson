<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>更新完了</h1>

<div>【更新前】</div>
<div>product_id:${beforeUpdate.getProduct_id()}</div>
<div>product_name:${beforeUpdate.getProduct_name()}</div>
<div>price:${beforeUpdate.getPrice()}</div>

<div>【更新後】</div>
<div>product_id:${afterUpdate.getProduct_id()}</div>
<div>product_name:${afterUpdate.getProduct_name()}</div>
<div>price:${afterUpdate.getPrice()}</div>

<a href="update.jsp">戻る</a>
</body>
</html>