<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="View activities by year"/>
	    </jsp:include>
	</head>
	<body>

		<div class="container">
			<div class="signin-head">
				<img src="images/test/head.png" alt="">
			</div>
			<jsp:include page="../menu.jsp">
		        <jsp:param name="active" value="act_details"/>
		    </jsp:include>
			<hr class="invisible" />
			<div class="panel panel-primary">
				
				<div class="panel-body">

					<table class="table table-hover "
						style="margin: 10px; padding: 10px 10px 10px 10px;">

						<tr  class="success">
							<th class="text-center">
								userName
							</th>
						<c:forEach var="group" items="${groups}">
							<th>${group.groupName}</th>
						</c:forEach>
						    <th class="text-center">quota</th>
						    <th class="text-center">sum</th>
						    <th class="text-center">different</th>
						</tr>
						
						<s:iterator var="oneUserGroupCost" value="allUserGroupCost">
							<tr>
								<td class="col-md-2 col-xs-2 text-center">
									${oneUserGroupCost.userName}
								</td>
								<c:forEach var="group" items="${groups}">
									<td class="col-md-1 col-xs-1">
										${oneUserGroupCost.groupCostVO[group.groupId].cost}
									</td>
								</c:forEach>
                             <%-- <c:forEach var="onegroupCost" items="${oneUserGroupCost.groupCostVO}">
								<td class="col-md-1 col-xs-1">
									${onegroupCost.cost}
								</td>
							</c:forEach> --%>
								<td class="text-center"><s:text name="format.money"><s:param value="quota"/></s:text></td>
						    	<td class="text-center"><s:text name="format.money"><s:param value="sum"/></s:text></td>
						    	<td class="text-center"><s:text name="format.money"><s:param value="different"/></s:text></td>
							</tr>

						</s:iterator>

					</table>


				</div>
			</div>
		</div>
	</body>
</html>