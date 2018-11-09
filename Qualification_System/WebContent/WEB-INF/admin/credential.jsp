<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.CredentialDTO"%>
<%@ page import="masteDTO.ClassificationDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資格情報</title>
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<CredentialDTO> result = (ArrayList<CredentialDTO>) request.getAttribute("QuaName");
		@SuppressWarnings("unchecked")
		ArrayList<ClassificationDTO> reu = (ArrayList<ClassificationDTO>) request.getAttribute("class");
	%>
	<table>
		<thead>
			<tr>
				<th>資格ID</th>
				<th>資格名</th>
				<th>難易度</th>
				<th>主催</th>
				<th>上位資格</th>
				<th>上位資格</th>
				<th>上位資格</th>
				<th>上位資格</th>
				<th>下位資格</th>
				<th>下位資格</th>
				<th>下位資格</th>
				<th>下位資格</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (CredentialDTO anko : result) {
			%>
			<tr>
				<td><%=anko.getId()%></td>
				<td><%=anko.getName()%></td>
				<td><%=anko.getLevel()%></td>
				<td><%=anko.getCname()%></td>
				<td><%=anko.getS1()%></td>
				<td><%=anko.getS2()%></td>
				<td><%=anko.getS3()%></td>
				<td><%=anko.getS4()%></td>
				<td><%=anko.getL1()%></td>
				<td><%=anko.getL2()%></td>
				<td><%=anko.getL3()%></td>
				<td><%=anko.getL4()%></td>
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
		<form action="/Qualification_System/CredentialRegistration"
			method="post">
			<table>
				<tr>
					<th>資格ID</th>
					<td><input type="number" name="id" required></td>
					<th>上位資格名</th>
					<td><input type="text" name="s1" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"> <datalist
							id="s">
							<%
								for (CredentialDTO anco : result) {
							%>
							<option value=<%=anco.getId()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
					<th>下位資格名</th>
					<td><input type="text" name="l1" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
				</tr>
				<tr>
					<th>資格名</th>
					<td><input type="text" name="name" required></td>
					<th>上位資格名</th>
					<td><input type="text" name="s2" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
					<th>下位資格名</th>
					<td><input type="text" name="l2" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
				</tr>
				<tr>
					<th>資格区分</th>
					<td><input type="text" name="class" list="c"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="c">
							<%
								for (ClassificationDTO anco : reu) {
							%>
							<option value=<%=anco.getCfid()%>><%=anco.getCfname()%></option>
							<%
								}
							%>
						</datalist></td>
					<th>上位資格名</th>
					<td><input type="text" name="s3" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
					<th>下位資格名</th>
					<td><input type="text" name="l3" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
				</tr>
				<tr>
					<th>資格難易度</th>
					<td><input type="text" name="level" list="l"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="l">
							<option value="--">------</option>
							<option value="S">S</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
							<option value="E">E</option>
						</datalist></td>
					<th>上位資格名</th>
					<td><input type="text" name="s4" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
					<th>下位資格名</th>
					<td><input type="text" name="l4" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
				</tr>
			</table>
			<input type="submit" value="登録">
		</form>
	</div>
	<div id="SW2" style="display: none;">
		<form action="/Qualification_System/CredentialUpdate" method="post">
			<table>
				<tr>
					<th>資格ID</th>
					<td><input type="number" name="id" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (CredentialDTO anco : result) {
							%>
							<option value=<%=anco.getName()%>><%=anco.getId()%></option>
							<%
								}
							%>
						</datalist></td>
					<th>上位資格名</th>
					<td><input type="text" name="s1" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"> <datalist
							id="s">
							<%
								for (CredentialDTO anco : result) {
							%>
							<option value=<%=anco.getId()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
					<th>下位資格名</th>
					<td><input type="text" name="l1" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
				</tr>
				<tr>
					<th>資格名</th>
					<td><input type="text" name="name" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (CredentialDTO anco : result) {
							%>
							<option value=<%=anco.getId()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
					<th>上位資格名</th>
					<td><input type="text" name="s2" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
					<th>下位資格名</th>
					<td><input type="text" name="l2" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
				</tr>
				<tr>
					<th>資格区分</th>
					<td><input type="text" name="class" list="c"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="c">
							<%
								for (ClassificationDTO anco : reu) {
							%>
							<option value=<%=anco.getCfid()%>><%=anco.getCfname()%></option>
							<%
								}
							%>
						</datalist></td>
					<th>上位資格名</th>
					<td><input type="text" name="s3" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
					<th>下位資格名</th>
					<td><input type="text" name="l3" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
				</tr>
				<tr>
					<th>資格難易度</th>
					<td><input type="text" name="level" list="l"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="l">
							<option value="--">------</option>
							<option value="S">S</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
							<option value="E">E</option>
						</datalist></td>
					<th>上位資格名</th>
					<td><input type="text" name="s4" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
					<th>下位資格名</th>
					<td><input type="text" name="l4" list="s"
						placeholder="テキスト入力又はクリック" autocomplete="off"></td>
				</tr>
			</table>
			<input type="submit" value="更新">
		</form>
	</div>
	<div id="SW3" style="display: none;">
		<form action="/Qualification_System/CredentialDelete" method="post">
			<table>
				<tr>
					<th>資格名</th>
					<td><input type="text" name="name" list="id"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="s">
							<%
								for (CredentialDTO anco : result) {
							%>
							<option value=<%=anco.getId()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
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