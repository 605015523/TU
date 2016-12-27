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
		<title>View messages</title>

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
		        <jsp:param name="active" value="messages"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Messages</h3>
				</div>


				<div class="panel-body">

					<table class="table table-hover row">

						<tr>
							<th class="col-md-2 col-xs-3 text-center">
								Group
							</th>
							<th class="col-md-3 col-xs-3 text-center">
								Activity name
							</th>
							<th class="col-md-2 col-xs-1 text-center">
								Price / person
							</th>
							<th class="col-md-2 col-xs-2 text-center">
								Date
							</th>
							<th class="col-md-3  col-xs-3 text-center">
								Details
							</th>
						</tr>
						<s:iterator var="usermsg" value="inmessages">
							<tr>
								<c:if test="${usermsg.readState=='new'}">

									<td class="col-md-2 col-xs-3 text-center">
										<strong>${usermsg.groupName}</strong>
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										<strong>${usermsg.message.activity.actName}</strong>
									</td>
									<td class="col-md-2 col-xs-1 text-center">
										<strong class="text-success">
											<s:text name="format.money">
											    <s:param value="message.activity.actMoney"/>
											</s:text>
										</strong>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
										<strong><s:date name="message.activity.actDate" format="%{getText('format.date')}"/></strong>
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										<a href='<c:url value="/doshowMsgDetailsUserviewAction.action" />?msgId=${usermsg.message.msgId}'>details</a>
									</td>

								</c:if>
								<c:if test="${usermsg.readState=='read'}">
									<td class="col-md-2 col-xs-3 text-center">
										${usermsg.groupName}
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										${usermsg.message.activity.actName}
									</td>
									<td class="col-md-2 col-xs-1 text-center">
										<div class="text-success text-center">
											<s:text name="format.money">
												<strong><s:param value="message.activity.actMoney"/></strong>
											</s:text>
										</div>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
										<s:date name="message.activity.actDate" format="%{getText('format.date')}"/>
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										<a href='<c:url value="/doshowMsgDetailsUserviewAction.action" />?msgId=${usermsg.message.msgId}'>details</a>
									</td>
								</c:if>
							</tr>
						</s:iterator>

						<s:iterator var="usermsg" value="overmessages">
							<tr class="active">

								<td class="col-md-2 col-xs-3 text-center">
									${usermsg.groupName}
								</td>
								<td class="col-md-3 col-xs-3 text-center">
									${usermsg.message.activity.actName}
								</td>
								<td class="col-md-2 col-xs-1 text-center">
									<div class="text-success text-center">
										<s:text name="format.money">
										    <s:param value="message.activity.actMoney"/>
										</s:text>
									</div>
								</td>
								<td class="col-md-2 col-xs-2 text-center">
									<s:date name="message.activity.actDate" format="%{getText('format.date')}"/>
								</td>
								<td class="col-md-3 col-xs-3 text-center">
									details
								</td>

							</tr>
						</s:iterator>
					</table>
				</div>

			</div>

		</div>

	</body>
</html>