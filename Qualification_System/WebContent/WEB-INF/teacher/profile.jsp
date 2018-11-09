<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="masteDTO.TeacherDTO"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録内容変更</title>
</head>
<body>
	<%
	TeacherDTO result = (TeacherDTO) request.getAttribute("pro");
	%>
	<form action="/Qualification_System/ProfileResult" method="post">
		<table>

			<tbody>
				<tr>
					<th>氏名</th>
					<td><input type="text" name="name" required
						value="<%=result.getName()%>"></td>
				</tr>
				<tr>
					<th>氏名（カタカナ）</th>
					<td><input type="text" name="namek" required
						value="<%=result.getNamek()%>"></td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td><input type="email" name="email" required
						value="<%=result.getEmail()%>"></td>
				</tr>
				<tr>
					<th>質問</th>
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
					<td><input type=text name="answer" required></td>
				</tr>
				<tr>
					<th>従来パスワード</th>
					<td><input type="password" name="pass"></td>
				</tr>
				<tr>
					<th>新規パスワード</th>
					<td><input type="password" name="pass1"></td>
				</tr>
				<tr>
					<th>パスワード(確認用)</th>
					<td><input type="password" name="pass2"></td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="変更">
	</form>
</body>
</html>