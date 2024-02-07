<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int productId = (int)request.getAttribute("productId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>データ削除確認</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<p>本当に削除しますか？</p>
	<div class="delete-frex">
	<form action="ProductDeleteServlet" method="post" class="delete-input">
		<input type="hidden" name="isYesNo" value="YES"/>
		<input type="hidden" name="productId" value="<%= productId%>"/>
		<input type="submit" value="YES" />
	</form>
	<form action="ProductDeleteServlet" method="post" class="delete-input">
		<input type="hidden" name="isYesNo" value="NO"/>
		<input type="hidden" name="productId" value="<%= productId%>"/>
    	<input type="submit" value="NO" />
	</form>
	</div>
</body>
</html>