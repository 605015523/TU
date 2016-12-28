<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul class="nav nav-tabs">
	<li role="presentation" <c:if test="${param.active=='home'}">class="active"</c:if>><a href="displayHomePageUserloginManageAction">Home</a>
	</li>
	<c:if test="${userRole==1}">
		<li role="presentation" class="dropdown <c:if test="${param.active=='act'}">active</c:if>"><a id="dLabel"
			data-target="#" href="#" data-toggle="dropdown" role="button"
			aria-haspopup="true" aria-expanded="false">Manage activities<span
				class="caret"></span>
		</a>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="addActFormLeaderviewAction">Add activity</a></li>
				<li><a href="doGetAllGroupActLeaderviewAction">View activities</a></li>
			</ul></li>
	</c:if>
	<c:if test="${userRole==2}">
		<li role="presentation" <c:if test="${param.active=='check'}">class="active"</c:if>active><a
			href="doGetAllCheckActAccountingviewAction.action" >Check&Validate<c:if
					test="${newCheck!=0}">
					<span class="badge">${newCheck}</span>
				</c:if>
		</a></li>
		<li role="presentation" class="dropdown <c:if test="${param.active=='act_details'}">active</c:if>"><a id="dLabel"
			data-target="#" href="#" data-toggle="dropdown" role="button"
			aria-haspopup="true" aria-expanded="false">Activity details <span
				class="caret"></span>
		</a>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<li class="dropdown-header">Total cost</li>
			<c:forEach var="year" items="${years}">
				<li><a
					href='<c:url value="/doshowAllActsByYearAccountingviewAction.action" />?year=${year}'>${year}</a>
				</li>
			</c:forEach>
			<li class="dropdown-header">All in group</li>
			<c:forEach var="group" items="${groups}">
				<li><a
					href='<c:url value="/doshowAllActsByGroupAccountingviewAction.action" />?groupId=${group.groupId}'>${group.groupName}</a>
				</li>
			</c:forEach>

		</ul></li>

	</c:if>

	<li role="presentation" class="dropdown <c:if test="${param.active=='activities'}">active</c:if>"><a id="dLabel"
		data-target="#" href="#" data-toggle="dropdown" role="button"
		aria-haspopup="true" aria-expanded="false">My activities <span
			class="caret"></span>
		</a>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<c:forEach var="year" items="${years}">
				<li><a
					href='<c:url value="/doshowActsUserviewAction.action" />?year=${year}'>${year}</a>
				</li>
			</c:forEach>
		</ul>
	</li>
	
	<c:if test="${userRole==4}">
	<li role="presentation" class="dropdown <c:if test="${param.active=='admin'}">active</c:if>"><a id="dLabel"
		data-target="#" href="#" data-toggle="dropdown" role="button"
		aria-haspopup="true" aria-expanded="false">Administration<span
			class="caret"></span>
		</a>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<li><a
				href='<c:url value="/listAllManageUsersAction.action" />'>Users</a>
			</li>
		</ul>
	</li>
	</c:if>
	
	<li role="presentation" class="dropdown navbar-right"><a id="dLabel"
		data-target="#" href="#" data-toggle="dropdown" role="button"
		aria-haspopup="true" aria-expanded="false"><c:out value="${currentUser.userName}" /><span
			class="caret"></span>
		</a>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<li class="dropdown-header"><c:out value="${currentUser.userDept}" /></li>
			<li>
				<a href="normalUser/change_pwd.jsp" class="navbar-link">Change
							Password</a>
			</li>
			<li><a role="button"
				href="<c:url value="j_spring_security_logout" />">Log out</a>
			</li>
		</ul>
	</li>
</ul>