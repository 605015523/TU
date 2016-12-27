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
		<title>Manage users</title>

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
		        <jsp:param name="active" value="admin"/>
		    </jsp:include>
			
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Manage users
					</h3>
				</div>
				
				<div class="panel-body">
					<p class="navbar-text navbar-right">
						<a href="<c:url value="/addUserFormManageUsersAction.action" />" class="navbar-link">Add user</a>
					</p>
				
					<table class="table table-hover">
						<tr>
							<th>User name</th>
							<th>Role</th>
							<th>Department</th>
							<th>Action</th>
						</tr>
					
						<s:iterator var="user" value="users">
							<tr>
								<td>${user.userName}</td>
								<td>${user.userRole}</td>
								<td>${user.userDept}</td>
								<td>
									<a href='<c:url value="/editUserFormManageUsersAction.action" />?userId=${user.userId}'>edit</a>
								</td>
							</tr>
						</s:iterator>				
					</table>
				</div>
			</div>
		</div>
	</body>
</html>