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
		<title>Check validate</title>

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
		        <jsp:param name="active" value="check"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Group Activities details
					</h3>
				</div>
				<div class="panel-body">
					<div class="row">
					</div>
					<table class="table table-hover "
						style="margin: 10px; padding: 10px 10px 10px 10px;">
						<tr>
							<td class="col-md-3 col-xs-3 text-center">ACTNAME</td>
							<td class="col-md-2 col-xs-2 text-center">State</td>
							<td class="col-md-3 col-xs-3 text-center">Price / person</td>
							<td class="col-md-2 col-xs-2 text-center">Act Date</td>
							<td class="col-md-2 col-xs-2 text-center">Details</td>
						</tr>
						<s:iterator var="act" value="acts">
							<tr>
								<c:if test="${act.state=='draft'||act.state=='tobevalidate'}">
									<td class="col-md-3 col-xs-3 text-center">
										<strong>${act.actName}</strong>
									</td>
									<td class="col-md-2 col-xs-2 text-danger text-center">
										<strong> ${act.state}</strong>
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										<strong>${act.actMoney}</strong>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
										<strong><s:date name="actDate" format="%{getText('format.date')}"/></strong>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
									<c:if test="${act.state=='draft'}">
										<a href='<c:url value="/doshowCheckDetailsAccountingviewAction.action" />?actId=${act.actId}'>
											<strong>details</strong>
										</a>
									</c:if>
									<c:if test="${act.state=='tobevalidate'}">
										<a href='<c:url value="/doshowValidateDetailsAccountingviewAction.action" />?actId=${act.actId}'>
											<strong>details</strong>
										</a>
									</c:if>
									</td>
								</c:if>
								<c:if test="${act.state!='draft'&&act.state!='tobevalidate'}">
									<td class="col-md-3 col-xs-3 text-center">
										${act.actName}
									</td>
									<td class="col-md-2 col-xs-2 text-center">
										<s:if test="%{state=='disapproved'}">
											<div class="text-danger">
												${act.state}
											</div>
										</s:if>
										<s:else>
											<div class="text-success">
												${act.state}
											</div>
										</s:else>
									</td>
									<td class="col-md-3 col-xs-3 text-center">
										<s:text name="format.money"><s:param value="actMoney"/></s:text>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
										<s:date name="actDate" format="%{getText('format.date')}"/>
									</td>
									<td class="col-md-2 col-xs-2 text-center">
									<s:if test="%{state=='validate'}">
										<a
											href='<c:url value="/doshowValidateDetailsAccountingviewAction.action" />?actId=${act.actId}'>details
										</a>
									</s:if>
									<s:else>
										<a
											href='<c:url value="/doshowCheckDetailsAccountingviewAction.action" />?actId=${act.actId}'>details</a>
									</s:else>
									</td>
								</c:if>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>