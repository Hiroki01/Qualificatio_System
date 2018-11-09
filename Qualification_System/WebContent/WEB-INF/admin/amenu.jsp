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
				<a href="/Qualification_System/Account">アカウント</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/Classification">資格区分</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/Department">学科</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/Course">コース</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/Credential_information">資格情報</a>
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