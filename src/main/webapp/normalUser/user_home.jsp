<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="Home"/>
	    </jsp:include>
	</head>
	<body>
		<div class="container">
			<div class="signin-head">
				<img src="images/test/head.png" alt="">
			</div>

			<jsp:include page="../menu.jsp">
		        <jsp:param name="active" value="home"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary doublecolumn">
				<div class="panel-heading">
					<h3 class="panel-title">
						Upcoming Activities
					</h3>
				</div>
				<div class="panel-body">
					<table class="table table-hover">
						<tr>
							<th>Activity name</th>
							<th>Date</th>
							<th>Details</th>
						</tr>
						
						<s:iterator var="act" value="upcomingActs">
							<tr>
								<td>
									${act.actName}
								</td>
								<td>
									<s:date name="actDate" format="%{getText('format.date')}"/>
								</td>
								<td>
									<a href='<c:url value="/doshowDetailsUserviewAction" />?actId=${act.actId}'>details</a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
			<div class="panel panel-primary doublecolumn">
				<div class="panel-heading">
					<h3 class="panel-title">
						User Information
					</h3>
				</div>
				<div class="panel-body">

					<table class="table table-hover">

						<tr>
							<td class="col-md-4 col-md-offset-4">
								Name:
							</td>

							<td>
								<c:out value="${currentUser.userName}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Department:
							</td>
							<td>
								<c:out value="${currentUser.userDept}" />
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								In date:
							</td>
							<td>
								<s:date name="currentUser.inDate" format="%{getText('format.date')}"/>
							</td>
						</tr>
						
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Quota:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="currentUser.quota"/>
								</s:text>
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Spending:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="currentUser.spending"/>
								</s:text>
							</td>
						</tr>
						<tr>
							<td class="col-md-4 col-md-offset-4">
								Remaining:
							</td>
							<td>
								<s:text name="format.money">
									<s:param value="remaining" />
								</s:text>
							</td>
						</tr>

					</table>


				</div>
			</div>

		</div>

	</body>
</html>