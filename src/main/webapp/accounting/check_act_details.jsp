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
		<title>Check activity details</title>

		<script src="js/jquery-2.2.0.min.js"></script>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
    

        function checkApproved() {
            if (!($('#approved').is(':checked')||$('#disapproved').is(':checked'))){
                alert("please choose approved or disapproved");
                return false;
            } else
                return true;
        }
		
        </script>
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
					<h3 class="panel-title">Details</h3>
				</div>

				<div class="panel-body table-bordered">
					<div class="row form-group form-group-lg">

						<div class="col-xs-3 col-md-3">
							<strong>Activity name:</strong>
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
							<strong>Date:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:date name="groupAct.activity.actDate" format="%{getText('format.date')}"/>
						</div>
						<div class="col-xs-3 col-md-3">
							<strong>Price per person:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:text name="format.money"><s:param value="groupAct.activity.actMoney"/></s:text>
						</div>
					</div>
					
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3 text-left">
							<strong>Description:</strong>
						</div>
						<div class="col-xs-8 col-md-8 display: block">
							<textarea class="form-control" rows="6" id="description"
								name="description" disabled>${groupAct.activity.description}</textarea>
						</div>
					</div>

					
					<div class="row form-group form-group-lg">
						<c:if test="${groupAct.activity.state=='draft'}">
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
										<input type="radio" name="checkState" id="disapproved"
											value="disapproved">
										disapproved
									</label>
								</div>
								<!-- /input-group -->
	
								<div class="col-xs-2  col-md-2 col-xs-offset-5 col-md-offset-5 ">
										<input type="submit" class="btn btn-primary  btn-block"
											value="submit"/>
								</div>
								
							</form>
						</c:if>
						<c:if test="${groupAct.activity.state!='draft'}">
							<div class="col-xs-3 col-md-3 text-left">
								<strong>Comment:</strong>
							</div>
							<div class="col-xs-8 col-md-8 display: block">
								${groupAct.activity.comment}
							</div>
						</c:if>
					</div>
					
					
					
					
					<hr class="invisible" />
					<div>
						<button type="button"
							class="btn btn-default navbar-btn navbar-left"
							onclick=window.history.back();>
							<span class="glyphicon glyphicon-chevron-left"
								aria-hidden="true"></span> back
						</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>