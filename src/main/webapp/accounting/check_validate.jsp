<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="Check validate"/>
	    </jsp:include>
	</head>
	<body>

		<div class="container">
			<div class="signin-head">
				<img src="images/test/head.png" alt="">
			</div>
			<jsp:include page="../menu.jsp">
		        <jsp:param name="active" value="check"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Activities details
					</h3>
				</div>
				<div class="panel-body">
					<div class="row">
					</div>
					<table class="table table-hover "
						style="margin: 10px; padding: 10px 10px 10px 10px;">
						<tr>
							<th class="col-md-3 col-xs-3 text-center">Activity name</th>
							<th class="col-md-2 col-xs-2 text-center">State</th>
							<th class="col-md-3 col-xs-3 text-center">Price / person</th>
							<th class="col-md-2 col-xs-2 text-center">Act date</th>
							<th class="col-md-2 col-xs-2 text-center">Details</th>
						</tr>
						<s:iterator var="act" value="acts">
						
							<tr <s:if test="%{state=='tobevalidate'||state=='tobeapproved'}">class="important"</s:if>>
								<td class="col-md-3 col-xs-3 text-center">
									${act.actName}
								</td>
								
								<s:if test="%{state=='tobevalidate'||state=='tobeapproved'}">
									<td class="col-md-2 col-xs-2 text-danger text-center">
										${act.state}
									</td>
								</s:if>
								<s:else>
									<td class="col-md-2 col-xs-2 text-success text-center">
										${act.state}
									</td>
								</s:else>
								
								<td class="col-md-3 col-xs-3 text-center">
									<s:text name="format.money"><s:param value="actMoney"/></s:text>
								</td>
									
								<td class="col-md-2 col-xs-2 text-center">
									<s:date name="actDate" format="%{getText('format.date')}"/>
								</td>
								
								<td class="col-md-2 col-xs-2 text-center">
									<a href='<c:url value="/doshowCheckValidateDetailsAccountingviewAction.action" />?actId=${act.actId}'>details</a>
								</td>
							</tr>

						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>