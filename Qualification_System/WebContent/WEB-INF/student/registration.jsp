<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.CredentialDTO"%>
<%@ page import="masteDTO.QualificationDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Qualification_System/css/style.css"
	type="text/css" media="print, projection, screen" />
<script type="text/javascript"
	src="/Qualification_System/js/openclose.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/ddmenu_min.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/jquery-latest.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/jquery.tablesorter.min.js"></script>
<title>生徒メニュー画面</title>
</head>

<body>
	<!--メニューバー-->
	<div id="container">
		<header>
			<h1 id="logo">
				<a href="/Qualification_System/Smenu">資格管理システム</a>
			</h1>
			<nav id="menubar">
				<ul>
					<li><a href="/Qualification_System/Registration">資格登録</a></li>
					<li><a href="/Qualification_System/Supdate">一覧・結果更新</a></li>
					<li><a href="/Qualification_System/Superior">上位資格確認</a></li>
					<li><a href="/Qualification_System/Profile">登録情報変更</a></li>
					<li><a href="/Qualification_System/LogOut">ログアウト</a></li>
				</ul>
			</nav>
		</header>
		<nav id="menubar-s">
			<ul>
				<li><a href="/Qualification_System/Registration">資格登録</a></li>
				<li><a href="/Qualification_System/Supdate">一覧・結果更新</a></li>
				<li><a href="/Qualification_System/Superior">上位資格確認</a></li>
				<li><a href="/Qualification_System/Profile">登録情報変更</a></li>
				<li><a href="/Qualification_System/LogOut">ログアウト</a></li>

			</ul>
		</nav>
		<div id="contents">
			<h1 id="title">受験予定登録</h1>
			<hr>
			<div id="main">
				<form action="/Qualification_System/RegistrationResult"
					method="post">
					<div class="juken_regi_form">
						<%
							HttpSession sessions = request.getSession(true);
							@SuppressWarnings("unchecked")
							ArrayList<CredentialDTO> re = (ArrayList<CredentialDTO>) request.getAttribute("QuaName");
							Object status = session.getAttribute("status");
							if (status != null) {
								if (status.equals("完了")) {
						%>
						<p>登録完了しました。</p>
						<%
							session.removeAttribute("status");
								} else if (status.equals("nai")) {
						%>
						<p>指定されたものはありません。</p>
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
						<table>
							<tr>
								<td style="text-align: right;">受験資格名</td>
								<td style="text-align: left;"><select name="QuaName"
									style="width: 40%;" required>
										<option value="" selected>受験資格を選択</option>
										<%
											for (CredentialDTO anko : re) {
										%>
										<option value="<%=anko.getName()%>"><%=anko.getName()%></option>
										<%
											}
										%>
								</select></td>
							</tr>
							<tr>
								<td style="text-align: right;">受験日</td>
								<td style="text-align: left;"><input type="date"
									name="date" required></td>
							</tr>
						</table>
						<div id="buttons">
							<input type="button" onClick="history.go(-1)" class="return"
								value="戻る"> <input type="submit" class="execute"
								value="登録" style="margin-left: 10%;">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#seito").tablesorter();
		});
	</script>
	<script>
		if (OCwindowWidth() <= 900) {
			open_close("newinfo_hdr", "newinfo");
		}
	</script>
	<!--メニューの３本バー-->
	<div id="menubar_hdr" class="close">
		<span></span><span></span><span></span>
	</div>
	<!--メニューの開閉処理条件設定　900px以下-->
	<script>
		if (OCwindowWidth() <= 900) {
			open_close("menubar_hdr", "menubar-s");
		}
	</script>
</body>
</html>