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
		<title>Home</title>

		<script src="js/jquery-2.2.0.min.js"></script>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="signin-head">
				<img src="images/test/head.png" alt="">
			</div>

			<jsp:include page="../menu.jsp">
		        <jsp:param name="active" value="home"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						User Information
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
								Name:
							</td>

							<td>
								<c:out value="${userview.userName}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Department:
							</td>
							<td>
								<c:out value="${userview.userDept}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Group:
							</td>
							<td>
								<c:forEach var="groupName" items="${userview.groupName}">
									<span class="label label-primary">${groupName}</span>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								In date:
							</td>
							<td>
								<s:date name="userview.inDate" format="%{getText('format.date')}"/>
							</td>
						</tr>
						
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Quota:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="userview.quota"/>
								</s:text>
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Spending:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="userview.spending"/>
								</s:text>
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Remaining:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="userview.remaining" />
								</s:text>
							</td>
						</tr>

					</table>


				</div>
			</div>

		</div>

	</body>
</html>