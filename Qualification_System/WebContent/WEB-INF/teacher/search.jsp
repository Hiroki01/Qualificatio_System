<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.DepartmentDTO"%>
<%@ page import="masteDTO.CourseDTO"%>
<%@ page import="masteDTO.StudentDTO"%>
<%@ page import="masteDTO.CredentialDTO"%>
<%@ page import="masteDTO.SubjectDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Qualification_System/css/style.css"
	type="text/css" />
<link rel="stylesheet"
	href="/Qualification_System/css/easy-responsive-tabs.css"
	type="text/css" />
<script type="text/javascript"
	src="/Qualification_System/js/openclose.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/ddmenu_min.js"></script>
<script src="/Qualification_System/js/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/jquery-1.6.3.min.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/easyResponsiveTabs.js"></script>
<title>検索</title>
</head>
<%
	@SuppressWarnings("unchecked")
	ArrayList<DepartmentDTO> department = (ArrayList<DepartmentDTO>) request.getAttribute("department");
	@SuppressWarnings("unchecked")
	ArrayList<CourseDTO> course = (ArrayList<CourseDTO>) request.getAttribute("course");
	@SuppressWarnings("unchecked")
	ArrayList<StudentDTO> student = (ArrayList<StudentDTO>) request.getAttribute("student");
	@SuppressWarnings("unchecked")
	ArrayList<CredentialDTO> credential = (ArrayList<CredentialDTO>) request.getAttribute("credential");
	@SuppressWarnings("unchecked")
	ArrayList<SubjectDTO> subject = (ArrayList<SubjectDTO>) request.getAttribute("subject");
	HttpSession sessions = request.getSession(true);
	/* 認証失敗から呼び出されたのかどうか */
	Object status = session.getAttribute("status");
