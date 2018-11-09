<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h1>ログイン</h1>
	<form action="/Qualification_System/Login" method="post">
	<p>ID<input type="number" name="id"required></p>
	<p>PASS<input type="password" name="pass"required></p>
	<input type="submit" value="ログイン">
	</form>
	<a href="/Qualification_System/Add">新規登録</a><br>
	<a href="/Qualification_System/Pass">パスワード再設定</a>
</body>
</html>