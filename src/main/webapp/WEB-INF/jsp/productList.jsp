<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.ProductService" %>

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
<div id="wrapper">
<h1>在庫一覧</h1>
	<form action="ProductInsertServlet" method="get">
        <input type="submit" value="データ追加">
    </form>
    <div id="menu">
	<% for (int i = 0; i < productList.size(); i++) { %>
		<div>
		<img src="<%= productList.get(i).getImageUrl() %>" alt="<%= productList.get(i).getProductName() %>">
		<a href="ProductDetailsServlet?productId=<%= productList.get(i).getProductId()%>">
			<%= productList.get(i).getProductName()%>
		</a>
		</div>
	<% } %>
	   </div>
</div>
</body>
</html>