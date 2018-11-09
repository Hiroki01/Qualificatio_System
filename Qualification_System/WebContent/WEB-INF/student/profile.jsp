<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="masteDTO.DTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.CourseDTO"%>
<%@ page import="masteDTO.DepartmentDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録内容変更</title>
</head>
<body>
	<%
		DTO re = (DTO) request.getAttribute("pro");
	%>
	<form action="/Qualification_System/ProfileUpdate" method="post">
		<table>

			<tbody>
				<tr>
					<th>氏名</th>
					<td><input type="text" name="name" required
						value="<%=re.getName()%>"></td>
				</tr>
				<tr>
					<th>氏名（カタカナ）</th>
					<td><input type="text" name="namek" required
						value="<%=re.getNamek()%>"></td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td><input type="email" name="email" required
						value="<%=re.getEmail()%>"></td>
				</tr>
				<tr>
					<th>学科</th>
					<td><select class="parent" name="foo">
							<option value=<%=re.getDid()%> selected="selected"><%=re.getDname()%></option>
							<%
							@SuppressWarnings("unchecked")
								ArrayList<DepartmentDTO> result = (ArrayList<DepartmentDTO>) request.getAttribute("depart");
								for (DepartmentDTO anco : result) {
							%>
							<option value=<%=anco.getId()%>><%=anco.getName()%></option>
							<%
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>コース</th>
					<td><select class="children" name="bar">
							<option value=<%=re.getCoid() %> selected="selected"><%=re.getCname2() %></option>
							<%
							@SuppressWarnings("unchecked")
								ArrayList<CourseDTO> resu = (ArrayList<CourseDTO>) request.getAttribute("course");
								for (CourseDTO anco : resu) {
							%>
							<option value=<%=anco.getCourse_id()%> data-val=<%=anco.getDepartment_id() %>><%=anco.getName()%></option>
							<%
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>学年</th>
					<td><select name="school_year" required>
					<option value=<%=re.getYear() %> selected><%=re.getYear() %></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
					</select></td>
				</tr>
				<tr>
					<th>組</th>
					<td><select name="class" required>
							<%
								if (re.getClasies() == 1) {
							%>
							<option value="1" selected>1</option>
							<option value="2">2</option>
							<%
								} else {
							%>
							<option value="1">1</option>
							<option value="2" selected>2</option>
							<%
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>質問</th>
					<td><select name="question" required>
							<option value="1">故郷</option>
							<option value="2">趣味</option>
							<option value="3">小学校</option>
							<option value="4">中学校</option>
							<option value="5">高校</option>
							<option value="6">得意言語</option>
							<option value="7">ゲーム</option>
							<option value="8">アフラック</option>
							<option value="9">ドンカラス</option>
							<option value="10">(*'ω'*)</option>
					</select></td>
				</tr>
				<tr>
					<th>質問の答え</th>
					<td><input type=text name="answer" required></td>
				</tr>
				<tr>
					<th>従来パスワード</th>
					<td><input type="password" name="pass"></td>
				</tr>
				<tr>
					<th>新規パスワード</th>
					<td><input type="password" name="pass1"></td>
				</tr>
				<tr>
					<th>パスワード(確認用)</th>
					<td><input type="password" name="pass2"></td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="変更">
	</form>
	<script src="//code.jquery.com/jquery-1.12.1.min.js"></script>
	<script>
		var $children = $('.children');
		var original = $children.html();

		$('.parent').change(function() {
			var val1 = $(this).val();
			$children.html(original).find('option').each(function() {
				var val2 = $(this).data('val');
				if (val1 != val2) {
					$(this).not(':first-child').remove();
				}
			});
			if ($(this).val() == "") {
				$children.attr('disabled', 'disabled');
			} else {
				$children.removeAttr('disabled');
			}
		});
	</script>
</body>
</html>