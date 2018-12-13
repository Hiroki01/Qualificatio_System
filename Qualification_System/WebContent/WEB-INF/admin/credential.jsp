<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="masteDTO.CredentialDTO"%>
<%@ page import="masteDTO.ClassificationDTO"%>
<%@ page import="masteDTO.DesrcpitionDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Qualification_System/css/style.css"
	type="text/css" media="print, projection, screen" />
<script type="text/javascript"
	src="/Qualification_System/js/openclose.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/ddmenu_min.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/jquery-latest.js"></script>
<script type="text/javascript"
	src="/Qualification_System/js/jquery.tablesorter.min.js"></script>
<script src="/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.6.4.js"></script>
<title>資格情報</title>
</head>
<body>
	<div id="container">
		<header>
			<h1 id="logo">
				<a href="/Qualification_System/Tmenu">資格管理システム</a>
			</h1>
			<nav id="menubar">
				<ul>
					<li><a href="#">マスタテーブル</a>
						<ul class="ddmenu" id="a">
							<li><a href="/Qualification_System/Credential_information">資格情報</a></li>
							<li><a href="/Qualification_System/Classification">主催情報</a></li>
							<li><a href="/Qualification_System/Subject">主要学科</a></li>
							<li><a href="/Qualification_System/Department">学科</a></li>
							<li><a href="/Qualification_System/Course">コース</a></li>
							<li><a href="/Qualification_System/Account">アカウント情報管理</a></li>
						</ul></li>
					<li><a href="/Qualification_System/Search">検索</a></li>
					<li><a href="#">範囲登録</a>
						<ul class="ddmenu">
							<li><a href="/Qualification_System/StudentNumber">学籍番号</a></li>
							<li><a href="/Qualification_System/ClassRegistration">クラス</a></li>
						</ul></li>
					<li><a href="/Qualification_System/Confirmation">生徒確認</a></li>
					<li><a href="/Qualification_System/DeleteEvent">削除</a></li>
					<li><a href="/Qualification_System/TProfile">登録情報変更</a></li>
					<li><a href="/Qualification_System/LogOut">ログアウト</a></li>
				</ul>
			</nav>
		</header>
		<nav id="menubar-s">

			<ul>
				<li><a href="/Qualification_System/Credential_information">資格情報</a></li>
				<li><a href="/Qualification_System/Classification">主催者情報</a></li>
				<li><a href="/Qualification_System/Subject">主要学科</a></li>
				<li><a href="/Qualification_System/Department">学科</a></li>
				<li><a href="/Qualification_System/Course">コース</a></li>
				<li><a href="/Qualification_System/Account">アカウント情報管理</a></li>

				<li><a href="/Qualification_System/Search">検索</a></li>
				<li><a href="/Qualification_System/StudentNumber">学籍番号登録</a></li>
				<li><a href="/Qualification_System/ClassRegistration">クラス登録</a></li>
				<li><a href="/Qualification_System/Confirmation">生徒確認</a></li>
				<li><a href="/Qualification_System/DeleteEvent">削除</a></li>
				<li><a href="/Qualification_System/TProfile">登録情報変更</a></li>
				<li><a href="/Qualification_System/LogOut">ログアウト</a></li>
			</ul>
		</nav>
		<div id="contents">
			<h1 id="title">資格情報</h1>
			<hr>
			<div id="main">
				<%
					@SuppressWarnings("unchecked")
					ArrayList<CredentialDTO> result = (ArrayList<CredentialDTO>) request.getAttribute("QuaName");
					@SuppressWarnings("unchecked")
					ArrayList<ClassificationDTO> reu = (ArrayList<ClassificationDTO>) request.getAttribute("class");
				%>
				<div class="change_button">
					<input type="submit" value="一覧" onclick="Display('no0')"
						class="change_b"> <input type="submit" value="登録"
						onclick="Display('no1')" class="change_b"> <input
						type="submit" value="更新" onclick="Display('no2')" class="change_b">
					<input type="submit" value="削除" onclick="Display('no3')"
						class="change_b"> <input type="submit" value="詳細"
						onclick="Display('no4')" class="change_b">
				</div>
				<div id="SW0" style="margin: 5px 5% 5px 5%;">
					<div id="wrap" style="height: 40%;">
						<script>
							jQuery(function() {
								jQuery('#all1')
										.tablesorter({
											headers : {
												0 : {
													sorter : false
												},
												2 : {
													sorter : false
												}
											}
										});
							});
						</script>

						<table border="1" id="all1" class="tablesorter">
							<thead>
								<tr>
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
									<td><%=anko.getName()%></td>
									<td><%=anko.getLevel()%></td>
									<td><%=anko.getCname()%></td>
									<%
										if (anko.getSname1() != null) {
									%>
									<td><%=anko.getSname1()%></td>
									<%
										} else {
									%>
									<td>無し</td>
									<%
										}
									%>
									<%
										if (anko.getSname2() != null) {
									%>
									<td><%=anko.getSname2()%></td>
									<%
										} else {
									%>
									<td>無し</td>
									<%
										}
									%>
									<%
										if (anko.getSname3() != null) {
									%>
									<td><%=anko.getSname3()%></td>
									<%
										} else {
									%>
									<td>無し</td>
									<%
										}
									%>
									<%
										if (anko.getSname4() != null) {
									%>
									<td><%=anko.getSname4()%></td>
									<%
										} else {
									%>
									<td>無し</td>
									<%
										}
									%>
									<%
										if (anko.getLname1() != null) {
									%>
									<td><%=anko.getLname1()%></td>
									<%
										} else {
									%>
									<td>無し</td>
									<%
										}
									%>
									<%
										if (anko.getLname2() != null) {
									%>
									<td><%=anko.getLname2()%></td>
									<%
										} else {
									%>
									<td>無し</td>
									<%
										}
									%>
									<%
										if (anko.getLname3() != null) {
									%>
									<td><%=anko.getLname3()%></td>
									<%
										} else {
									%>
									<td>無し</td>
									<%
										}
									%>
									<%
										if (anko.getLname4() != null) {
									%>
									<td><%=anko.getLname4()%></td>
									<%
										} else {
									%>
									<td>無し</td>
									<%
										}
									%>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
				<div id="SW1" style="display: none;">
					<form action="/Qualification_System/CredentialRegistration"
						method="post">
						<div id="wrap" style="width: 46%;">
							<table border="1" id="all1" class="tablesorter">
								<thead>
									<tr>
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
										<td><%=anko.getName()%></td>
										<td><%=anko.getLevel()%></td>
										<td><%=anko.getCname()%></td>
										<%
											if (anko.getSname1() != null) {
										%>
										<td><%=anko.getSname1()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname2() != null) {
										%>
										<td><%=anko.getSname2()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname3() != null) {
										%>
										<td><%=anko.getSname3()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname4() != null) {
										%>
										<td><%=anko.getSname4()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname1() != null) {
										%>
										<td><%=anko.getLname1()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname2() != null) {
										%>
										<td><%=anko.getLname2()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname3() != null) {
										%>
										<td><%=anko.getLname3()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname4() != null) {
										%>
										<td><%=anko.getLname4()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<div class="st_form">
							<%
								HttpSession sessions = request.getSession(true);
								/* 認証失敗から呼び出されたのかどうか */
								Object status = session.getAttribute("status");
								if (status != null) {
									if (status.equals("完了1")) {
							%>
							<p>登録情報を更新しました。</p>
							<%
								session.setAttribute("status", null);
									} else if (status.equals("nai1")) {
							%>
							<p>必要事項の入力にミスがあります</p>
							<%
								session.setAttribute("status", null);
									} else if (status.equals("Exception1")) {
							%>
							<p>エラーが発生しました。</p>
							<%
								session.setAttribute("status", null);
									}
								}
							%>
							<table>
								<tr>
									<td style="text-align: right;">資格名</td>
									<td style="text-align: left;"><input type="text"
										name="name" required></td>
								</tr>
								<tr>
									<td style="text-align: right;">主催</td>
									<td style="text-align: left;"><select name="class"
										required style="width: 110%;">
											<option value="" selected>主催を選択して下さい</option>
											<%
												for (ClassificationDTO anco : reu) {
											%>
											<option value=<%=anco.getCfid()%>><%=anco.getCfname()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">資格難易度</td>
									<td style="text-align: left;"><select name="level"
										required style="width: 110%;">
											<option value="--">------</option>
											<option value="A">A</option>
											<option value="B">B</option>
											<option value="C">C</option>
											<option value="D">D</option>
											<option value="E">E</option>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">上位資格名</td>
									<td style="text-align: left;"><select name="s1"
										style="width: 110%;">
											<option value="" selected>資格名を選択</option>
											<%
												for (CredentialDTO anko : result) {
											%>
											<option value=<%=anko.getId()%>><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">上位資格名</td>
									<td style="text-align: left;"><select name="s2"
										style="width: 110%;">
											<option value="" selected>資格名を選択</option>
											<%
												for (CredentialDTO anko : result) {
											%>
											<option value=<%=anko.getId()%>><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">上位資格名</td>
									<td style="text-align: left;"><select name="s3"
										style="width: 110%;">
											<option value="" selected>資格名を選択</option>
											<%
												for (CredentialDTO anko : result) {
											%>
											<option value=<%=anko.getId()%>><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">上位資格名</td>
									<td style="text-align: left;"><select name="s4"
										style="width: 110%;">
											<option value="" selected>資格名を選択</option>
											<%
												for (CredentialDTO anko : result) {
											%>
											<option value=<%=anko.getId()%>><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">下位資格名</td>
									<td style="text-align: left;"><select name="l1"
										style="width: 110%;">
											<option value="" selected>資格名を選択</option>
											<%
												for (CredentialDTO anko : result) {
											%>
											<option value=<%=anko.getId()%>><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">下位資格名</td>
									<td style="text-align: left;"><select name="l2"
										style="width: 110%;">
											<option value="" selected>資格名を選択</option>
											<%
												for (CredentialDTO anko : result) {
											%>
											<option value=<%=anko.getId()%>><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">下位資格名</td>
									<td style="text-align: left;"><select name="l3"
										style="width: 110%;">
											<option value="" selected>資格名を選択</option>
											<%
												for (CredentialDTO anko : result) {
											%>
											<option value=<%=anko.getId()%>><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right;">下位資格名</td>
									<td style="text-align: left;"><select name="l4"
										style="width: 110%;">
											<option value="" selected>資格名を選択</option>
											<%
												for (CredentialDTO anko : result) {
											%>
											<option value=<%=anko.getId()%>><%=anko.getName()%></option>
											<%
												}
											%>
									</select></td>
								</tr>
							</table>
							<div id="buttons">
								<input type="submit" value="登録" class="execute">
							</div>
						</div>
					</form>
				</div>
				<div id="SW2" style="display: none;">
					<form action="/Qualification_System/CredentialUpdate" method="post">
						<div id="wrap"
							style="margin-left: 5%; margin-right: 5%; height: 200px; width: 90%;">
							<table border="1" style="height: 100; width: 100%;" id="all2"
								class="tablesorter">
								<thead>
									<tr>
										<th></th>
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
										<td><input type="radio" name="check"
											value="<%=anko.getId()%>" required /></td>
										<td><%=anko.getName()%></td>
										<td><%=anko.getLevel()%></td>
										<td><%=anko.getCname()%></td>
										<%
											if (anko.getSname1() != null) {
										%>
										<td><%=anko.getSname1()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname2() != null) {
										%>
										<td><%=anko.getSname2()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname3() != null) {
										%>
										<td><%=anko.getSname3()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname4() != null) {
										%>
										<td><%=anko.getSname4()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname1() != null) {
										%>
										<td><%=anko.getLname1()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname2() != null) {
										%>
										<td><%=anko.getLname2()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname3() != null) {
										%>
										<td><%=anko.getLname3()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname4() != null) {
										%>
										<td><%=anko.getLname4()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<div class="a_ssr_form">
							<%
								if (status != null) {
									if (status.equals("完了2")) {
							%>
							<p>登録情報を更新しました。</p>
							<%
								session.setAttribute("status", null);
									} else if (status.equals("nai2")) {
							%>
							<p>必要事項の入力にミスがあります</p>
							<%
								session.setAttribute("status", null);
									} else if (status.equals("Exception2")) {
							%>
							<p>エラーが発生しました。</p>
							<%
								session.setAttribute("status", null);
									}
								}
							%>
							<table style="font-size: 100%;">
								<tr>
									<td><table style="display: inline-block; margin: 0;">
											<tr>
												<td style="width: 60%;">資格名</td>
												<td><input type="text" name="name" required
													style="width: 110%; margin-left: -75%"></td>

											</tr>
											<tr>
												<td style="width: 60%;">資格区分</td>
												<td><select name="class" required
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格区分(主催)を選択して下さい</option>
														<%
															for (ClassificationDTO anco : reu) {
														%>
														<option value=<%=anco.getCfid()%>><%=anco.getCfname()%></option>
														<%
															}
														%>
												</select></td>

											</tr>
											<tr>
												<td style="width: 60%;">資格難易度</td>
												<td><select name="level" required
													style="width: 110%; margin-left: -75%">
														<option value="--">------</option>
														<option value="A">A</option>
														<option value="B">B</option>
														<option value="C">C</option>
														<option value="D">D</option>
														<option value="E">E</option>
												</select></td>
											</tr>
										</table></td>
									<td><table
											style="display: inline-block; padding: 0; margin: 0;">
											<tr>
												<td style="width: 60%;">上位資格名</td>
												<td><select name="s1"
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格名を選択</option>
														<%
															for (CredentialDTO anko : result) {
														%>
														<option value=<%=anko.getId()%>><%=anko.getName()%></option>
														<%
															}
														%>
														<option value=0>無</option>
												</select></td>
											</tr>
											<tr>
												<td style="width: 60%;">上位資格名</td>
												<td><select name="s2"
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格名を選択</option>
														<%
															for (CredentialDTO anko : result) {
														%>
														<option value=<%=anko.getId()%>><%=anko.getName()%></option>
														<%
															}
														%>
														<option value=0>無</option>
												</select></td>
											</tr>
											<tr>

											</tr>
											<tr>

												<td style="width: 60%;">上位資格名</td>
												<td><select name="s3"
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格名を選択</option>
														<%
															for (CredentialDTO anko : result) {
														%>
														<option value=<%=anko.getId()%>><%=anko.getName()%></option>
														<%
															}
														%>
														<option value=0>無</option>
												</select></td>
											</tr>
											<tr>
												<td style="width: 60%;">上位資格名</td>
												<td><select name="s4"
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格名を選択</option>
														<%
															for (CredentialDTO anko : result) {
														%>
														<option value=<%=anko.getId()%>><%=anko.getName()%></option>
														<%
															}
														%>
														<option value=0>無</option>
												</select></td>
											</tr>
										</table></td>
									<td><table style="display: inline-block; margin: 0;">
											<tr>

												<td style="width: 60%;">下位資格名</td>
												<td><select name="l1"
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格名を選択</option>
														<%
															for (CredentialDTO anko : result) {
														%>
														<option value=<%=anko.getId()%>><%=anko.getName()%></option>
														<%
															}
														%>
														<option value=0>無</option>
												</select></td>
											</tr>
											<tr>
												<td style="width: 60%;">下位資格名</td>
												<td><select name="l2"
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格名を選択</option>
														<%
															for (CredentialDTO anko : result) {
														%>
														<option value=<%=anko.getId()%>><%=anko.getName()%></option>
														<%
															}
														%>
														<option value=0>無</option>
												</select></td>
											</tr>
											<tr>
												<td style="width: 60%;">下位資格名</td>
												<td><select name="l3"
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格名を選択</option>
														<%
															for (CredentialDTO anko : result) {
														%>
														<option value=<%=anko.getId()%>><%=anko.getName()%></option>
														<%
															}
														%>
														<option value=0>無</option>
												</select></td>
											</tr>
											<tr>
												<td style="width: 60%;">下位資格名</td>
												<td><select name="l4"
													style="width: 110%; margin-left: -75%">
														<option value="" selected>資格名を選択</option>
														<%
															for (CredentialDTO anko : result) {
														%>
														<option value=<%=anko.getId()%>><%=anko.getName()%></option>
														<%
															}
														%>
														<option value=0>無</option>
												</select></td>
											</tr>
										</table></td>
								</tr>
							</table>
							<div id="buttons">
								<input type="submit" value="変更" class="execute">
							</div>
						</div>
					</form>
				</div>
				<div id="SW3" style="display: none;">
					<form action="/Qualification_System/CredentialDelete" method="post">
						<div id="wrap"
							style="margin-left: 5%; margin-right: 5%; height: 200px; width: 90%;">
							<table border="1" style="height: 100; width: 100%;" id="delete" class="tablesorter">
								<thead>
									<tr>
										<th></th>
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
										<td><input type="checkbox" name="check"
											value="<%=anko.getId()%>" /></td>
										<td><%=anko.getName()%></td>
										<td><%=anko.getLevel()%></td>
										<td><%=anko.getCname()%></td>
										<%
											if (anko.getSname1() != null) {
										%>
										<td><%=anko.getSname1()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname2() != null) {
										%>
										<td><%=anko.getSname2()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname3() != null) {
										%>
										<td><%=anko.getSname3()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getSname4() != null) {
										%>
										<td><%=anko.getSname4()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname1() != null) {
										%>
										<td><%=anko.getLname1()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname2() != null) {
										%>
										<td><%=anko.getLname2()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname3() != null) {
										%>
										<td><%=anko.getLname3()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
										<%
											if (anko.getLname4() != null) {
										%>
										<td><%=anko.getLname4()%></td>
										<%
											} else {
										%>
										<td>無し</td>
										<%
											}
										%>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<div class="a_ssr_form">
							<%
								if (status != null) {
									if (status.equals("完了3")) {
							%>
							<p>削除完了しました。</p>
							<%
								session.setAttribute("status", null);
									} else if (status.equals("nai3")) {
							%>
							<p>必要事項の入力にミスがあります。</p>
							<%
								session.setAttribute("status", null);
									} else if (status.equals("Exception3")) {
							%>
							<p>エラーが発生しました。</p>
							<%
								session.setAttribute("status", null);
									}
								}
							%>
							<div id="buttons">
								<input type="submit" value="削除" class="execute">
							</div>
						</div>
					</form>
				</div>
				<div id="SW4" style="display: none;">
					<div id="main">
						<%
							@SuppressWarnings("unchecked")
							ArrayList<DesrcpitionDTO> results = (ArrayList<DesrcpitionDTO>) request.getAttribute("des");
						%>
						<form action="/Qualification_System/DesrcpitionUpdate"
							method="post">
							<div id="wrap" style="width: 45%;">
								<table border="1">
									<thead>
										<tr>
											<th></th>
											<th>資格名</th>
											<th>主催</th>
											<th>資格開催ページ</th>
											<th>合格率</th>
											<th>合格基準</th>
										</tr>
									</thead>
									<tbody>
										<%
											for (DesrcpitionDTO anko : results) {
										%>
										<tr>
											<td><input type="radio" name="check"
												value="<%=anko.getCid()%>" required="required"></td>
											<td><%=anko.getCname()%></td>
											<td><%=anko.getClassname()%></td>
											<%
												String text1 = anko.getUrl();
													if (text1 == null) {
											%>
											<td>未登録</td>
											<%
												} else {
											%>
											<td><a href="<%=text1%>" target="_blank"><%=text1%></a></td>
											<%
												}
													String text2 = anko.getPassrate();
													if (text2 == null) {
											%>
											<td>未登録</td>
											<%
												} else {
											%>
											<td><%=text2%></td>
											<%
												}
											%>
											<%
												String text3 = anko.getPassing();
													if (text3 == null) {
											%>
											<td>未登録</td>
											<%
												} else {
											%>
											<td><%=text3%></td>
											<%
												}
											%>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
							<div class="st_form">
								<%
									if (status != null) {
										if (status.equals("完了")) {
								%>
								<p>登録完了しました。</p>
								<%
									session.removeAttribute("status");
										} else if (status.equals("nai")) {
								%>
								<p>指定されたものはありません。</p>
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
								<table border="1" style="white-space: nowrap;">
									<tr>
										<th style="text-align: right;">リンク</th>
										<td style="text-align: left;"><input type="url"
											name="link" required>
									</tr>
									<tr>
										<th style="text-align: right;">合格率</th>
										<td style="text-align: left;"><input type="text"
											name="pass1" required>
									</tr>
									<tr>
										<th style="text-align: right;">合格率</th>
										<td style="text-align: left;"><input type="text"
											name="pass2">
									</tr>
									<tr>
										<th style="text-align: right;">合格基準</th>
										<td style="text-align: left;"><input type="text"
											name="text1" placeholder="総数○○○点以上OR午前○○点以上" required>
									</tr>
									<tr>
										<th style="text-align: right;">合格基準</th>
										<td style="text-align: left;"><input type="text"
											name="text2" placeholder="各部門○○点以上OR午後○○点以上">
									</tr>
								</table>
								<div id="buttons" style="margin-top: 20px;">
									<input type="submit" value="変更" class="execute">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		if (OCwindowWidth() <= 900) {
			open_close("newinfo_hdr", "newinfo");
		}
	</script>

	<!--メニューの３本バー-->
	<div id="menubar_hdr" class="close">
		<span></span><span></span><span></span>
	</div>
	<!--メニューの開閉処理条件設定　900px以下-->
	<script>
		if (OCwindowWidth() <= 900) {
			open_close("menubar_hdr", "menubar-s");
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#all").tablesorter();
		});
	</script>
	<script type="text/javascript">
		function Display(no) {
			if (no == "no0") {
				document.getElementById("SW0").style.display = "";
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "none";
			} else if (no == "no1") {
				document.getElementById("SW0").style.display = "none";
				document.getElementById("SW1").style.display = "";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "none";
			} else if (no == "no2") {
				document.getElementById("SW0").style.display = "none";
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "none";
			} else if (no == "no3") {
				document.getElementById("SW0").style.display = "none";
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "";
				document.getElementById("SW4").style.display = "none";
			} else if (no == "no4") {
				document.getElementById("SW0").style.display = "none";
				document.getElementById("SW1").style.display = "none";
				document.getElementById("SW2").style.display = "none";
				document.getElementById("SW3").style.display = "none";
				document.getElementById("SW4").style.display = "";
			}
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#all1").tablesorter();
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#all2").tablesorter();
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#all3").tablesorter();
		});
	</script>
		<script type="text/javascript">
		$(document).ready(function() {
			$("#delete").tablesorter();
		});
	</script>
	<script type="text/javascript">
		$(window)
				.load(
						function() {

							$(document)
									.ready(
											function() {
												$('input[type=checkbox]')
														.click(
																function() {
																	var t = $(this).prop;
																	if (t('checked'))
																		t(
																				'checked',
																				'');
																	else
																		t(
																				'checked',
																				'checked');
																});

												$('table tr')
														.click(
																function() {
																	var c = $(
																			this)
																			.children(
																					'td')
																			.children(
																					'input[type=checkbox]');
																	if (c
																			.prop('checked'))
																		c
																				.prop(
																						'checked',
																						'');
																	else
																		c
																				.prop(
																						'checked',
																						'checked');
																});
											});

						});
	</script>
	<script type="text/javascript">
		$(window)
				.load(
						function() {

							$(document)
									.ready(
											function() {
												$('input[type=radio]')
														.click(
																function() {
																	var t = $(this).prop;
																	if (t('checked'))
																		t(
																				'checked',
																				'');
																	else
																		t(
																				'checked',
																				'checked');
																});

												$('table tr')
														.click(
																function() {
																	var c = $(
																			this)
																			.children(
																					'td')
																			.children(
																					'input[type=radio]');
																	if (c
																			.prop('checked'))
																		c
																				.prop(
																						'checked',
																						'');
																	else
																		c
																				.prop(
																						'checked',
																						'checked');
																});
											});

						});
	</script>
</body>
</html>