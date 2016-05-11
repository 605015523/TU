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
		<title>View messages</title>

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
			<ul class="nav nav-tabs">
				<li role="presentation">
					<a href="normalUser/user_home.jsp">Home</a>
				</li>
				<c:if test="${userRole==1}">
					<li role="presentation" class="dropdown">
						<a id="dLabel" data-target="#" href="#"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"> Act <span class="caret"></span> </a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li>
								<a href="groupLeader/add_act.jsp">Add Act</a>
							</li>
							<li>
								<a href="groupLeader/view_act.jsp">View Act</a>
							</li>
						</ul>
					</li>
				</c:if>
								<c:if test="${userRole==2}">
					<li role="presentation">
						<a href="doGetAllCheckActAccountingviewAction.action">Check&Validate<c:if test="${newCheck!=0}">
								<span class="badge">${newCheck}</span>
							</c:if>
						</a>
					</li>
					<li role="presentation" class="dropdown">
						<a id="dLabel" data-target="#" href="#"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Act Details <span class="caret"></span> </a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li class="dropdown-header">total cost</li>
						<c:forEach var="year" items="${years}">
							<li><a href='<c:url value="/doshowAllActsByYearAccountingviewAction.action" />?year=${year}'>${year}</a></li>
						</c:forEach>
						   <li class="dropdown-header">All in group</li>
						<c:forEach var="groupname" items="${groupsName}">
							<li><a href='<c:url value="/doshowAllActsByGroupAccountingviewAction.action" />?groupname=${groupname}'>${groupname}</a></li>
						</c:forEach>
							
						</ul>
					</li>
					
				</c:if>
				<li role="presentation" class=" active">
					<a href="doshowMessagesUserviewAction.action">Message <c:if
							test="${newMsg!=0}">
							<span class="badge"> ${newMsg}</span>
						</c:if> </a>
				</li>

				<li role="presentation" class="dropdown">
					<a id="dLabel" data-target="#" href="#"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> activities <span class="caret"></span>
					</a>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
						<c:forEach var="year" items="${years}">
							<li>
								<a
									href='<c:url value="/doshowActsUserviewAction.action" />?year=${year}'>${year}</a>
							</li>
						</c:forEach>
					</ul>
				</li>
				<li role="presentation" class="navbar-right">
					<a role="button" href="dologoutUserloginManageAction.action">Exit
						Login</a>
				</li>
			</ul>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Messages
					</h3>

				</div>


				<div class="panel-body">

					<table class="table table-hover row ">

						<tr>
							<td class="col-md-2 col-xs-3 text-center">
								GROUP
							</td>
							<td class="col-md-3 col-xs-3 text-center">
								Act Name
							</td>
							<td class="col-md-2 col-xs-1 text-center">
								Act Money
							</td>
							<td class="col-md-2 col-xs-2 text-center">
								Date
							</td>
							<td class="col-md-3  col-xs-3 text-center">
								Details
							</td>
						</tr>
						<c:forEach var="message" items="${inmessages}">
							<tr>
								<c:if test="${message.readState=='new'}">

									<td class="col-md-2 col-xs-3 text-center">
										<strong>${message.groupName}</strong>
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										<strong>${message.actName}</strong>
									</td>
									<td class="col-md-2 col-xs-1 text-center">
										<div class="text-success text-center">
											<strong> ${message.actMoney}</strong>
										</div>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
										<strong>${message.actDate}</strong>
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										<a href='<c:url value="/doshowMsgDetailsUserviewAction.action" />?msgId=${message.msgId}'>details</a>
									</td>

								</c:if>
								<c:if test="${message.readState=='read'}">
									<td class="col-md-2 col-xs-3 text-center">
										${message.groupName}
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										${message.actName}
									</td>
									<td class="col-md-2 col-xs-1 text-center">
										<div class="text-success text-center">
											<strong> ${message.actMoney}</strong>
										</div>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
										${message.actDate}
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										<a href='<c:url value="/doshowMsgDetailsUserviewAction.action" />?msgId=${message.msgId}'>details</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>

					</table>
					
					<c:if test="${overmessages!=null}">
						<table class="table row">
							<c:forEach var="message" items="${overmessages}">
								<tr class="active">

									<td class="col-md-2 col-xs-3 text-center">
										${message.groupName}
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										${message.actName}
									</td>
									<td class="col-md-2 col-xs-1 text-center">
										<div class="text-success text-center">
											<strong> ${message.actMoney}</strong>
										</div>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
										${message.actDate}
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										details
									</td>

								</tr>
							</c:forEach>
						</table>
					</c:if>

				</div>



			</div>

		</div>

	</body>
</html>