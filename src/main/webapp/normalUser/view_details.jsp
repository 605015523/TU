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
		<title>View details</title>

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
					<h3 class="panel-title">details</h3>
				</div>


				<div class="panel-body table-bordered">

					<div class="row form-group form-group-lg">

						<div class="col-xs-3 col-md-3">
							<strong>Act Name:</strong>
						</div>
						<div class="col-xs-3  col-md-3">
							${act.actName}
						</div>
						<div class="col-xs-3  col-md-3">
							<strong>Group:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							${act.group}
						</div>
					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3">
							<strong>Date:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:date name="act.actDate" format="%{getText('format.date')}"/>
						</div>
						<div class="col-xs-3 col-md-3">
							<strong>Price per Person</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:text name="format.money">
								<s:param value="act.consumption"/>
							</s:text>
						</div>
					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3 text-left">
							<strong>Description:</strong>
						</div>
						<div class="col-xs-8 col-md-8 display: block">
							<p class="desc">
								${act.description}
							</p>
						</div>

					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3 text-left">
							<strong>Remark:</strong>
						</div>
						<div class="col-xs-8 col-md-8 display: block">
							<p
								style="background-color: #eaeaea; height: 200px; border-radius: 5px; padding: 10px;">
								${act.remark}
							</p>
						</div>

					</div>
					<div class="row form-group form-group-lg center-block">
						<script type="text/javascript">
					function deleteAct(){
						var actId=${act.actId};
						var truthBeTold = window.confirm("quit this activity?");
						if (truthBeTold){
							window.location.href="<c:url value='/doDeleteOneActUserviewAction.action?actId="+actId+"' />";
							window.alert("quit success!");
						}
					}
</script>
						<c:if
							test="${act.state=='pending'||act.state=='tobevalidate'||act.state=='validate'}">

							<div
								class="form-group col-xs-offset-4 col-md-offset-4 col-md-2 col-xs-2">
								<input type="submit" class="btn btn-primary  btn-block"
									value="edit" disabled />
							</div>
							<div class="form-group col-md-2 col-xs-2">
								<input type="submit" class="btn btn-primary  btn-block"
									onclick=deleteAct();; value="quit" disabled />
							</div>
						</c:if>

						<c:if test="${act.state=='publish'}">

							<div class="form-group col-xs-offset-5 col-md-offset-5 col-md-2 col-xs-2">
								<input type="submit" class="btn btn-primary  btn-block"
									onclick="deleteAct();" value="quit" />
							</div>
						
						</c:if>

					</div>
					<div>
						<button type="button" class="btn btn-default navbar-btn"
							onclick=window.history.back();>
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							back
						</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>