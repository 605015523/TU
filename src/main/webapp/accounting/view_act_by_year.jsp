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
		<title>View activities by year</title>

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
				
				<div class="panel-body">

					<table class="table table-hover "
						style="margin: 10px; padding: 10px 10px 10px 10px;">

						<tr  class="success">
							<td class="text-center">
								userName
							</td>
						<c:forEach var="groupname" items="${groupsName}">	
							<td>${groupname}</td>
						</c:forEach>
						    <td class="text-center">quota</td>
						    <td class="text-center">sum</td>
						     <td class="text-center">different</td>
						
						</tr>
						
						<c:forEach var="oneUserGroupCost" items="${allUserGroupCost}">
							<tr>
								<td class="col-md-2 col-xs-2 text-center">
									${oneUserGroupCost.userName}
								</td>
                             <c:forEach var="onegroupCost" items="${oneUserGroupCost.groupCostVO}">
								<td class="col-md-1 col-xs-1">
									${onegroupCost.cost}
								</td>
							</c:forEach>
							 <td class="text-center">${oneUserGroupCost.quota}</td>
						    <td class="text-center">${oneUserGroupCost.sum}</td>
						     <td class="text-center">${oneUserGroupCost.different}</td>
							</tr>

						</c:forEach>

					</table>


				</div>
			</div>
		</div>
	</body>
</html>