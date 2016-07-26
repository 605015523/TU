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
		<title>View message details</title>

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
		        <jsp:param name="active" value="messages"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						details
					</h3>
				</div>
				<div class="panel-body table-bordered">

					<div class="row form-group form-group-lg">

						<div class="col-xs-3 col-md-3">
							<strong>ACTNAME:</strong>
						</div>
						<div class="col-xs-3  col-md-3">
							${msgDetails.message.activity.actName}
						</div>
						<div class="col-xs-3  col-md-3">
							<strong>GROUP:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							${msgDetails.groupName}
						</div>
					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3">
							<strong>DATE:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:property value="msgDetails.message.activity.actDate"/>
						</div>
						<div class="col-xs-3 col-md-3">
							<strong>PRICE per PERSON:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							${msgDetails.message.activity.actMoney}
						</div>
					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3">
							<strong>REGISTRATION DATE:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:property value="msgDetails.message.activity.enrollStartDate"/> - <s:property value="msgDetails.message.activity.enrollEndDate"/>
						</div>
						
					</div>

					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3 text-left">
							<strong> DESCRIPTION:</strong>
						</div>
						<div class="col-xs-8 col-md-8 display: block">
							<p class="desc">
								${msgDetails.message.activity.description}
							</p>
						</div>
					</div>
					<div class="row form-group form-group-lg center-block">		

							<div
								class="form-group col-xs-offset-5 col-md-offset-5 col-md-2 col-xs-2">
								<a role="button" class="btn btn-primary  btn-block"
									href='<c:url value="/doInActUserviewAction.action" />?msgId=${msgDetails.message.msgId}'>I'm
									in</a>
							</div>
					
					</div>
				</div>
			</div>
		</div>
	</body>
</html>