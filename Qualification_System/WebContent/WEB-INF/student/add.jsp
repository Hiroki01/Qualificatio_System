<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録画面</title>
</head>
<body>
	<h1>新規登録</h1>
	<form action="/Qualification_System/AddResult" method="post">
		<p>
			学籍番号<input type="number" name="id" required>
		</p>
		<p>
			姓<input type="text" name="nameA" required><br>
			名<input type="text"
				name="nameB">
		</p>
		<p>
			フリガナ<input type="text" name="namekA" required><br>
			<input type="text" name="namekB">
		</p>
		<p>
			メールアドレス<input type="email" name="email" required>
		</p>
		<p>
			学科<select name="department" required>
				<option value="s1" selected>学科を選択してください</option>
				<option value="情報システム科">情報システム科</option>
				<option value="総合システム工学科">総合システム工学科</option>
				<option value="ネットワーク・セキュリティ科">ネットワーク・セキュリティ科</option>
				<option value="高度情報工学科">高度情報工学科</option>
				<option value="情報ビジネス科">情報ビジネス科</option>
				<option value="会計ビジネス科">会計ビジネス科</option>
				<option value="デザイン科">デザイン科</option>
				<option value="総合デザイン科">総合デザイン科</option>
			</select>
		</p>
		<p>
			コース<select name="course" required>
				<option selected="selected">コースを選択してください</option>
				<optgroup label="情報システム科">
					<option value="システムエンジニアコース">システムエンジニアコース</option>
					<option value="スマートフォンアプリ開発コース">スマートフォンアプリ開発コース</option>
				</optgroup>
				<optgroup label="総合システム工学科">
					<option value="WEBプログラマコース">WEBプログラマコース</option>
					<option value="組込みシステムコース">組込みシステムコース</option>
				</optgroup>
				<optgroup label="ネットワーク・セキュリティ科">
					<option value="ネットワークエンジニアコース">ネットワークエンジニアコース</option>
					<option value="情報セキュリティコース">情報セキュリティコース</option>
				</optgroup>
				<optgroup label="高度情報工学科">
					<option value="高度情報工学科">高度情報工学科</option>
				</optgroup>
				<optgroup label="情報ビジネス科">
					<option value="情報ビジネスコース">情報ビジネスコース</option>
					<option value="公共ビジネスコース">公共ビジネスコース</option>
				</optgroup>
				<optgroup label="会計ビジネス科">
					<option value="会計ビジネスコース">会計ビジネスコース</option>
					<option value="会計エキスパートコース">会計エキスパートコース</option>
				</optgroup>
				<optgroup label="デザイン科">
					<option value="グラフィックデザインコース">グラフィックデザインコース</option>
					<option value="アニメ・マンガコース">アニメ・マンガコース</option>
					<option value="CGクリエイトコース">CGクリエイトコース</option>
					<option value="建築インテリアコース">建築インテリアコース</option>
				</optgroup>
				<optgroup label="総合デザイン科">
					<option value="総合デザインコース">総合デザインコース</option>
				</optgroup>
			</select>
		</p>
		<p>
			学年<select name="school_year" required>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
			</select>
		</p>
		<p>
			クラス<select name="set_in" required>
				<option value="1">1</option>
				<option value="2">2</option>
			</select>
		</p>
		<p>
			質問<select name="question" required>
				<option value="1">故郷</option>
				<option value="2">趣味</option>
				<option value="3">小学校</option>
				<option value="4">中学校</option>
				<option value="5">高校</option>
				<option value="6">得意言語</option>
				<option value="7">ゲーム</option>
				<option value="8">アフラック</option>
				<option value="9">ドンカラス</option>
				<option value="10">(*'ω'*)</option>
			</select>
		</p>
		<p>
			質問の答え<input type=text name="answer" required>
		</p>
		<p>
			パスワード<input type="password" name="pass" required>
		</p>
		<input type="submit" value="登録">
	</form>
</body>
</html>