<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.TeacherDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資格情報</title>
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<TeacherDTO> result = (ArrayList<TeacherDTO>) request.getAttribute("teacher");
	%>
	<table>
		<thead>
			<tr>
				<th>教師ID</th>
				<th>氏名</th>
				<th>フリガナ</th>
				<th>メールアドレス</th>
				<th>管理者権限</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (TeacherDTO anko : result) {
			%>
			<tr>
				<td><%=anko.getId()%></td>
				<td><%=anko.getName()%></td>
				<td><%=anko.getNamek()%></td>
				<td><%=anko.getEmail()%></td>
				<%
					if (anko.getAdmin() == 1) {
				%>
				<td>有</td>
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

	<%
		HttpSession sessions = request.getSession(true);
		/* 認証失敗から呼び出されたのかどうか */
		Object status = session.getAttribute("status");
		if (status != null) {
			if (status.equals("完了")) {
	%>
	<p>登録情報を更新しました。</p>
	<%
		session.setAttribute("status", null);
			} else if (status.equals("nai")) {
	%>
	<p>ないよぉ</p>
	<%
		session.setAttribute("status", null);
			} else if (status.equals("Exception")) {
	%>
	<p>エラーが発生しました。</p>
	<%
		session.setAttribute("status", null);
			}
		}
	%>
	<form action="/Qualification_System/Teacher" method="post">
		<table>
			<tr>
				<th>教師ID</th>
				<td><input type="number" name="id" required></td>
			</tr>
			<tr>
				<th>名前</th>
				<td>姓<input type="text" name="name1" required> 名<input
					type="text" name="name2" required>
				</td>
			</tr>
			<tr>
				<th>フリガナ</th>
				<td>姓<input type="text" name="kata1" required> 名<input
					type="text" name="kata2" required>
				</td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td><input type="email" name="email" required></td>
			</tr>
			<tr>
				<th>秘密の質問</th>
				<td><select name="question" required>
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
				</select></td>
			</tr>
			<tr>
				<th>質問の答え</th>
				<td><input type="text" name="answer" required></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" name="pass1" required></td>
			</tr>
			<tr>
				<th>新規パスワード</th>
				<td><input type="password" name="pass2" required></td>
			</tr>
			<tr>
				<th>管理者権限</th>
				<td><select name="admin" required>
						<option value="0" selected>---</option>
						<option value="0">無</option>
						<option value="1">有</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="登録">
	</form>
</body>
</html>