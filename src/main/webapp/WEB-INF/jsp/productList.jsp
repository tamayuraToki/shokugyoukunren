<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.ProductServiceLogic" %>

<%
    List<Product> productList = (List<Product>)request.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>在庫一覧</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<h1>在庫一覧</h1>
	<form action="ProductInsertServlet" method="get">
		<input type="submit" value="データ追加">
	</form>
	<form action="ProductServlet" method="get">
		<input type="hidden" name="isLike" value="Like"/>
		検索<input type="text" name="searchWord"/>
	<input type="submit" value="検索">
	</form>
	<div class="wrapper grid">
	<% for (int i = 0; i < productList.size(); i++) { %>
		<div class="flex-item">
			<a href="ProductDetailsServlet?productId=<%= productList.get(i).getProductId()%>">
				<div class="image-wrap">
					<img src="<%= productList.get(i).getImageUrl() %>" alt="<%= productList.get(i).getProductName() %>">
				</div>
				<%= productList.get(i).getProductName()%>
			</a>
		</div>
	<% } %>
	</div>
</body>
</html>