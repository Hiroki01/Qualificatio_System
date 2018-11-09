<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.CourseDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コース情報操作</title>
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<CourseDTO> result = (ArrayList<CourseDTO>) request.getAttribute("course");
	%>
	<table style="float: right">
		<thead>
			<tr>
				<th>学科ＩＤ</th>
				<th>学科名</th>
				<th>コースID</th>
				<th>コース名</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (CourseDTO anko : result) {
			%>
			<tr>
				<td><%=anko.getDepartment_id() %></td>
				<td><%=anko.getDname() %></td>
				<td><%=anko.getCourse_id()%></td>
				<td><%=anko.getName()%></td>
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
	<input type="submit" value="新規情報登録" onclick="Display('no1')">
	<input type="submit" value="登録情報更新" onclick="Display('no2')">
	<input type="submit" value="登録情報削除" onclick="Display('no3')">
	<div id="SW1">
		<h1>新規情報登録</h1>
		<form action="/Qualification_System/CourseRegistration"
			method="post">
			<table>
				<tr>
					<th>学科ID</th>
					<td><input type="number" name="did" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (CourseDTO anco : result) {
							%>
							<option value=<%=anco.getDepartment_id()%>><%=anco.getDname()%></option>
							<%
								}
							%>
						</datalist></td>
				<tr>
					<th>コースID</th>
					<td><input type="number" name="id" required></td>
				</tr>
				<tr>
					<th>コース名</th>
					<td><input type="text" name="name" required></td>
				</tr>
			</table>
			<input type="submit" value="登録">
		</form>
	</div>
	<div id="SW2" style="display: none;">
		<h1>登録情報更新</h1>
		<form action="/Qualification_System/CourseUpdate" method="post">
			<table>
				<tr>
					<th>学科ID</th>
					<td><input type="number" name="id" list="did"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (CourseDTO anco : result) {
							%>
							<option value=<%=anco.getDepartment_id()%>><%=anco.getDname()%></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
				<tr>
					<th>コースID</th>
					<td><input type="number" name="id" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (CourseDTO anco : result) {
							%>
							<option value=<%=anco.getCourse_id()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
				<tr>
				<th>コース名</th>
					<td><input type="text" name="name" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (CourseDTO anco : result) {
							%>
							<option value=<%=anco.getName()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
			</table>
			<input type="submit" value="更新">
		</form>
	</div>
	<div id="SW3" style="display: none;">
		<form action="/Qualification_System/CourseDelete" method="post">
			<table>
				<tr>
					<th>コース名</th>
					<td><input type="text" name="name" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (CourseDTO anco : result) {
							%>
							<option value=<%=anco.getName()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
			</table>
			<input type="submit" value="更新">
		</form>
	</div>
	<script type="text/javascript">
		function Display(no) {

			if (no == "no1") {

				document.getElementById("SW1").style.display = "";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";

			} else if (no == "no2") {

				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "";
				document.getElementById("SW3").style.display = "none";

			} else if (no == "no3") {
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "";
			}

		}
	</script>
</body>
</html>