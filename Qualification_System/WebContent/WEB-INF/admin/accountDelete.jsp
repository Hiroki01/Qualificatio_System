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
		ArrayList<StudentDTO> result = (ArrayList<StudentDTO>) request.getAttribute("student");
		@SuppressWarnings("unchecked")
		ArrayList<TeacherDTO> results = (ArrayList<TeacherDTO>) request.getAttribute("teacher");
	%>


	<input type="submit" value="生徒" onclick="Display('no1')">
	<input type="submit" value="教師" onclick="Display('no2')">

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
	<div id="SW1">
		<table>
			<thead>
				<tr>
					<th>学籍番号</th>
					<th>氏名</th>
					<th>学科</th>
					<th>コース</th>
					<th>学年</th>
					<th>組</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (StudentDTO anko : result) {
				%>
				<tr>
					<td><%=anko.getId()%></td>
					<td><%=anko.getName()%></td>
					<td><%=anko.getDepartment_name()%></td>
					<td><%=anko.getCourse_name()%></td>
					<td><%=anko.getSchool_year()%></td>
					<td><%=anko.getSet_in()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

		<h1>生徒削除</h1>
		<form action="/Qualification_System/AccountDelete" method="post">
			<table>
				<tr>
					<th>学籍番号</th>
					<td><input type="number" name="sid" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="id">
							<%
								for (StudentDTO anco : result) {
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
			<input type="submit" value="削除">
		</form>
	</div>
	<div id="SW2" style="display: none;">
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
		<h1>教師削除</h1>
		<form action="/Qualification_System/AccountDelete" method="post">
			<table>
				<tr>
					<th>教師ID</th>
					<td><input type="number" name="tid" list="id"
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
			<input type="submit" value="削除">
		</form>
	</div>
	<script type="text/javascript">
		function Display(no) {

			if (no == "no1") {

				document.getElementById("SW1").style.display = "";
				document.getElementById("SW2").style.display = "none";

			} else if (no == "no2") {

				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "";
			}

		}
	</script>
	</body>
</html>