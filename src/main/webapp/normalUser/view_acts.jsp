<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="View my activities"/>
	    </jsp:include>
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
					<h3 class="panel-title">
						My activities in ${thisyear}
					</h3>
				</div>


				<div class="panel-body">

					<table class="table table-hover">
						<tr>
							<th>Activity name</th>
							<th>Group</th>
							<th>Consumption</th>
							<th>Date</th>
							<th>Details</th>
						</tr>
						<s:iterator var="useract" value="useracts">
							<tr>
								<td>${useract.activity.actName}</td>
								<td>${useract.activity.groupName}</td>
								    
								<td>
								<c:if test="${useract.activity.state=='validate'}">
									<s:text name="format.money">
										<s:param value="activity.consumption"/>
									</s:text>
								</c:if>
								</td>
								<td>
									<s:date name="activity.actDate" format="%{getText('format.date')}"/>
								</td>
								<td>
									<a href='<c:url value="/doshowDetailsUserviewAction" />?actId=${useract.activity.actId}'>details</a>
								</td>
							</tr>
	
						</s:iterator>
	
					</table>

				</div>
			</div>

		</div>

	</body>
</html>