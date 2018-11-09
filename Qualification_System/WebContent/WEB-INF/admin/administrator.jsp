<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.TeacherDTO"%>
<%@ page import="masteDTO.StudentDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報</title>
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<TeacherDTO> results = (ArrayList<TeacherDTO>) request.getAttribute("teacher");
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
	<table>
		<thead>
			<tr>
				<th>教師ID</th>
				<th>氏名</th>
				<th>管理者権限</th>
			</tr>
		</thead>
		<tbody>
			<%
					for (TeacherDTO anko : results) {
				%>
			<tr>
				<td><%=anko.getId()%></td>
				<td><%=anko.getName()%></td>
				<%if(anko.getAdmin()==1){ %>
				<td>有</td>
				<%}else{ %>
				<td>無</td>
				<%} %>
			</tr>
			<%
					}
				%>
		</tbody>
	</table>
	<h1>管理者権限</h1>
	<form action="/Qualification_System/Administrator_Result" method="post">
		<table>
			<tr>
				<th>教師ID</th>
				<td><input type="text" name="tid" list="id"
					placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
						id="id">
						<%
								for (TeacherDTO anco : results) {
							%>
						<option value=<%=anco.getId()%>><%=anco.getName()%></option>
						<%
								}
							%>
					</datalist></td>
			</tr>
			<tr>
				<th>管理者パスワード</th>
				<td><input type="password" name="pass" required></td>
			</tr>
		</table>
		<input type="submit" value="リセット">
	</form>
</body>
</html>