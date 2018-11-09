<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.DTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー画面</title>
</head>
<body>
	<ul>
		<li><p>
				<a href="/Qualification_System/Search">検索</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/Confirmation">生徒確認</a>
			</p></li>
		<li><p>
				<a href="#">登録</a>
			</p>
			<ul>
				<li><p>
						<a href="/Qualification_System/ClassRegistration">クラス</a>
					</p></li>
				<li><p>
						<a href="/Qualification_System/StudentNumber">学籍番号</a>
					</p></li>
			</ul></li>
		<li><p>
				<a href="/Qualification_System/DeleteEvent">削除</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/TProfile">登録内容変更</a>
			</p></li>

	</ul>
	<h1>メニュー</h1>

	<%
		HttpSession sessions = request.getSession(true);
	@SuppressWarnings("unchecked")
		ArrayList<DTO> result = (ArrayList<DTO>) sessions.getAttribute("log");
		if (!result.isEmpty()) {
	%>
	<table>
		<tr>
			<th>学籍番号</th>
			<th>氏名</th>
			<th>資格名</th>
			<th>難易度</th>
			<th>受験日</th>
			<th>受験結果</th>
		</tr>
		<%
			for (DTO anko : result) {
		%>
		<tr>
			<td><%=anko.getSid()%></td>
			<td><%=anko.getSname()%></td>
			<td><%=anko.getCname()%></td>
			<td><%=anko.getLevel()%></td>
			<td><%=anko.getDate()%></td>
			<td><%=anko.getResult()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>

</body>
</html>