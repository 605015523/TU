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
		<title>View activity</title>

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
		        <jsp:param name="active" value="act"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Group Acts details
					</h3>
				</div>


				<div class="panel-body">

					<table class="table table-hover "
						style="margin: 10px; padding: 10px 10px 10px 10px;">

						<thead>
						<tr>
							<td class="col-md-2 col-xs-2 text-center">
								Activity name
							</td>
							<td class="col-md-1 col-xs-1 text-center">
								State
							</td>
							<td class="col-md-3 col-xs-3 text-center">
								Registration Period
							</td>
							<td class="col-md-2 col-xs-2 text-center">
								Activity Date
							</td>
							<td class="col-md-2 col-xs-2 text-center">
								Details
							</td>
							<td class="col-md-2 col-xs-2 text-center">
								Edit
							</td>

						</tr>
						</thead>
						<s:iterator var="groupAct" value="groupActs">
							<tr>
								<td class="col-md-2 col-xs-2 text-center">
									${groupAct.activity.actName}
								</td>

								<td class="col-md-1 col-xs-1">
									<s:if test="%{activity.state=='draft'||activity.state=='disapproved'}">
										<div class="text-danger text-center">
											<strong> ${groupAct.activity.state}</strong>
										</div>
									</s:if>
									<s:else>
										<div class="text-success text-center">
											<strong> ${groupAct.activity.state}</strong>
										</div>
									</s:else>

								</td>
								<td class="col-md-3 col-xs-3 text-center">
									<s:date name="activity.enrollStartDate" format="%{getText('format.date')}"/> - <s:date name="activity.enrollEndDate" format="%{getText('format.date')}"/>
								</td>
								<td class="col-md-2 col-xs-2 text-center">
									<s:date name="activity.actDate" format="%{getText('format.date')}"/>
								</td>
								<td class="col-md-2 col-xs-2 text-center">
									<s:if test="%{activity.state=='draft'||activity.state=='disapproved'||activity.state=='approved'}">
                     					details
                     				</s:if>
									<s:else>
										<a href='<c:url value="/doshowActDetailsLeaderviewAction.action" />?actId=${groupAct.activity.actId}'>details</a>
									</s:else>
									
								</td>

								<td class="col-md-2 col-xs-2 text-center">
									<s:if test="%{activity.state=='validate'||activity.state=='publish'||activity.state=='tobevalidate'}">
                       					edit
                      				</s:if>
									<s:else>
										<a href='<c:url value="/doEditActLeaderviewAction.action" />?actId=${groupAct.activity.actId}'>edit</a>
									</s:else>
								</td>

							</tr>


						</s:iterator>

					</table>


				</div>
			</div>
		</div>
	</body>
</html>