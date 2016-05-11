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
								<c:if test="${userRole==2}">
					<li role="presentation">
						<a href="doGetAllCheckActAccountingviewAction.action">Check&Validate<c:if test="${newCheck!=0}">
								<span class="badge">${newCheck}</span>
							</c:if>
						</a>
					</li>
					<li role="presentation" class="dropdown active">
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
				<li role="presentation">
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