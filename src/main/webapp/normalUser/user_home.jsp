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
			<div class="panel panel-primary doublecolumn">
				<div class="panel-heading">
					<h3 class="panel-title">
						Upcoming Activities
					</h3>
				</div>
				<div class="panel-body">
					<table class="table table-hover">
						<tr>
							<th>Activity name</th>
							<th>Date</th>
							<th>Details</th>
						</tr>
						
						<s:iterator var="act" value="upcomingActs">
							<tr>
								<td>
									${act.actName}
								</td>
								<td>
									<s:date name="actDate" format="%{getText('format.date')}"/>
								</td>
								<td>
									<a href='<c:url value="/doshowActDetailsLeaderviewAction.action" />?actId=${act.actId}'>details</a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
			<div class="panel panel-primary doublecolumn">
				<div class="panel-heading">
					<h3 class="panel-title">
						User Information
					</h3>
				</div>
				<div class="panel-body">

					<table class="table table-hover">

						<tr>
							<td class="col-md-4 col-md-offset-4">
								Name:
							</td>

							<td>
								<c:out value="${currentUser.userName}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Department:
							</td>
							<td>
								<c:out value="${currentUser.userDept}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								In date:
							</td>
							<td>
								<s:date name="currentUser.inDate" format="%{getText('format.date')}"/>
							</td>
						</tr>
						
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Quota:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="currentUser.quota"/>
								</s:text>
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Spending:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="currentUser.spending"/>
								</s:text>
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Remaining:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="remaining" />
								</s:text>
							</td>
						</tr>

					</table>


				</div>
			</div>

		</div>

	</body>
</html>