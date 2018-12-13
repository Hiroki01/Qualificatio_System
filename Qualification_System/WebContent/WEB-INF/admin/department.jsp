<%@page import="masteDTO.SubjectDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.DepartmentDTO"%>
<%@ page import="masteDTO.SubjectDTO"%>

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
<script src="/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.6.4.js"></script>
<title>学科情報</title>
</head>
<body>
	<div id="container">
		<header>
			<h1 id="logo">
				<a href="/Qualification_System/Tmenu">資格管理システム</a>
			</h1>
			<nav id="menubar">
				<ul>
					<li><a href="#">マスタテーブル</a>
						<ul class="ddmenu" id="a">
							<li><a href="/Qualification_System/Credential_information">資格情報</a></li>
							<li><a href="/Qualification_System/Classification">主催情報</a></li>
							<li><a href="/Qualification_System/Subject">主要学科</a></li>
							<li><a href="/Qualification_System/Department">学科</a></li>
							<li><a href="/Qualification_System/Course">コース</a></li>
							<li><a href="/Qualification_System/Account">アカウント情報管理</a></li>
						</ul></li>
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
				<li><a href="/Qualification_System/Credential_information">資格情報</a></li>
				<li><a href="/Qualification_System/Classification">主催者情報</a></li>
				<li><a href="/Qualification_System/Subject">主要学科</a></li>
				<li><a href="/Qualification_System/Department">学科</a></li>
				<li><a href="/Qualification_System/Course">コース</a></li>
				<li><a href="/Qualification_System/Account">アカウント情報管理</a></li>

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
			<h1 id="title">学科情報</h1>
			<hr>
			<div id="main">
				<%
					@SuppressWarnings("unchecked")
					ArrayList<DepartmentDTO> result = (ArrayList<DepartmentDTO>) request.getAttribute("department");
					@SuppressWarnings("unchecked")
					ArrayList<SubjectDTO> subject = (ArrayList<SubjectDTO>) request.getAttribute("subject");
				%>
				<div class="stchange_b">
					<input type="submit" value="登録" onclick="Display('no1')"
						class="change_b2"> <input type="submit" value="更新"
						onclick="Display('no2')" class="change_b2"> <input
						type="submit" value="削除" onclick="Display('no3')"
						class="change_b2">
				</div>
				<div id="SW1">
					<form action="/Qualification_System/DepartmentRegistration"
						method="post">
						<div id="wrap"
							style="margin-left: 10%; height: 400px; width: 40%; float: left;">
							<table border="1" style="height: 100%; width: 90%;">
								<thead>
									<tr>
										<th>主要学科名</th>
										<th>学科名</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (DepartmentDTO anko : result) {
									%>
									<tr>
										<td><%=anko.getSname()%></td>
										<td><%=anko.getName()%></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<div class="st_form">
						<h1 style="white-space: nowrap; font-size: 120%;margin: 0;">登録画面</h1>
							<%
								Object status = session.getAttribute("status");
								if (status != null) {
									if (status.equals("完了1")) {
							%>
							<p>登録完了しました。</p>
							<%
								session.removeAttribute("status");
									} else if (status.equals("nai1")) {
							%>
							<p>指定されたものはありません。</p>
							<%
								session.removeAttribute("status");
									} else if (status.equals("Exception1")) {
							%>
							<p>エラーが発生しました。</p>
							<%
								session.removeAttribute("status");
									}
								}
							%>
							<table style="white-space: nowrap; font-size: 150%;">
								<tr>
									<td style="text-align: right;">主要学科名：</td>
									<td style="text-align: left;"><select name="sid" required>
											<%
												for (SubjectDTO anco : subject) {
											%>
											<option value="<%=anco.getId()%>"><%=anco.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">学科名：</td>
									<td style="text-align: left;"><input type="text"
										name="name" required></td>
								</tr>
							</table>
							<div id="buttons" style="margin-top: 20px;">
								<input type="submit" value="登録" class="execute">
							</div>
						</div>
					</form>
				</div>
				<div id="SW2" style="display: none;">
					<form action="/Qualification_System/DepartmentUpdate" method="post">
						<div id="wrap"
							style="margin-left: 10%; height: 400px; width: 40%; float: left;">
							<table border="1" style="height: 100%; width: 90%;">
								<thead>
									<tr>
										<th></th>
										<th>主要学科名</th>
										<th>学科名</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (DepartmentDTO anko : result) {
									%>
									<tr>
										<td><input type="radio" name="check"
											value="<%=anko.getId()%>" required="required" /></td>
										<td><%=anko.getSname()%></td>
										<td><%=anko.getName()%></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<div class="st_form">
						<h1 style="white-space: nowrap; font-size: 120%;margin: 0;">更新画面</h1>
							<%
								if (status != null) {
									if (status.equals("完了2")) {
							%>
							<p>更新完了しました。</p>
							<%
								session.removeAttribute("status");
									} else if (status.equals("nai2")) {
							%>
							<p>指定されたものはありません。</p>
							<%
								session.removeAttribute("status");
									} else if (status.equals("Exception2")) {
							%>
							<p>エラーが発生しました。</p>
							<%
								session.removeAttribute("status");
									}
								}
							%>
							<table style="white-space: nowrap; font-size: 150%;">
								<tr>
									<td style="text-align: right;">主要学科名：</td>
									<td style="text-align: left;"><select name="sid" required>
											<%
												for (SubjectDTO anco : subject) {
											%>
											<option value="<%=anco.getId()%>"><%=anco.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">学科名：</td>
									<td style="text-align: left;"><input type="text"
										name="name" required></td>
								</tr>
							</table>
							<div id="buttons" style="margin-top: 20px;">
								<input type="submit" value="更新" class="execute">
							</div>
						</div>
					</form>
				</div>
				<div id="SW3" style="display: none;">
					<form action="/Qualification_System/DepartmentDelete" method="post">
						<div id="wrap"
							style="margin-left: 10%; height: 400px; width: 40%; float: left;">
							<table border="1" style="height: 100%; width: 90%;">
								<thead>
									<tr>
										<th></th>
										<th>主要学科名</th>
										<th>学科名</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (DepartmentDTO anko : result) {
									%>
									<tr>
										<td><input type="checkbox" name="check"
											value="<%=anko.getId()%>" /></td>
										<td><%=anko.getSname()%></td>
										<td><%=anko.getName()%></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<div class="st_form">
							<%
								if (status != null) {
									if (status.equals("完了3")) {
							%>
							<p>削除完了しました。</p>
							<%
								session.removeAttribute("status");
									} else if (status.equals("nai3")) {
							%>
							<p>指定されたものはありません。</p>
							<%
								session.removeAttribute("status");
									} else if (status.equals("Exception3")) {
							%>
							<p>エラーが発生しました。</p>
							<%
								session.removeAttribute("status");
									}
								}
							%>
							<h1 style="white-space: nowrap; font-size: 120%;margin: 0;">削除画面</h1>
							<div id="buttons" style="margin: 0;">
								<input type="submit" value="削除" class="execute">
							</div>
						</div>
					</form>
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
		function Display(no) {

			if (no == "no1") {

				document.getElementById("SW1").style.display = "";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";

			} else if (no == "no2") {

				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "";
				document.getElementById("SW3").style.display = "none";

			} else if (no == "no3") {
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "";
			}

		}
	</script>
	<script type="text/javascript">
		$(window)
				.load(
						function() {

							$(document)
									.ready(
											function() {
												$('input[type=checkbox]')
														.click(
																function() {
																	var t = $(this).prop;
																	if (t('checked'))
																		t(
																				'checked',
																				'');
																	else
																		t(
																				'checked',
																				'checked');
																});

												$('table tr')
														.click(
																function() {
																	var c = $(
																			this)
																			.children(
																					'td')
																			.children(
																					'input[type=checkbox]');
																	if (c
																			.prop('checked'))
																		c
																				.prop(
																						'checked',
																						'');
																	else
																		c
																				.prop(
																						'checked',
																						'checked');
																});
											});

						});
	</script>
	<script type="text/javascript">
		$(window)
				.load(
						function() {

							$(document)
									.ready(
											function() {
												$('input[type=radio]')
														.click(
																function() {
																	var t = $(this).prop;
																	if (t('checked'))
																		t(
																				'checked',
																				'');
																	else
																		t(
																				'checked',
																				'checked');
																});

												$('table tr')
														.click(
																function() {
																	var c = $(
																			this)
																			.children(
																					'td')
																			.children(
																					'input[type=radio]');
																	if (c
																			.prop('checked'))
																		c
																				.prop(
																						'checked',
																						'');
																	else
																		c
																				.prop(
																						'checked',
																						'checked');
																});
											});

						});
	</script>
</body>
</html>