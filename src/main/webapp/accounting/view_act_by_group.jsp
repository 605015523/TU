<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="View activities by group"/>
	    </jsp:include>
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
					<table class="table table-hover"
						style="margin: 10px; padding: 10px 10px 10px 10px;">
					<tr>
					<th class="col-md-3 col-xs-3 text-center">Activity name</th>
					<th class="col-md-2 col-xs-2 text-center">State</th>
					<th class="col-md-3 col-xs-3 text-center">Registration Period</th>
					<th class="col-md-2 col-xs-2 text-center">Act Date</th>
					<th class="col-md-2 col-xs-2 text-center">Details</th>
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