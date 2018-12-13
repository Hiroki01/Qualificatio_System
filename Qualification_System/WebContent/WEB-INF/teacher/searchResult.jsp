<%@page import="java.math.BigDecimal"%>
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
<title>検索結果</title>
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
						int admin = (int) session.getAttribute("admin");
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
			<h1 id="title">検索結果</h1>
			<hr>
			<div id="main">

				<%
					@SuppressWarnings("unchecked")
					ArrayList<QualificationDTO> result = (ArrayList<QualificationDTO>) session.getAttribute("result");
					QualificationDTO count = (QualificationDTO) session.getAttribute("count");
				%>
				<div style="display: block;">
					<form action="/Qualification_System/Update" method="post"
						style="display: inline-block;">
						結果更新：<select name="result" required="required">
							<option value="0" selected>------</option>
							<option value="未受験">未受験</option>
							<option value="合格">合格</option>
							<option value="不合格">不合格</option>
						</select> <input type="text" value="1" name="Judgment"
							style="display: none;"> <input type="submit"
							value="全更新">
					</form>
					<form action="/Qualification_System/CSV" id="superform"
						style="display: inline-block;float: right;">
						<input type="text" value="search" name="button"
							style="display: none;"> <input type="submit"
							value="CSVファイルで出力" id="csv">
					</form>
				</div>
				<form action="/Qualification_System/Update" method="post">
					<div id="wrap" style="height: 290px;">
						<table border="1" style="height: 30; width: 95%;"
							id="result" class="tablesorter">
							<thead>
								<tr>
									<th>学籍番号</th>
									<th>氏名</th>
									<th>資格名</th>
									<th>受験日</th>
									<th>受験結果</th>
									<th>上位資格名</th>
									<th>上位資格名</th>
									<th>上位資格名</th>
									<th>上位資格名</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (QualificationDTO anko : result) {
								%>
								<tr>
									<td><%=anko.getSid()%><input type="text" value="0"
										name="Judgment" style="display: none"><input
										type="text" name="id" value="<%=anko.getSid()%>"
										style="display: none;"></td>
									<td><%=anko.getSname()%></td>
									<td><%=anko.getCname()%><input type="text" name="name"
										value="<%=anko.getCname()%>" style="display: none;"></td>
									<td><%=anko.getExamdate()%><input type="text" name="date"
										value="<%=anko.getExamdate()%>" style="display: none;"></td>
									<td><select name="results" required="required">
											<option value=<%=anko.getResult()%> selected><%=anko.getResult()%></option>
											<option value="未受験">未受験</option>
											<option value="合格">合格</option>
											<option value="不合格">不合格</option>
									</select></td>
									<%
										if (anko.getS1() != null) {
									%>
									<td><%=anko.getS1()%></td>
									<%
										} else {
									%>
									<td>無</td>
									<%
										}
									%>
									<%
										if (anko.getS2() != null) {
									%>
									<td><%=anko.getS2()%></td>
									<%
										} else {
									%>
									<td>無</td>
									<%
										}
									%>
									<%
										if (anko.getS3() != null) {
									%>
									<td><%=anko.getS3()%></td>
									<%
										} else {
									%>
									<td>無</td>
									<%
										}
									%>
									<%
										if (anko.getS4() != null) {
									%>
									<td><%=anko.getS4()%></td>
									<%
										} else {
									%>
									<td>無</td>
									<%
										}
									%>
								</tr>

								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<div id="goukakusya">
						<p style="font-size: 140%;">
							合格者：<%=count.getCount1()%>名 不合格者：<%=count.getCount2()%>名 未受験者：<%=count.getCount3()%>名
						</p>
					</div>
					<div id="buttons">
						<input type="button" class="return" value="戻る"
							onClick="history.go(-1)"><input type="submit"
							class="execute" value="更新" style="margin-left: 10%;">
					</div>
				</form>
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
			$("#result").tablesorter({
		        headers: {
		            1: { sorter: false },
		            5: { sorter: false },
		            6: { sorter: false },
		            7: { sorter: false },
		            8: { sorter: false }
		        }
		    } );
		});
	</script>
</body>
</html>