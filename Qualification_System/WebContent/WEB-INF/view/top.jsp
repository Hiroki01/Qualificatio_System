<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Qualification_System/css/style.css"
	type="text/css" />
<title>ログイン画面</title>
</head>
<body>
	<div class="main">
		<div class="form-wrapper">
			<%
			HttpSession sessions = request.getSession(true);
			/* 認証失敗から呼び出されたのかどうか */
			Object status = session.getAttribute("status");
			if (status != null) {
				if (status.equals("完了")) {
		%>
			<p>登録完了しました。</p>
			<%
			session.removeAttribute("status");
				} else if (status.equals("nai")) {
		%>
			<p>入力ミスがあります。</p>
			<%
			session.removeAttribute("status");
				} else if (status.equals("Exception")) {
		%>
			<p>エラーが発生しました。</p>
			<%
			session.removeAttribute("status");
				}
			}
		%>
            <form action="/Qualification_System/Login" method="post">
                <div class="form-item" style="margin-bottom: 35px;">
                    <label>ユーザーID</label>
                    <input type="number" name="id" required placeholder="学籍番号または教師ID">
                </div>
                <div class="form-item">
                    <label>パスワード</label>
                    <input type="password" name="pass" required placeholder="password">
                </div>
                <div class="button-panel">
                    <input id="loginbutton" type="submit" value="ログイン">
                </div>
            </form>
            <div class="form-footer">
                <p><a href="/Qualification_System/Add">新規登録はこちら</a></p>
                <p><a href="/Qualification_System/Pass">パスワードを忘れた方はこちら</a></p>
            </div>
		</div>
	</div>
</body>
</html>