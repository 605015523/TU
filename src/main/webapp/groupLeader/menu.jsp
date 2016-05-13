<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul class="nav nav-tabs">
	<li role="presentation"><a href="normalUser/user_home.jsp">Home</a>
	</li>
	<c:if test="${userRole==1}">
		<li role="presentation" class="dropdown <c:if test="${param.active=='act'}">active</c:if>">
			<a id="dLabel"
				data-target="#" href="#" data-toggle="dropdown" role="button"
				aria-haspopup="true" aria-expanded="false"> Act <span
					class="caret"></span>
			</a>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="groupLeader/add_act.jsp">Add Act</a></li>
				<li><a href="doGetAllGroupActLeaderviewAction.action">View
						Act</a></li>
			</ul>
		</li>
	</c:if>
	<li role="presentation"><a
		href="doshowMessagesUserviewAction.action">Message <c:if
				test="${newMsg!=0}">
				<span class="badge"> ${newMsg}</span>
			</c:if>
	</a></li>

	<li role="presentation" class="dropdown"><a id="dLabel"
		data-target="#" href="#" data-toggle="dropdown" role="button"
		aria-haspopup="true" aria-expanded="false"> activities <span
			class="caret"></span>
	</a>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<c:forEach var="year" items="${years}">
				<li><a
					href='<c:url value="/doshowActsUserviewAction.action" />?year=${year}'>${year}</a>
				</li>

			</c:forEach>

		</ul></li>
	<li role="presentation" class="navbar-right"><a role="button"
		href="dologoutUserloginManageAction.action">Exit Login</a></li>
</ul>