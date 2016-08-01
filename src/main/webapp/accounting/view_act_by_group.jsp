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
		<title>View activities by group</title>
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
		        <jsp:param name="active" value="act_details"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						${group.groupName} activities
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
					<td class="col-md-3 col-xs-3 text-center">Registration Period</td>
					<td class="col-md-2 col-xs-2 text-center">Act Date</td>
					<td class="col-md-2 col-xs-2 text-center">Details</td>
				    </tr>
						<s:iterator var="grpAct" value="groupActs">
							<c:if test="${grpAct.activity.state=='validate'}">
								<tr>
								<td class="col-md-3 col-xs-3 text-center">${grpAct.activity.actName}</td>
								<td class="col-md-2 col-xs-2 text-success text-center">
									<strong>${grpAct.activity.state}</strong></td>
								<td class="col-md-3 col-xs-3 text-center">
									<s:date name="activity.enrollStartDate" format="%{getText('format.date')}"/> - <s:date name="activity.enrollEndDate" format="%{getText('format.date')}"/>
								</td>
								<td class="col-md-2 col-xs-2 text-center">
									<s:date name="activity.actDate" format="%{getText('format.date')}"/>
								</td>
								<td class="col-md-2 col-xs-2 text-center">
								<a href='<c:url value="/doshowActDetailsInGroupAccountingviewAction.action" />?actId=${grpAct.activity.actId}'>details</a></td>
				                </tr>
							</c:if>
                      </s:iterator>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>