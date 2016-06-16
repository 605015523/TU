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

		<script src="js/jquery-2.2.0.min.js"></script>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>


		<div class="container">
			<div class="signin-head">
				<img src="normalUser/images/test/head.png" alt="">
			</div>
			<jsp:include page="menu.jsp">
		        <jsp:param name="active" value="messages"/>
		    </jsp:include>
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