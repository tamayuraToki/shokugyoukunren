<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Product" %>

<%
	Product product = (Product)request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>在庫詳細</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>在庫詳細</h1>
		<img src="${product.imageUrl}" alt="${product.productName}">
		<p>商品名: ${product.productName}</p>
		<p>価格: ${product.price}</p>
		<p>購入店: ${product.buyer}</p>
		<p>在庫数: ${product.stock}</p>
		<a href="ProductUpdateServlet?productId=${product.productId}">
		データ更新
		</a>
	</div>
</body>
</html>