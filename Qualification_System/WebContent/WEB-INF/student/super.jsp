<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.DTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上位資格確認</title>
</head>
<body>
	<ul>
		<li><p>
				<a href="/Qualification_System/Registration">新規登録・取得済み登録</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/All">受験資格一覧</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/Superior">上位資格確認</a>
			</p></li>
		<li><p>
				<a href="/Qualification_System/Profile">登録情報変更</a>
			</p></li>
	</ul>
	<table>
		<thead>
			<tr>
				<th>取得済み資格名</th>
				<th>上位資格名</th>
				<th>資格区分</th>
				<th>難易度</th>
			</tr>
		</thead>
		<tbody>
			<%
			@SuppressWarnings("unchecked")
				ArrayList<DTO> result = (ArrayList<DTO>) request.getAttribute("Super");
				for (DTO anko : result) {
			%>
			<tr>
				<td><%=anko.getCname()%></td>
				<td><%=anko.getSname()%></td>
				<td><%=anko.getCfname()%></td>
				<td><%=anko.getLevel()%></td>
			</tr>
			<%
				}
			%>
		</tbody>

	</table>
</body>
</html>