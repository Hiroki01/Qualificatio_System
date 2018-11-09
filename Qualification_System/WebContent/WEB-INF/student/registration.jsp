<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.CredentialDTO"%>
<%@ page import="masteDTO.QualificationDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録画面</title>
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
	<h1>新規登録</h1>
	<table>
		<thead>
			<tr>
				<th>資格名</th>
				<th>資格難易度</th>
				<th>受験日</th>
				<th>結果</th>
			</tr>
		</thead>
		<tbody>
			<%
			@SuppressWarnings("unchecked")
				ArrayList<QualificationDTO> result = (ArrayList<QualificationDTO>) request.getAttribute("QuaDATE");
				for (QualificationDTO anco : result) {
			%>
			<tr>
				<td><%=anco.getCname()%></td>
				<td><%=anco.getLevel()%></td>
				<td><%=anco.getExamdate()%></td>
				<td><%=anco.getResult()%></td>
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
	<p>登録完了しました。</p>
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
<input type="submit" value="新規登録" onclick="Display('no1')">
<input type="submit" value="登録更新" onclick="Display('no2')">
<div id="SW1">
	<form action="/Qualification_System/RegistrationResult">
		<p>
			資格名<input type="text" name="QuaName" list="Qua"
				placeholder="テキスト入力又はクリック" autocomplete="off">
			<datalist id="Qua">
				<%
				@SuppressWarnings("unchecked")
					ArrayList<CredentialDTO> re = (ArrayList<CredentialDTO>) request.getAttribute("QuaName");
					for (CredentialDTO anko : re) {
				%>
				<option value=<%=anko.getName()%>><%=anko.getName()%></option>
				<%
					}
				%>

			</datalist>
			<br> 受験日<br>年<select name="year" required>
				<option value="2018"selected>2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
				<option value="2021">2021</option>
				<option value="2022">2022</option>
				<option value="2023">2023</option>
				<option value="2024">2024</option>
				<option value="2025">2025</option>
				<option value="2026">2026</option>
				<option value="2027">2027</option>
				<option value="2028">2028</option>
				<option value="2029">2029</option>
				<option value="2030">2030</option>
			</select> 月<select name="month" required>
				<option value="">-</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select> 日<select name="day">
				<option value="">-</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
				<option value="21">21</option>
				<option value="22">22</option>
				<option value="23">23</option>
				<option value="24">24</option>
				<option value="25">25</option>
				<option value="26">26</option>
				<option value="27">27</option>
				<option value="28">28</option>
				<option value="29">29</option>
				<option value="30">30</option>
				<option value="31">31</option>
			</select><br> <input type="submit" value="登録">
		</p>

	</form>
	</div><div id="SW2" style="display:none;">
	<form action="/Qualification_System/UpdateResult" method="post">
		<p>
			資格名<input type="text" name="QuaName" list="Qua"
				placeholder="テキスト入力又はクリック" autocomplete="off">
			<datalist id="Qua">
				<%
				@SuppressWarnings("unchecked")
					ArrayList<CredentialDTO> reas = (ArrayList<CredentialDTO>) request.getAttribute("QuaName");
					for (CredentialDTO ankos : reas) {
				%>
				<option value=<%=ankos.getName()%>><%=ankos.getName()%></option>
				<%
					}
				%>

			</datalist>
			<br> 受験日<br>年<select name="year" required>
				<option value="">-</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
				<option value="2021">2021</option>
				<option value="2022">2022</option>
				<option value="2023">2023</option>
				<option value="2024">2024</option>
				<option value="2025">2025</option>
				<option value="2026">2026</option>
				<option value="2027">2027</option>
				<option value="2028">2028</option>
				<option value="2029">2029</option>
				<option value="2030">2030</option>
			</select> 月<select name="month" required>
				<option value="">-</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select> 日<select name="day" required>
				<option value="">-</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
				<option value="21">21</option>
				<option value="22">22</option>
				<option value="23">23</option>
				<option value="24">24</option>
				<option value="25">25</option>
				<option value="26">26</option>
				<option value="27">27</option>
				<option value="28">28</option>
				<option value="29">29</option>
				<option value="30">30</option>
				<option value="31">31</option>
			</select><br> 結果<select name="result" required>
				<option value="---" selected>---</option>
				<option value="合格">合格</option>
				<option value="不合格">不合格</option>
				<option value="未受験">未受験</option>
			</select><br> <input type="submit" value="更新">
		</p>
	</form>
	</div>
	<script type="text/javascript">
	function Display(no){

	   if(no == "no1"){

	       document.getElementById("SW1").style.display = "";
	       document.getElementById("SW2").style.display = "none";

	   }else if(no == "no2"){

	       document.getElementById("SW1").style.display = "none";
	       document.getElementById("SW2").style.display = "";

	   }

	}
	</script>
</body>
</html>