%>
<body>
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
		<h1 id="title">検索</h1>
		<hr>
		<div id="main">
			<%
				if (status != null) {
					if (status.equals("nai")) {
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
			<div id="demoTab">
				<ul class="resp-tabs-list">
					<li>学科検索</li>
					<li>コース検索</li>
					<li>クラス検索</li>
					<li>学籍番号検索</li>
					<li>受験日検索</li>
					<li>期日検索</li>
					<li>資格検索</li>
				</ul>

				<div class="resp-tabs-container">
					<div>
						<h2>学科検索</h2>
						<form action="/Qualification_System/DepartmentSearch"
							method="post">
							<div style="text-align: center;">
								<table class="form_table">
									<tr>
										<td style="text-align: right;">学科：</td>
										<td style="text-align: left;"><select name="did" required>
												<option value="0" selected>検索したい学科を選択してください</option>
												<%
													for (DepartmentDTO anco : department) {
												%>
												<option value="<%=anco.getId()%>"><%=anco.getName()%></option>
												<%
													}
												%>
										</select></td>
									</tr>
									<tr>
										<td style="text-align: right;">資格名：</td>
										<td style="text-align: left;"><select name="cfname">
												<option value="0" selected>資格を含めて検索したい場合に入力してください</option>
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
										<td style="text-align: right;">結果：</td>
										<td style="text-align: left;"><select name="outcome">
												<option value="0" selected>結果を含めて検索したい場合に選択してください</option>
												<option value="未受験">未受験</option>
												<option value="合格">合格</option>
												<option value="不合格">不合格</option>
										</select></td>
									</tr>
								</table>
							</div>
							<div id="buttons">
								<input type="button" class="return" value="戻る"
									onClick="history.go(-1)"><input type="submit"
									class="execute" value="検索" style="margin-left: 10%;">
							</div>
						</form>
					</div>

					<div>
						<h2>コース検索</h2>
						<form action="/Qualification_System/CourseSearch" method="post">
							<table class="table_form">
								<tr>
									<td style="text-align: right;">コース：</td>
									<td style="text-align: left;"><select name="course"
										required>
											<option value="0" selected>検索したいコースを選択してください</option>
											<%
												for (CourseDTO anco : course) {
											%>
											<option value="<%=anco.getCourse_id()%>"><%=anco.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">資格名：</td>
									<td style="text-align: left;"><select name="cfname">
											<option value="0" selected>資格を含めて検索したい場合に入力してください</option>
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
									<td style="text-align: right;">結果：</td>
									<td style="text-align: left;"><select name="outcome">
											<option value="0" selected>結果を含めて検索したい場合に選択してください</option>
											<option value="未受験">未受験</option>
											<option value="合格">合格</option>
											<option value="不合格">不合格</option>
									</select></td>
								</tr>
							</table>
							<div id="buttons">
								<input type="button" onClick="history.go(-1)" class="return"
									value="戻る"> <input type="submit" class="execute"
									value="検索" style="margin-left: 10%;">
							</div>
						</form>
					</div>
					<div>
						<h2>クラス検索</h2>
						<form action="/Qualification_System/ClassSearch" method="post">
							<table class="form_table">
								<tr>
									<td style="text-align: right;">主要学科：</td>
									<td style="text-align: left;"><select name="subject"
										required>
											<%
												for (SubjectDTO anko : subject) {
											%>
											<option value="<%=anko.getId()%>"><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">学年・組：</td>
									<td style="text-align: left;"><select name="year" required>
											<option value="1" selected>1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
									</select>年<select name="class" required>
											<option value="1" selected>1</option>
											<option value="2">2</option>
									</select>組</td>
								</tr>
								<tr>
									<td style="text-align: right;">受験資格名:</td>
									<td style="text-align: left;"><select name="cfname"
										required>
											<option value="0" selected>選択してください</option>
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
									<td style="text-align: right;">結果：</td>
									<td style="text-align: left;"><select name="outcome">
											<option selected>結果を含めて検索したい場合に選択してください</option>
											<option value="未受験">未受験</option>
											<option value="合格">合格</option>
											<option value="不合格">不合格</option>
									</select></td>
								</tr>
							</table>
							<div id="buttons">
								<input type="button" class="return" value="戻る"
									onClick="history.go(-1)"> <input type="submit"
									class="execute" value="検索" style="margin-left: 10%;">
							</div>
						</form>
					</div>
					<div>
						<h2>学籍番号検索</h2>
						<form action="/Qualification_System/StudentNumberSearch"
							method="post">
							<table class="form_table">
								<tr>
									<td style="text-align: right;">学籍番号：</td>
									<td style="text-align: left;"><input type="search"
										name="sid" autocomplete="on" list="keywords"
										placeholder="学籍番号を入力" required="required"> <datalist
											id="keywords">
											<%
												for (StudentDTO anco : student) {
											%>
											<option value="<%=anco.getId()%>">
												<%=anco.getName()%></option>
											<%
												}
											%>
										</datalist></td>
								</tr>
								<tr>
									<td style="text-align: right;">結果：</td>
									<td style="text-align: left;"><select name="outcome">
											<option selected>結果を含めて検索したい場合に選択してください</option>
											<option value="未受験">未受験</option>
											<option value="合格">合格</option>
											<option value="不合格">不合格</option>
									</select></td>
								</tr>
							</table>
							<div id="buttons">
								<input type="button" class="return" value="戻る"
									onClick="history.go(-1)"> <input type="submit"
									class="execute" value="検索" style="margin-left: 10%;">
							</div>
						</form>
					</div>
					<div>
						<h2>受験日検索</h2>
						<form action="/Qualification_System/DateSearch" method="post">
							<table class="form_table">
								<tr>
									<td style="text-align: right;">受験日：</td>
									<td style="text-align: left;"><input type="date"
										name="date" required></td>
								</tr>
								<tr>
									<td style="text-align: right;">受験資格名:</td>
									<td style="text-align: left;"><select name="cfname"
										required>
											<option value="0" selected>資格を含めて検索したい場合に選択してください</option>
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
									<td style="text-align: right;">結果：</td>
									<td style="text-align: left;"><select name="outcome">
											<option value="0" selected>結果を含めて検索したい場合に選択してください</option>
											<option value="未受験">未受験</option>
											<option value="合格">合格</option>
											<option value="不合格">不合格</option>
									</select></td>
								</tr>
							</table>
							<div id="buttons">
								<input type="button" class="return" value="戻る"
									onClick="history.go(-1)"> <input type="submit"
									class="execute" value="検索" style="margin-left: 10%;">
							</div>
						</form>
					</div>
					<div>
						<h2>期日検索</h2>
						<form action="/Qualification_System/PeriodSearch" method="post">
							<table class="form_table">
								<tr>
									<td style="text-align: right;">受験日(開始)：</td>
									<td style="text-align: left;"><input type="date"
										name="date" required></td>
								</tr>
								<tr>
									<td style="text-align: right;">受験日(終了)：</td>
									<td style="text-align: left;"><input type="date"
										name="date2"></td>
								</tr>
								<tr>
									<td style="text-align: right;">受験資格名:</td>
									<td style="text-align: left;"><select name="cfname">
											<option value="0" selected>資格を含めて検索したい場合に選択してください</option>
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
									<td style="text-align: right;">資格難易度：</td>
									<td style="text-align: left;"><select name="level"
										required>
											<option selected>難易度を含めて検索したい場合に選択してください</option>
											<option value="A">A</option>
											<option value="B">B</option>
											<option value="C">C</option>
											<option value="D">D</option>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">結果：</td>
									<td style="text-align: left;"><select name="outcome">
											<option selected>結果を含めて検索したい場合に選択してください</option>
											<option value="未受験">未受験</option>
											<option value="合格">合格</option>
											<option value="不合格">不合格</option>
									</select></td>
								</tr>
							</table>
							<div id="buttons">
								<input type="button" class="return" value="戻る"
									onClick="history.go(-1)"> <input type="submit"
									class="execute" value="検索" style="margin-left: 10%;">
							</div>
						</form>
					</div>
					<div>
						<h2>資格検索</h2>
						<form action="/Qualification_System/QualificationSearch"
							method="post">
							<table class="form_table">
								<tr>
									<td style="text-align: right;">受験資格名:</td>
									<td style="text-align: left;"><select name="cfname"
										required>
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
									<td style="text-align: right;">結果：</td>
									<td style="text-align: left;"><select name="outcome">
											<option selected>結果を含めて検索したい場合に選択してください</option>
											<option value="未受験">未受験</option>
											<option value="合格">合格</option>
											<option value="不合格">不合格</option>
									</select></td>
								</tr>
							</table>
							<div id="buttons">
								<input type="button" class="return" value="戻る"
									onClick="history.go(-1)"> <input type="submit"
									class="execute" value="検索" style="margin-left: 10%;">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#demoTab").easyResponsiveTabs({
				type : 'default', //タイプ、default：デフォルト, vertical：垂直, accordion：アコーディオン
				width : 'auto', //幅、レスポンシブなのでauto、ピクセル指定も可
				fit : true
			// コンテンツに100%フィットさせるかどうか
			});
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