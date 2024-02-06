<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Product" %>
<!DOCTYPE html>
<%
	Product product = (Product)request.getAttribute("product");
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>在庫情報更新</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<h1>在庫情報更新</h1>
		<img src="${product.imageUrl}" alt="${product.productName}">
		<form action="ProductUpdateServlet" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${product.productId}"/>
			在庫名<input type="text" name="productName" value ="${product.productName}" /><br>
			写真<input type="file" name="pict" accept=".png, .jpg" /><br>
			<input type="hidden" name="biforPict" value="${product.imageUrl}"/>
			値段<input type="text" name="price" value="${product.price}"/><br>
			購入店<input type="text" name="buyer" value="${product.buyer}"/><br>
			在庫数<input type="text" name="stock" value="${product.stock}"/><br>
    	<input type="submit" value="登録" />
		</form>
		
	</div>
</body>
</html>