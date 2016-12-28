<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common_header.inc.jsp">
	    <jsp:param name="subtitle" value="View activity details"/>
	</jsp:include>
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