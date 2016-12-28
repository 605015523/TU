<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="View details"/>
	    </jsp:include>
	    
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
		        <jsp:param name="active" value="activities"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Details</h3>
				</div>


				<div class="panel-body table-bordered">

					<div class="row form-group form-group-lg">

						<div class="col-xs-3 col-md-3">
							<strong>Activity name:</strong>
						</div>
						<div class="col-xs-3  col-md-3">
							${userAct.activity.actName}
						</div>
						<div class="col-xs-3  col-md-3">
							<strong>Group:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							${userAct.activity.groupName}
						</div>
					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3">
							<strong>Date:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:date name="userAct.activity.actDate" format="%{getText('format.date')}"/>
						</div>
						<div class="col-xs-3 col-md-3">
							<strong>Price per person</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:text name="format.money">
								<s:param value="userAct.consumption"/>
							</s:text>
						</div>
					</div>
					
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3">
							<strong>Nb participants</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							${userAct.nbParticipants}
						</div>
					</div>
					
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3 text-left">
							<strong>Description:</strong>
						</div>
						<div class="col-xs-8 col-md-8 display: block">
							<p class="desc">
								<s:property value="userAct.activity.description"/>
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
								<s:property value="userAct.remark"/>
							</p>
						</div>

					</div>
					<div class="row form-group form-group-lg center-block">
						
						<c:if
							test="${userAct.activity.state=='pending'||
							userAct.activity.state=='tobevalidate'||
							userAct.activity.state=='validate'}">

							<div class="col-xs-offset-5 col-md-offset-5 col-xs-2  col-md-2">
								<a role="button" class="btn btn-primary btn-block"
									href='<c:url value="/doInActUserviewAction" />?actId=${userAct.activity.actId}'>I'm
									in</a>
							</div>
							
						</c:if>

						<c:if test="${userAct.activity.state=='publish'}">

							<div class="col-xs-offset-5 col-md-offset-5 form-group col-md-2 col-xs-2">
								<a role="button" class="btn btn-primary btn-block"
										href='#' onclick="deleteAct(${userAct.activity.actId});">quit</a>
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