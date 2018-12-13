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
<title>上位資格確認</title>
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
			<h1 id="title">上位資格確認</h1>
			<hr>
			<div id="main">
				<form action="/Qualification_System/CSV" id="superform">
					<input type="text" value="Superior" name="button"
						style="display: none;"> <input type="submit"
						value="CSVファイルで出力" id="csv">
				</form>
				<div id="wrap"
					style="height: 300px; margin-top: 1%; font-size: 120%;">
					<table border="1" style="height: 30; width: 95%;">
						<thead>
							<tr>
								<th>取得済み資格名</th>
								<th>上位資格名</th>
								<th>難易度</th>
								<th>合格点</th>
								<th>合格基準</th>
								<th>リンク</th>
							</tr>
						</thead>
						<tbody>
							<%
								@SuppressWarnings("unchecked")
								ArrayList<QualificationDTO> result = (ArrayList<QualificationDTO>) session.getAttribute("Super");
								for (QualificationDTO anko : result) {
							%>
							<tr>
								<td><%=anko.getCname()%></td>
								<%
									if (anko.getCname1() != null) {
								%>
								<td><%=anko.getCname1()%></td>
								<td><%=anko.getLevel1()%></td>
								<td><%=anko.getPassing1()%></td>
								<td><%=anko.getPassrate1()%></td>
								<td><a href="<%=anko.getUrl1()%>">公式サイト</a></td>
								<%
									} else {
								%>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<%
									}
								%>
							</tr>
							<%
								if (anko.getCname1() != null) {
							%>
							<tr>
								<%
									if (anko.getCname2() != null) {
								%>
								<td></td>
								<td><%=anko.getCname2()%></td>
								<td><%=anko.getLevel2()%></td>
								<td><%=anko.getPassing2()%></td>
								<td><%=anko.getPassrate2()%></td>
								<td><a href="<%=anko.getUrl2()%>">公式サイト</a></td>
								<%
									} else {
								%>
								<td></td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<%
									}
								%>
							</tr>
							<%
								if (anko.getCname2() != null) {
							%>
							<tr>
								<%
									if (anko.getCname3() != null) {
								%>
								<td></td>
								<td><%=anko.getCname3()%></td>
								<td><%=anko.getLevel3()%></td>
								<td><%=anko.getPassing3()%></td>
								<td><%=anko.getPassrate3()%></td>
								<td><a href="<%=anko.getUrl3()%>">公式サイト</a></td>
								<%
									} else {
								%>
								<td></td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<%
									}
								%>
							</tr>
							<%
								if (anko.getCname3() != null) {
							%>
							<tr>
								<%
									if (anko.getCname4() != null) {
								%>
								<td></td>
								<td><%=anko.getCname4()%></td>
								<td><%=anko.getLevel4()%></td>
								<td><%=anko.getPassing4()%></td>
								<td><%=anko.getPassrate4()%></td>
								<td><a href="<%=anko.getUrl4()%>">公式サイト</a></td>
								<%
									} else {
								%>
								<td></td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<td>登録情報なし</td>
								<%
									}
								%>
							</tr>
							<%
								}
										}
									}
								}
							%>
						</tbody>
					</table>
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
</body>
</html>