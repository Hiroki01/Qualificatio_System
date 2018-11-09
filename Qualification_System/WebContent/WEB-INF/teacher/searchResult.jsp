<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.QualificationDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学科情報</title>
</head>
<body>
<h1>検索結果</h1>
	<%
	@SuppressWarnings("unchecked")
		ArrayList<QualificationDTO> result = (ArrayList<QualificationDTO>) request.getAttribute("result");
		if (!result.isEmpty()) {
	%>
	<table>
		<tr>
			<th>学籍番号</th>
			<th>氏名</th>
			<th>資格名</th>
			<th>受験日</th>
			<th>受験結果</th>
			<th>上位資格名</th>
			<th>上位資格名</th>
			<th>上位資格名</th>
			<th>上位資格名</th>
		</tr>
		<%
			for (QualificationDTO anko : result) {
		%>
		<tr>
			<td><%=anko.getSid()%></td>
			<td><%=anko.getSname()%></td>
			<td><%=anko.getCname()%></td>
			<td><%=anko.getExamdate()%></td>
			<td><%=anko.getResult()%></td>
			<%if(anko.getS1() != null || Integer.parseInt(anko.getS1()) != 0){ %>
			<td><%=anko.getS1() %></td>
			<%}else { %>
			<td>無</td>
			<%} %>
			<%if(anko.getS2() != null || Integer.parseInt(anko.getS2()) != 0){ %>
			<td><%=anko.getS2() %></td>
			<%}else { %>
			<td>無</td>
			<%} %>
			<%if(anko.getS3() != null || Integer.parseInt(anko.getS3()) != 0){ %>
			<td><%=anko.getS3() %></td>
			<%}else { %>
			<td>無</td>
			<%} %>
			<%if(anko.getS4() != null || Integer.parseInt(anko.getS4()) != 0){ %>
			<td><%=anko.getS4() %></td>
			<%}else { %>
			<td>無</td>
			<%} %>
		</tr>
		<%
			}
		%>
	</table>
	<%QualificationDTO count = (QualificationDTO)request.getAttribute("count"); %>
	<table>
		<tr>
			<th>合格者</th>
			<td><%=count.getCount1() %>名</td>
			<th>不合格者</th>
			<td><%=count.getCount2() %>名</td>
			<th>未受験者</th>
			<td><%=count.getCount3() %>名</td>
		</tr>

	</table>
	<%
		}
	%>
</body>
</html>