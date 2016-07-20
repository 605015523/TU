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
		<title>View activities</title>

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
		        <jsp:param name="active" value="activities"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Acts in ${thisyear}
					</h3>

				</div>


				<div class="panel-body">

				<table class="table table-hover ">
					<tr>
						<td>
							ACTNAME
						</td>
						<td>
							GROUP
						</td>
						<td>
							CONSUMPTION
						</td>
						<td>
							Date
						</td>
						<td>
							Details
						</td>
					</tr>
					<c:forEach var="act" items="${acts}">
						<tr>
							<td>
								${act.actName}
							</td>
							<td>
								${act.group}
							</td>
							    
							<td>
							<c:if test="${act.state=='validate'}">
								${act.consumption}
							</c:if>
							</td>
							<td>
								${act.actDate}
							</td>
							<td>
								<a href='<c:url value="/doshowDetailsUserviewAction.action" />?actId=${act.actId}'>details</a>
							</td>
						</tr>

					</c:forEach>

				</table>

			</div>
			</div>

		</div>

	</body>
</html>