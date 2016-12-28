<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="Manage users"/>
	    </jsp:include>
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
								<td>${roles[user.userRole].name}</td>
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