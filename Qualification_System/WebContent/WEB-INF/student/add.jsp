<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.SubjectDTO"%>
<%@ page import="masteDTO.DepartmentDTO"%>
<%@ page import="masteDTO.CourseDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Qualification_System/css/style.css"
	type="text/css">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var department = $(".department option").clone();
						var course = $(".course option").clone();
						$(".subject")
								.change(
										function() {
											var subject = $(".subject").val();
											$(".department").removeAttr(
													"disabled");
											$(".department option").remove();
											$(department).appendTo(
													".department");
											$(
													".department option[class != "
															+ subject + "]")
													.remove();
											$(".department")
													.prepend(
															'<option value="0" selected="selected">▼選択</option>');
											$(".course").attr("disabled",
													"disabled");
											$(".course option").remove();
											$(".course")
													.prepend(
															'<option value="0" selected="selected">▼選択</option>');
										});
						$(".department")
								.change(
										function() {
											var departmentVal = $(".department")
													.val();
											$(".course").removeAttr("disabled");
											$(".course option").remove();
											$(course).appendTo(".course");
											$(
													".course option[class != "
															+ departmentVal
															+ "]").remove();
											$(".course")
													.prepend(
															'<option value="0" selected="selected">▼選択</option>');
										});
					});
</script>
<title>新規登録画面</title>
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		ArrayList<SubjectDTO> subject = (ArrayList<SubjectDTO>) request.getAttribute("subject");
		@SuppressWarnings("unchecked")
		ArrayList<DepartmentDTO> department = (ArrayList<DepartmentDTO>) request.getAttribute("department");
		@SuppressWarnings("unchecked")
		ArrayList<CourseDTO> course = (ArrayList<CourseDTO>) request.getAttribute("course");
	%>
	<h1 id="title">新規登録</h1>
	<hr>
	<div class="form_area">
		<%
			Object status = session.getAttribute("status");
			if (status != null) {
				if (status.equals("完了")) {
		%>
		<p>登録完了しました。</p>
		<%
			session.removeAttribute("status");
				} else if (status.equals("nai")) {
		%>
		<p>入力ミスがあります。</p>
		<%
			session.removeAttribute("status");
				} else if (status.equals("Exception")) {
		%>
		<p>エラーが発生しました。</p>
		<%
			session.removeAttribute("status");
				}
			}
		%>

		<form action="/Qualification_System/AddResult" method="post">
			<dl class="sinki_form">
				<dt>学籍番号</dt>
				<dd>
					<input name="id" size="35" placeholder="0000000" type="number" />
				</dd>
				<dt>名前</dt>
				<dd>
					姓<input type="text" name="nameA" placeholder="盛ジョビ" required><span
						style="white-space: nowrap;">名<input type="text"
						name="nameB" placeholder="太郎" required></span>
				</dd>
				<dt>フリガナ</dt>
				<dd>
					姓<input type="text" name="namekA" placeholder="モリジョビ" required><span
						style="white-space: nowrap;">名<input type="text"
						name="namekB" placeholder="タロウ" required></span>
				</dd>
				<dt>メールアドレス</dt>
				<dd>
					<input type="email" name="email" placeholder="xxxx@xxxx.xxx"
						required>
				</dd>
				<dt>主要学科</dt>
				<dd>
					<select name="subject" class="subject" required>
						<option value="0" selected="selected">▼選択</option>
						<%
							for (SubjectDTO anko : subject) {
						%>
						<option value=<%=anko.getName()%>>
							<%=anko.getName()%>
						</option>
						<%
							}
						%>
					</select>
				</dd>
				<dt>学科</dt>
				<dd>
					<select name="department" class="department" disabled="disabled"
						required>
						<option value="0">▼選択</option>
						<%
							for (DepartmentDTO anco : department) {
						%>
						<option value=<%=anco.getName()%> class=<%=anco.getSname()%>>
							<%=anco.getName()%>
						</option>
						<%
							}
						%>
					</select>
				</dd>
				<dt>コース</dt>
				<dd>
					<select name="course" class="course" disabled="disabled" required>
						<option value="0">▼選択</option>
						<%
							for (CourseDTO anpan : course) {
						%>
						<option value=<%=anpan.getName()%> class=<%=anpan.getDname()%>>
							<%=anpan.getName()%>
						</option>
						<%
							}
						%>
					</select>
				</dd>
				<dt>学年・組</dt>
				<dd>
					<select name="school_year" required>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select>年<select name="set_in" required>
						<option value="1">1</option>
						<option value="2">2</option>
					</select>組
				</dd>
				<dt>秘密の質問</dt>
				<dd>
					<select name="question" required>
						<option value="1">出身地は</option>
						<option value="2">趣味は</option>
						<option value="3">出身小学校は</option>
						<option value="4">出身中学校は</option>
						<option value="5">出身高等学校は</option>
						<option value="6">得意言語</option>
						<option value="7">尊敬している人</option>
						<option value="8">好きな食べ物</option>
						<option value="9">親友の名前</option>
						<option value="10">得意教科</option>
						<option value="11">好きな芸能人</option>
						<option value="12">嫌いな人</option>
						<option value="13">好きな月</option>
						<option value="14">好きなゲーム</option>
						<option value="15">嫁</option>
					</select>
				</dd>
				<dt>質問の答え</dt>
				<dd>
					<input type="text" name="answer" required>
				</dd>
				<dt>パスワード</dt>
				<dd>
					<input type="password" name="pass" required>
				</dd>
			</dl>
		</form>
		<form>
			<div id="f_buttons" style="margin-top: 7px; margin-bottom: 15px;">
				<a href="/Qualification_System/TopServlet"><input type="button"
					class="return" value="戻る"></a> <input type="submit"
					class="execute" value="登録" style="margin-left: 20%;">
			</div>
		</form>
	</div>
</body>
</html>