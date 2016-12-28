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
					<s:include value="act_details_inc.jsp">
						<s:param name="groupAct" value="groupAct"/>
					</s:include>
					
					<c:if test="${groupAct.activity.state=='tobeapproved'}">
					
						<form action="doCheckActAccountingviewAction.action" method="post"
							role="form" name="form" id="form"
							onsubmit="return checkApproved();">
							<input type="hidden" name="actId" value="${actId}"/>
							
							<div class="form-group col-xs-10 col-md-10 col-xs-offset-1 col-md-offset-1 text-left">
								<textarea class="form-control" rows="6" id="comment"
									name="comment" placeholder="less than 500 words"
									onkeyup="wordStatic(this);"></textarea>
							</div>
						
							<div class=" form-group col-xs-4 col-md-4 col-xs-offset-4 col-md-offset-4 ">
								<label>
									<input type="radio" name="checkState" id="approved"
										value="approved">
									approved
								</label>
								<label>
									<input type="radio" name="checkState" id="draft"
										value="draft">
									disapproved
								</label>
							</div>
							<!-- /input-group -->
	
							<div class="col-xs-2  col-md-2 col-xs-offset-5 col-md-offset-5 ">
									<input type="submit" class="btn btn-primary  btn-block"
										value="submit"/>
							</div>
							<div>
								<button type="button" class="btn btn-default navbar-btn"
									onclick=window.history.back();>
									<span class="glyphicon glyphicon-chevron-left"
										aria-hidden="true"></span>back
								</button>
							</div>
						</form>
					</c:if>
					
					
					<c:if test="${groupAct.activity.state=='tobevalidate'}">

						<form action="doValidateActAccountingviewAction.action" method="post"
							role="form" name="form" id="form">
							<input type="hidden" name="actId" value="${actId}"/>
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
							
						</form>
					</c:if>
				</div>
			</div>
			</div>
	</body>
</html>