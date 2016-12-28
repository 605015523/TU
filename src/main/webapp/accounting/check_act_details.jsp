<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="Check activity details"/>
	    </jsp:include>
	
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
	
							<div class="col-xs-3 col-md-3 text-left">
								<strong>Comment:</strong>
							</div>
							<div class="col-xs-8 col-md-8 display: block">
								${groupAct.activity.comment}
							</div>
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