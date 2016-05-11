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
		<title>View activity</title>

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
				<li role="presentation" class="dropdown active">
					<a id="dLabel" data-target="#" href="#"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> Act <span class="caret"></span> </a>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
						<li>
							<a href="groupLeader/add_act.jsp">Add Act</a>
						</li>
						<li>
							<a href="doGetAllGroupActLeaderviewAction.action">View Act</a>
						</li>
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
				<div class="panel-heading">
					<h3 class="panel-title">
						Group Acts detail
					</h3>

				</div>


				<div class="panel-body">
					<div class="row">

					</div>


					<table class="table table-hover "
						style="margin: 10px; padding: 10px 10px 10px 10px;">

						<tr>
							<td class="col-md-2 col-xs-2 text-center">
								ACTNAME
							</td>
							<td class="col-md-1 col-xs-1 text-center">
								State
							</td>
							<td class="col-md-3 col-xs-3 text-center">
								Registration Period
							</td>
							<td class="col-md-2 col-xs-2 text-center">
								Act Date
							</td>
							<td class="col-md-2 col-xs-2 text-center">
								Details
							</td>
							<td class="col-md-2 col-xs-2 text-center">
								Edit
							</td>

						</tr>
						<c:forEach var="act" items="${groupacts}">
							<tr>
								<td class="col-md-2 col-xs-2 text-center">
									${act.actName}
								</td>

								<td class="col-md-1 col-xs-1">
									<c:if test="${act.state=='draft'||act.state=='disapproved'}">
										<div class="text-danger text-center">
											<strong> ${act.state}</strong>
										</div>
									</c:if>
									<c:if test="${act.state!='draft'&&act.state!='disapproved'}">
										<div class="text-success text-center">
											<strong> ${act.state}</strong>
										</div>
									</c:if>

								</td>
								<td class="col-md-3 col-xs-3 text-center">
									${act.daterange}
								</td>
								<td class="col-md-2 col-xs-2 text-center">
									${act.actDate}
								</td>
								<td class="col-md-2 col-xs-2 text-center">
									<c:if
										test="${act.state!='draft'&&act.state!='disapproved'&&act.state!='approved'}">
										<a
											href='<c:url value="/doshowActDetailsLeaderviewAction.action" />?actId=${act.actId}'>details</a>
									</c:if>
									<c:if
										test="${act.state=='draft'||act.state=='disapproved'||act.state=='approved'}">
                     details
                     </c:if>
								</td>

								<td class="col-md-2 col-xs-2 text-center">
									<c:if
										test="${act.state=='pending'||act.state=='validate'||act.state=='publish'||act.state=='tobevalidate'}">
                      
                       edit
                    
                      </c:if>


									<c:if
										test="${act.state!='pending'&&act.state!='validate'&&act.state!='publish'&&act.state!='tobevalidate'}">

										<a
											href='<c:url value="/doEditActLeaderviewAction.action" />?actId=${act.actId}'>edit</a>
									</c:if>
								</td>

							</tr>


						</c:forEach>

					</table>


				</div>
			</div>
		</div>
	</body>
</html>