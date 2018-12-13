<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
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
	src="/Qualification_System/js/jquery.tablesorter.js"></script>
<script src="/js/jquery-1.12.4.min.js"></script>
<%
	int admin = (int) session.getAttribute("admin");
	if (admin == 1) {
%>
<title>管理者メニュー画面</title>
<%
	} else {
%>
<title>教師メニュー画面</title>
<%
	}
%>
</head>
<body>
	<div id="container">
		<header>
			<h1 id="logo">
				<a href="/Qualification_System/Tmenu">資格管理システム</a>
			</h1>
			<nav id="menubar">
				<ul>
					<%
						if (admin == 1) {
					%>
					<li><a href="#">マスタテーブル</a>
						<ul class="ddmenu" id="a">
							<li><a href="/Qualification_System/Credential_information">資格情報</a></li>
							<li><a href="/Qualification_System/Classification">主催情報</a></li>
							<li><a href="/Qualification_System/Subject">主要学科</a></li>
							<li><a href="/Qualification_System/Department">学科</a></li>
							<li><a href="/Qualification_System/Course">コース</a></li>
							<li><a href="/Qualification_System/Account">アカウント情報管理</a></li>
						</ul></li>
					<%
						}
					%>
					<li><a href="/Qualification_System/Search">検索</a></li>
					<li><a href="#">範囲登録</a>
						<ul class="ddmenu">
							<li><a href="/Qualification_System/StudentNumber">学籍番号</a></li>
							<li><a href="/Qualification_System/ClassRegistration">クラス</a></li>
						</ul></li>
					<li><a href="/Qualification_System/Confirmation">生徒確認</a></li>
					<li><a href="/Qualification_System/DeleteEvent">削除</a></li>
					<li><a href="/Qualification_System/TProfile">登録情報変更</a></li>
					<li><a href="/Qualification_System/LogOut">ログアウト</a></li>

				</ul>
			</nav>
		</header>
		<nav id="menubar-s">

			<ul>
				<%
					if (admin == 1) {
				%>
				<li><a href="/Qualification_System/Credential_information">資格情報</a></li>
				<li><a href="/Qualification_System/Classification">主催情報</a></li>
				<li><a href="/Qualification_System/Subject">主要学科</a></li>
				<li><a href="/Qualification_System/Department">学科</a></li>
				<li><a href="/Qualification_System/Course">コース</a></li>
				<li><a href="/Qualification_System/Account">アカウント情報管理</a></li>
				<%
					}
				%>
				<li><a href="/Qualification_System/Search">検索</a></li>
				<li><a href="/Qualification_System/StudentNumber">学籍番号登録</a></li>
				<li><a href="/Qualification_System/ClassRegistration">クラス登録</a></li>
				<li><a href="/Qualification_System/Confirmation">生徒確認</a></li>
				<li><a href="/Qualification_System/DeleteEvent">削除</a></li>
				<li><a href="/Qualification_System/TProfile">登録情報変更</a></li>
				<li><a href="/Qualification_System/LogOut">ログアウト</a></li>
			</ul>
		</nav>
		<div id="contents">
			<%
				if (admin == 0) {
			%>
			<h1 id="title">教師メニュー</h1>
			<%
				} else {
			%>
			<h1 id="title">管理者メニュー</h1>
			<%
				}
			%>
			<hr>
			<div id="main">

				<%
					HttpSession sessions = request.getSession(true);
					@SuppressWarnings("unchecked")
					ArrayList<QualificationDTO> result = (ArrayList<QualificationDTO>) session.getAttribute("log");
					if (!result.isEmpty()) {
				%>
				<div id="wrap" style="height: 150px;">
					<table border="1" style="height: 30; width: 70%;" id="res" class="tablesorter">
						<caption>不在時に行われた情報変更</caption>
						<thead>
							<tr>
								<th>学籍番号</th>
								<th>氏名</th>
								<th>資格名</th>
								<th>難易度</th>
								<th>受験日</th>
								<th>受験結果</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (QualificationDTO anko : result) {
							%>
							<tr>
								<td><%=anko.getSid()%></td>
								<td><%=anko.getSname()%></td>
								<td><%=anko.getCname()%></td>
								<td><%=anko.getLevel()%></td>
								<td><%=anko.getExamdate()%></td>
								<td><%=anko.getResult()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<%
						}
					%>

				</div>
				<div class="a_top_back">
					<p>資格管理システムへようこそ</p>
				</div>
			</div>
		</div>
	</div>
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
	<script type="text/javascript">
		$(document).ready(function() {
			$("#res").tablesorter({
		        headers: {
		            1: { sorter: false }
		        }
		    } );
		});
	</script>
</body>
</html>