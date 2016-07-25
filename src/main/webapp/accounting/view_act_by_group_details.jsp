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
		<title>View activity details</title>

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
						details
					</h3>

				</div>

				<div class="panel-body table-bordered">
					<form action="doToValidateActLeaderviewAction.action" method="post"
						role="form" name="form" id="form">
						<div class="row form-group form-group-lg">

							<div class="col-xs-3 col-md-3">
								<strong>ACTNAME:</strong>
							</div>
							<div class="col-xs-3  col-md-3">
								${groupAct.actName}
							</div>
							<div class="col-xs-3  col-md-3">
								<strong> Registration Period:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${groupAct.daterange}
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3">
								<strong>DATE:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${groupAct.actDate}
							</div>
							<div class="col-xs-3 col-md-3">
								<strong>PRICE per PERSON</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${groupAct.actMoney}
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3">
								<strong>NUMBER OF PARTICIPANTS:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${groupAct.nbParticipants}
							</div>
							<div class="col-xs-3 col-md-3">
								<strong>CONSUMPTION:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${groupAct.sum}
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3 text-left">
								<strong>DESCRIPTION:</strong>
							</div>
							<div class="col-xs-8 col-md-8 display: block">
								<p class="desc">
									${groupAct.description}
								</p>
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-2 col-md-2 text-left">
								<strong>participants:</strong>
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
									<c:forEach var="member" items="${groupAct.memberInVO}">
										<tr>
											<td>
												${member.userName}
											</td>
											<td>
												${member.userDept}
											</td>
											<td>
												${member.nbParticipants}
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