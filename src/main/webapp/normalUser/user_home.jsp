<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<title>Home</title>

		<script src="normalUser/js/jquery-2.2.0.min.js"></script>
		<link href="normalUser/css/bootstrap.min.css" rel="stylesheet">
		<link href="normalUser/css/signin.css" rel="stylesheet">
		<script src="normalUser/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="signin-head">
				<img src="normalUser/images/test/head.png" alt="">
			</div>

			<jsp:include page="menu.jsp">
		        <jsp:param name="active" value="home"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						UserInfo
					</h3>
					<p class="navbar-text navbar-right">
						<a href="normalUser/change_pwd.jsp" class="navbar-link">Change
							Password</a>
					</p>
				</div>
				<div class="panel-body">

					<table class="table table-hover">

						<tr>
							<td class="col-md-4 col-md-offset-4">
								NAME:
							</td>

							<td>
								<c:out value="${userview.userName}" />
							</td>

						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								DEPARTMENT:
							</td>
							<td>
								<c:out value="${userview.userDept}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								GROUP:
							</td>
							<td>

								<c:forEach var="groupName" items="${userview.groupName}">
									<span class="label label-primary">${groupName}</span>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								IN_DATE:
							</td>
							<td>
								<c:out value="${userview.in_date}" />
							</td>
						</tr>
						
						<tr>
							<td class="col-md-4 col-md-offset-4">
								QUOTA:
							</td>
							<td>
								<c:out value="${userview.quota}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								SPENDING
							</td>
							<td>
								<c:out value="${userview.spending}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								REMAINING:
							</td>
							<td>
								<c:out value="${userview.remaining}" />
							</td>
						</tr>

					</table>


				</div>
			</div>

		</div>

	</body>
</html>