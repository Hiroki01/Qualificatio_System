<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.DepartmentDTO"%>
<%@ page import="masteDTO.CourseDTO"%>
<%@ page import="masteDTO.StudentDTO"%>
<%@ page import="masteDTO.CredentialDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学科情報</title>
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<DepartmentDTO> department = (ArrayList<DepartmentDTO>) request.getAttribute("department");
		@SuppressWarnings("unchecked")
		ArrayList<CourseDTO> course = (ArrayList<CourseDTO>) request.getAttribute("course");
		@SuppressWarnings("unchecked")
		ArrayList<StudentDTO> student = (ArrayList<StudentDTO>) request.getAttribute("student");
		@SuppressWarnings("unchecked")
		ArrayList<CredentialDTO> credential = (ArrayList<CredentialDTO>) request.getAttribute("credential");
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

	<input type="submit" value="学科検索" onclick="Display('no1')">
	<input type="submit" value="コース検索" onclick="Display('no2')">
	<input type="submit" value="クラス検索" onclick="Display('no3')">
	<input type="submit" value="学籍番号検索" onclick="Display('no4')">
	<input type="submit" value="受験日検索" onclick="Display('no5')">
	<input type="submit" value="期間検索" onclick="Display('no6')">
	<div id="SW1">
		<h1>学科検索</h1>
		<form action="/Qualification_System/DepartmentSearch" method="post">
			<table>
				<tr>
					<th>学科名</th>
					<td><input type="text" name="did" list="department"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="department">
							<%
								for (DepartmentDTO anco : department) {
							%>
							<option value=<%=anco.getName()%>></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
				<tr>
					<th>資格名</th>
					<td><input type="text" name="cfname" list="cedential"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="cedential">
							<%
								for (CredentialDTO anco : credential) {
							%>
							<option value=<%=anco.getName()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
			</table>
			<input type="submit" value="検索">
		</form>
	</div>
	<div id="SW2" style="display: none;">
		<h1>コース検索</h1>
		<form action="/Qualification_System/CourseSearch" method="post">
			<table>
				<tr>
					<th>コース名</th>
					<td><input type="text" name="course" list="course"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="course">
							<%
								for (CourseDTO anco : course) {
							%>
							<option value=<%=anco.getName()%>></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
				<tr>
					<th>資格名</th>
					<td><input type="text" name="cfname" list="cedential"
						placeholder="テキスト入力又はクリック" autocomplete="off" required></td>
				</tr>
			</table>
			<input type="submit" value="検索">
		</form>
	</div>
	<div id="SW3" style="display: none;">
		<h1>クラス検索</h1>
		<form action="/Qualification_System/ClassSearch" method="post">
			<table>
				<tr>
					<th>学科名</th>
					<td><input type="text" name="dname" list="department"
						placeholder="テキスト入力又はクリック" autocomplete="off" required></td>
				</tr>
				<tr>
					<th>学年</th>
					<td><select name="year" required>
							<option value="1" selected>1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
					</select></td>
				</tr>
				<tr>
					<th>クラス</th>
					<td><select name="class" required>
							<option value="1" selected>1</option>
							<option value="2">2</option>
					</select></td>
				</tr>
				<tr>
					<th>資格名</th>
					<td><input type="text" name="cfname" list="cedential"
						placeholder="テキスト入力又はクリック" autocomplete="off" required></td>
				</tr>
			</table>
			<input type="submit" value="検索">
		</form>
	</div>
	<div id="SW4" style="display: none;">
		<h1>学籍番号検索</h1>
		<form action="/Qualification_System/StudentNumberSearch" method="post">
			<table>
				<tr>
					<th>学籍番号</th>
					<td><input type="number" name="sid" list="studentid"
						placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
							id="studentid">
							<%
								for (StudentDTO anco : student) {
							%>
							<option value=<%=anco.getId()%>><%=anco.getName()%></option>
							<%
								}
							%>
						</datalist></td>
				</tr>
			</table>
			<input type="submit" value="検索">
		</form>
	</div>
	<div id="SW5" style="display: none;">
		<h1>受験日検索</h1>
		<form action="/Qualification_System/DateSearch" method="post">
			<table>
				<tr>
					<th>受験日</th>
					<td>年<select name="year" required>
							<option value="2018" selected>2018</option>
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
					</select></td>
				</tr>
				<tr>
					<th>資格名</th>
					<td><input type="text" name="cfname" list="cedential"
						placeholder="テキスト入力又はクリック" autocomplete="off" required></td>
				</tr>
			</table>
			<input type="submit" value="検索">
		</form>
	</div>
	<div id="SW6" style="display: none;">
		<h1>期間指定</h1>
		<form action="/Qualification_System/PeriodSearch" method="post">
			<table>
				<tr>
					<th>受験日</th>
					<td>年<select name="year" required>
							<option value="2018" selected>2018</option>
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
					</select></td>
					<td>年<select name="year2" required>
							<option value="2018" selected>2018</option>
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
					</select> 月<select name="month2" required>
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
					</select> 日<select name="day2" required>
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
					</select></td>
				</tr>
				<tr>
					<th>難易度</th>
					<td><select name="level" required>
							<option value="0" selected>----</option>
							<option value="S">S</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
					</select></td>
				</tr>
			</table>
			<input type="submit" value="検索">
		</form>
	</div>
	<script type="text/javascript">
		function Display(no) {
			switch (no) {
			case "no1":
				document.getElementById("SW1").style.display = "";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "none";
				document.getElementById("SW5").style.display = "none";
				document.getElementById("SW6").style.display = "none";
				break;
			case "no2":
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "none";
				document.getElementById("SW5").style.display = "none";
				document.getElementById("SW6").style.display = "none";
				break;
			case "no3":
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "";
				document.getElementById("SW4").style.display = "none";
				document.getElementById("SW5").style.display = "none";
				document.getElementById("SW6").style.display = "none";
				break;
			case "no4":
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "";
				document.getElementById("SW5").style.display = "none";
				document.getElementById("SW6").style.display = "none";
				break;
			case "no5":
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "none";
				document.getElementById("SW5").style.display = "";
				document.getElementById("SW6").style.display = "none";
				break;
			case "no6":
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "none";
				document.getElementById("SW5").style.display = "none";
				document.getElementById("SW6").style.display = "";
				break;
			default:
				document.getElementById("SW1").style.display = "";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "none";
				document.getElementById("SW5").style.display = "none";
				document.getElementById("SW6").style.display = "none";

			}
		}
	</script>
</body>
</html>