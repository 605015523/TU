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
<script type="text/javascript">
	function deleteAct(actId){
		var truthBeTold = window.confirm("quit this activity?");
		if (truthBeTold){
			window.location.href="<c:url value='/doDeleteOneActUserviewAction.action?actId="+actId+"' />";
			window.alert("quit success!");
		}
	}
</script>
		
</head>
<body>
	<div class="container">
		<div class="signin-head">
			<img src="images/test/head.png" alt="">
		</div>
		<jsp:include page="../menu.jsp">
			<jsp:param name="active" value="act" />
		</jsp:include>
		<hr class="invisible" />
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Details</h3>
			</div>

			<div class="panel-body table-bordered">
				<s:include value="../accounting/act_details_inc.jsp">
					<s:param name="groupAct" value="groupAct"/>
				</s:include>
					
				<div class="row form-group form-group-lg ">
					<div class="col-xs-offset-5 col-md-offset-5 col-xs-2  col-md-2">
						<a role="button" class="btn btn-primary btn-block"
							href='<c:url value="/doInActUserviewAction" />?actId=${actId}'>I'm
							in</a>
					</div>
					
					<div class="form-group col-md-2 col-xs-2">
						<a role="button" class="btn btn-primary btn-block"
								href='#' onclick="deleteAct(${actId});">quit</a>
					</div>
					
					<s:if test="%{groupAct.activity.state=='disapproved'}">
						<div class="col-xs-2 col-md-2">
							<a role="button" class="btn btn-primary btn-block"
								href='<c:url value="doToValidateActLeaderviewAction" />?actId=${actId}'>Submit</a>
						</div>
					</s:if>
					
					<s:if test="%{groupAct.activity.state=='pending'}">
						<div class="col-xs-2  col-md-2">
							<a role="button" class="btn btn-primary btn-block"
								href='<c:url value="/doEditActLeaderviewAction.action" />?actId=${groupAct.activity.actId}'>edit</a>
						</div>
					</s:if>

				</div>
				<div>
					<button type="button" class="btn btn-default navbar-btn"
						onclick="window.history.back();">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						back
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>