<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
//セッションスコープからユーザ情報を取得
String userId =(String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫管理</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<h1>在庫管理ログイン</h1>
<%if(userId != null){ %>
	<div class="login-content wrapper">
		<p>ログインに成功しました</p>
		<p>ようこそ<%= userId%>さん</p>
		<a href="ProductServlet">在庫管理へ</a>
	</div>
<%}else{ %>
	<div class="login-content wrapper">
		<p>ログインに失敗しました</p>
		<a href="index.jsp">TOPへ</a>
	</div>
<%} %>
</body>
</html>