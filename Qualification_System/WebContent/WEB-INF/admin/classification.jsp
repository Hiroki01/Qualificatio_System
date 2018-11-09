<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.ClassificationDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学科情報</title>
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<ClassificationDTO> result = (ArrayList<ClassificationDTO>) request.getAttribute("class");
	%>
	<table style="float: right">
		<thead>
			<tr>
				<th>資格区分ID</th>
				<th>主催名</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (ClassificationDTO anko : result) {
			%>
			<tr>
				<td><%=anko.getCfid()%></td>
				<td><%=anko.getCfname()%></td>
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
		<form action="/Qualification_System/ClassificationRegistration"
			method="post">
			<table>
				<tr>
					<th>資格区分ID</th>
					<td><input type="number" name="id" required></td>
				</tr>
				<tr>
					<th>主催名</th>
					<td><input type="text" name="name" required></td>
				</tr>
			</table>
			<input type="submit" value="登録">
		</form>
	</div>
	<div id="SW2" style="display: none;">
		<h1>登録情報更新</h1>
		<form action="/Qualification_System/ClassificationUpdate" method="post">
			<table>
				<tr>
					<th>資格区分ID</th>
					<td><input type="number" name="id" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (ClassificationDTO anco : result) {
							%>
							<option value=<%=anco.getCfname()%>><%=anco.getCfid()%></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
				<tr>
					<th>主催名</th>
					<td><input type="text" name="name" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (ClassificationDTO anco : result) {
							%>
							<option value=<%=anco.getCfid()%>><%=anco.getCfname()%></option>
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
		<form action="/Qualification_System/ClassificationDelete" method="post">
			<table>
				<tr>
					<th>主催名</th>
					<td><input type="text" name="name" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (ClassificationDTO anco : result) {
							%>
							<option value=<%=anco.getCfname()%>><%=anco.getCfname()%></option>
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