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
		        <jsp:param name="active" value="act"/>
			</jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">details</h3>
				</div>

				<div class="panel-body table-bordered">
					<form action="doValidateActAccountingviewAction.action" method="post"
						role="form" name="form" id="form">
						<input type="hidden" name="actId" value="${actId}"/>
						<div class="row form-group form-group-lg">

							<div class="col-xs-3 col-md-3">
								<strong>ACTNAME:</strong>
							</div>
							<div class="col-xs-3  col-md-3">
								${groupAct.activity.actName}
							</div>
							<div class="col-xs-3  col-md-3">
								<strong>Registration Period:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								<s:date name="groupAct.activity.enrollStartDate" format="%{getText('format.date')}"/> - <s:date name="groupAct.activity.enrollEndDate" format="%{getText('format.date')}"/>
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3">
								<strong>DATE:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								<s:date name="groupAct.activity.actDate" format="%{getText('format.date')}"/>
							</div>
							<div class="col-xs-3 col-md-3">
								<strong>PRICE per PERSON</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								<s:text name="format.money"><s:param value="groupAct.activity.actMoney"/></s:text>
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
								<s:text name="format.money"><s:param value="groupAct.sum"/></s:text>
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3 text-left">
								<strong>DESCRIPTION:</strong>
							</div>
							<div class="col-xs-8 col-md-8 display: block">
								<p class="desc">
									${groupAct.activity.description}
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
										<td>Name</td>
										<td>Dept</td>
										<td>number</td>
										<td>consumption</td>
										<td>remark</td>
									</tr>
									<s:iterator var="useract" value="groupAct.memberInVO">
										<tr>
											<td>${useract.user.userName}</td>
											<td>${useract.user.userDept}</td>
											<td>${useract.nbParticipants}</td>
											<td><s:text name="format.money"><s:param value="consumption"/></s:text></td>
											<td><s:property value="remark"/></td>
										</tr>

									</s:iterator>

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