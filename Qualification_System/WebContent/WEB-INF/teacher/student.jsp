<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.CredentialDTO"%>
<%@ page import="masteDTO.StudentDTO"%>
<%@ page import="masteDTO.QualificationDTO"%>
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
	<h1>学籍番号登録</h1>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<QualificationDTO> qualification = (ArrayList<QualificationDTO>) request.getAttribute("qualification");
		@SuppressWarnings("unchecked")
		ArrayList<StudentDTO> student = (ArrayList<StudentDTO>) request.getAttribute("student");
		@SuppressWarnings("unchecked")
		ArrayList<CredentialDTO> credential = (ArrayList<CredentialDTO>) request.getAttribute("credential");
	%>
	<table>
		<thead>
			<tr>
				<th>学籍番号</th>
				<th>生徒氏名</th>
				<th>資格名</th>
				<th>受験日</th>
				<th>結果</th>
				<th>上位資格名</th>
				<th>上位資格名</th>
				<th>上位資格名</th>
				<th>上位資格名</th>
				<th>下位資格名</th>
				<th>下位資格名</th>
				<th>下位資格名</th>
				<th>下位資格名</th>
			</tr>
		</thead>
		<tbody>
			<%for(QualificationDTO qua : qualification){ %>
			<tr>
				<td><%=qua.getSid() %></td>
				<td><%=qua.getSname() %></td>
				<td><%=qua.getCname() %></td>
				<td><%=qua.getExamdate() %></td>
				<td><%=qua.getResult() %></td>
				<td><%=qua.getS1() %></td>
				<td><%=qua.getS2() %></td>
				<td><%=qua.getS3() %></td>
				<td><%=qua.getS4() %></td>
				<td><%=qua.getL1() %></td>
				<td><%=qua.getL2() %></td>
				<td><%=qua.getL3() %></td>
				<td><%=qua.getL4() %></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<form action="/Qualification_System/StudentResult" method="post">
		<table>
			<tr>
				<th>学籍番号</th>
				<td><input type="text" name="sid" list="sid"
					placeholder="テキスト入力又はクリック" autocomplete="off" required> <datalist
						id="sid">
						<%
							for (StudentDTO anco : student) {
						%>
						<option value=<%=anco.getId() %>><%=anco.getName() %></option>
						<%
							}
						%>
					</datalist></td>
				<td>～<input type="text" name="sid2" list="sid"
					placeholder="テキスト入力又はクリック" autocomplete="off" required></td>
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
		</table>
		<input type="submit" value="登録">
	</form>
</body>
</html>