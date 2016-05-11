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
		<title>View activity details</title>

		<script src="normalUser/js/jquery-2.2.0.min.js"></script>
		<link href="normalUser/css/bootstrap.min.css" rel="stylesheet">
		<link href="normalUser/css/signin.css" rel="stylesheet">
		<script src="normalUser/js/bootstrap.min.js"></script>
	</head>
	<body>
        <%
			session.setAttribute("validateActId",request.getAttribute("actId"));
		%>
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
					<li role="presentation" class="dropdown">
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
									href='<c:url value="/doshowActsUserviewAction.action" />
              ?year=${year}'>${year}
								</a>
							</li>

						</c:forEach>

					</ul>

				</li>
				<li role="presentation" class="navbar-right">
					<a role="button" href="dologoutUserloginManageAction.action">
						Exit Login </a>
				</li>
			</ul>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						details
					</h3>

				</div>

				<div class="panel-body table-bordered">
					<form action="doValidateActAccountingviewAction.action" method="post"
						role="form" name="form" id="form">
						<div class="row form-group form-group-lg">

							<div class="col-xs-3 col-md-3">
								<strong>ACTNAME:</strong>
							</div>
							<div class="col-xs-3  col-md-3">
								${act.actName}
							</div>
							<div class="col-xs-3  col-md-3">
								<strong> Registration Period:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${act.daterange}
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3">
								<strong>DATE:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${act.actDate}
							</div>
							<div class="col-xs-3 col-md-3">
								<strong>PRICE per PERSON</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${act.actMoney}
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3">
								<strong>NUMBER OF PARTICIPATER:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${act.participatorNO}
							</div>
							<div class="col-xs-3 col-md-3">
								<strong>CONSUMPTION:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${act.sum}
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3 text-left">
								<strong>DESCRIPTION:</strong>
							</div>
							<div class="col-xs-8 col-md-8 display: block">
								<p class="desc">
									${act.description}
								</p>
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-2 col-md-2 text-left">
								<strong>participators:</strong>
							</div>
							<div class="col-xs-10 col-md-10 display: block">
								<table class="table table-bordered">

									<tr class="success">
										<td>
											Name
										</td>
										<td>
											Dept
										</td>
										<td>
											number
										</td>
										<td>
											consumption
										</td>
										<td>
											remark
										</td>
									</tr>
									<c:forEach var="member" items="${act.memberInVO}">
										<tr>
											<td>
												${member.userName}
											</td>
											<td>
												${member.userDept}
											</td>
											<td>
												${member.participatorNO}
											</td>
											<td>
												${member.consumption}
											</td>
											<td>
												${member.remark}
											</td>
										</tr>

									</c:forEach>

								</table>


							</div>

						</div>

						<div class="row form-group form-group-lg">
							<div
								class="col-xs-6  col-md-6 col-xs-offset-2 col-md-offset-2 text-right ">
								<h5>
									●Click
									<strong>submit</strong> to validate this act.
								</h5>
							</div>
							<div class="col-xs-2  col-md-2 navbar-right ">


									<input type="submit" class="btn btn-primary   btn-block"
										value="submit" />


							</div>

						</div>
						<div>
							<button type="button" class="btn btn-default navbar-btn"
								onclick=window.history.back();>
								<span class="glyphicon glyphicon-chevron-left"
									aria-hidden="true"></span> back
							</button>
						</div>
					</form>
				</div>
			</div>
			</div>
	</body>
</html>