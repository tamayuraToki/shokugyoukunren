<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>在庫データ追加</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<form action="ProductInsertServlet" method="post" enctype="multipart/form-data">
    在庫名<input type="text" name="productName"/><br>
    写真<input type="file" name="pict" accept=".png, .jpg"/><br>
    値段<input type="text" name="price"/><br>
    購入店<input type="text" name="buyer"/><br>
    在庫数<input type="text" name="stock"/><br>

    <input type="submit" value="登録" />
</form>


</body>
</html>