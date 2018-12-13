<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="masteDTO.TeacherDTO"%>
<%@ page import="java.util.ArrayList"%>
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
<script type="text/javascript" src="../js/pagechange.js"></script>
<script src="/js/jquery-1.12.4.min.js"></script>
<title>検索</title>
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
			<h1 id="title">登録情報変更</h1>
			<hr>
			<div id="main">

				<%
					TeacherDTO result = (TeacherDTO) request.getAttribute("pro");
					String[] name = (result.getName()).split(" ");
					String[] namek = (result.getNamek()).split(" ");
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
				<div class="form_area">
					<form>
						<dl class="sinki_form" style="white-space: nowrap;">
							<dt>名前</dt>
							<dd>
								姓<input type="text" name="nameA" value="<%=name[0]%>" required>
								名<input type="text" name="nameB" value="<%=name[1]%>" required>
							</dd>
							<dt>フリガナ</dt>
							<dd>
								姓<input type="text" name="namekA" value="<%=namek[0]%>" required>
								名<input type="text" name="namekB" value="<%=namek[1]%>" required>
							</dd>
							<dt>メールアドレス</dt>
							<dd>
								<input type="email" value="<%=result.getEmail()%>" required>
							</dd>
							<dt>秘密の質問</dt>
							<dd>
								<select name="question" required>
									<option value="1">出身地は</option>
									<option value="2">趣味は</option>
									<option value="3">出身小学校は</option>
									<option value="4">出身中学校は</option>
									<option value="5">出身高等学校は</option>
									<option value="6">得意言語</option>
									<option value="7">尊敬している人</option>
									<option value="8">好きな食べ物</option>
									<option value="9">親友の名前</option>
									<option value="10">得意教科</option>
									<option value="11">好きな芸能人</option>
									<option value="12">嫌いな人</option>
									<option value="13">好きな月</option>
									<option value="14">好きなゲーム</option>
									<option value="15">嫁</option>
								</select>
							</dd>
							<dt>質問の答え</dt>
							<dd>
								<input type="text" name="answer" required>
							</dd>
							<dt>従来パスワード</dt>
							<dd>
								<input type="password" name="pass" required>
							</dd>
							<dt>変更後パスワード</dt>
							<dd>
								<input type="password" name="pass1" required>
							</dd>
							<dt>パスワード（確認用）</dt>
							<dd>
								<input type="password" name="pass2" required>
							</dd>
						</dl>
					</form>
					<form>
						<div id="f_buttons" style="margin-top: 7px; margin-bottom: 15px;">
							<input type="button" class="return" value="戻る"
								onClick="history.go(-1)"> <input type="submit"
								class="execute" value="変更" style="margin-left: 20%;">
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
		$(document).ready(function() {
			$("#all").tablesorter();
		});
	</script>
</body>
</html>