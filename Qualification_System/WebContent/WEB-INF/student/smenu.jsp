<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/Qualification_System/css/style.css"
	type="text/css" media="print, projection, screen" />
<script type="text/javascript"
	src="/Qualification_System/js/openclose.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/ddmenu_min.js"></script>
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
			<h1 id="title">トップ</h1>
			<hr>
			<div id="main">
				 <div class="a_top_back">
				 <h3>資格取得状況管理システム</h3>
                    <p style="margin-left: 5%;">資格管理システムへようこそ</p>
                    <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>
                </div>
			</div>
			<!--/contents-->
		</div>
		<!--/container-->
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
