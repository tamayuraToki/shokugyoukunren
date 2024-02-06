<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫管理</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<h1>在庫管理アプリへようこそ</h1>
<div class="login-content wrapper">
	<form action="LoginServlet" method="post">
	ユーザ名　:　<input type="text" name="name"><br>
	パスワード:　<input type="password" name ="pass"><br>
	<input class="login-button" type="submit" value="ログイン">
</div>
</form>
</body>
</html>