<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.CredentialDTO"%>
<%@ page import="masteDTO.StudentDTO"%>
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
<script src="/js/jquery-1.12.4.min.js"></script>
<title>学籍番号登録</title>
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
			<h1 id="title">学籍番号一括登録</h1>
			<div id="main">
				<%
					@SuppressWarnings("unchecked")
					ArrayList<QualificationDTO> qualification = (ArrayList<QualificationDTO>) request
							.getAttribute("qualification");
					@SuppressWarnings("unchecked")
					ArrayList<StudentDTO> student = (ArrayList<StudentDTO>) request.getAttribute("student");
					@SuppressWarnings("unchecked")
					ArrayList<CredentialDTO> credential = (ArrayList<CredentialDTO>) request.getAttribute("credential");
				%>
				<div id="wrap" style="height: 200px;">
					<table border="1" style="font-size: 120%; height: 20; width: 70%;"
						id="all" class="tablesorter">
						<thead>
							<tr>
								<th>学籍番号</th>
								<th>生徒氏名</th>
								<th>資格名</th>
								<th>受験日</th>
								<th>結果</th>
								<th>上位資格名</th>
								<th>上位資格名</th>
								<th>上位資格名</th>
								<th>上位資格名</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (QualificationDTO qua : qualification) {
							%>
							<tr>
								<td><%=qua.getSid()%></td>
								<td><%=qua.getSname()%></td>
								<td><%=qua.getCname()%></td>
								<td><%=qua.getExamdate()%></td>
								<td><%=qua.getResult()%></td>
								<%
									if (qua.getS1() == null) {
								%>
								<td>無</td>
								<%
									} else {
								%>
								<td><%=qua.getS1()%></td>
								<%
									}
										if (qua.getS2() == null) {
								%>
								<td>無</td>
								<%
									} else {
								%>
								<td><%=qua.getS2()%></td>
								<%
									}
										if (qua.getS3() == null) {
								%>
								<td>無</td>
								<%
									} else {
								%>
								<td><%=qua.getS3()%></td>
								<%
									}
										if (qua.getS4() == null) {
								%>
								<td>無</td>
								<%
									} else {
								%>
								<td><%=qua.getS4()%></td>
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
				<hr>
				<form action="/Qualification_System/StudentResult" method="post">
				 <div class="i_regi_form2">
                        <table border="1">
                            <tr>
                                <th style="white-space: nowrap;">学籍番号</th>
                                <td style="text-align: left; padding-left: 2px;"><input type="number"
									name="sid" autocomplete="on" list="keywords"
									placeholder="学籍番号を入力" required="required"> <datalist
										id="keywords">
										<%
											for (StudentDTO anco : student) {
										%>
										<option value=<%=anco.getId()%>>
											<%=anco.getName()%></option>
										<%
											}
										%>
									</datalist>～ <input type="search" name="sid" autocomplete="on"
									list="keywords" placeholder="学籍番号を入力"></td>
                            </tr>
                            <tr>
                                <th style="white-space: nowrap;">資格名</th>
                                <td style="text-align: left; padding-left: 2px;"><select name="cfname" required="required">
										<option selected>受験資格を選択してください</option>
										<%
											for (CredentialDTO anko : credential) {
										%>
										<option value="<%=anko.getName()%>"><%=anko.getName()%></option>
										<%
											}
										%>
								</select></td>
                            </tr>
                            <tr>
                                <th style="white-space: nowrap;">受験日</th>
                                <td style="text-align: left; padding-left: 2px;"><input type="date"
									name="date" required></td>
                            </tr>
                        </table>
                        <div id="buttons">
                           <input type="button"  onClick="history.go(-1)" class="return" value="戻る">
                           <input type="submit" class="execute" value="登録" style="margin-left: 10%;">
                        </div>
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
			$("#all").tablesorter();
		});
	</script>
</body>
</html>