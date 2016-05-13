<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul class="nav nav-tabs">
	<li role="presentation"><a href="normalUser/user_home.jsp">Home</a>
	</li>
	<c:if test="${userRole==2}">
		<li role="presentation" <c:if test="${param.active=='check'}">class="active"</c:if>><a
			href="doGetAllCheckActAccountingviewAction.action">Check&Validate<c:if
					test="${newCheck!=0}">
					<span class="badge">${newCheck}</span>
				</c:if>
		</a></li>
		<li role="presentation" class="dropdown <c:if test="${param.active=='act_details'}">active</c:if>"><a id="dLabel"
			data-target="#" href="#" data-toggle="dropdown" role="button"
			aria-haspopup="true" aria-expanded="false">Act Details <span
				class="caret"></span>
		</a>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li class="dropdown-header">total cost</li>
				<c:forEach var="year" items="${years}">
					<li><a
						href='<c:url value="/doshowAllActsByYearAccountingviewAction.action" />?year=${year}'>${year}</a></li>
				</c:forEach>
				<li class="dropdown-header">All in group</li>
				<c:forEach var="groupname" items="${groupsName}">
					<li><a
						href='<c:url value="/doshowAllActsByGroupAccountingviewAction.action" />?groupname=${groupname}'>${groupname}</a></li>
				</c:forEach>
			</ul></li>

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
					href='<c:url value="/doshowActsUserviewAction.action" />
              ?year=${year}'>${year}
				</a></li>

			</c:forEach>
		</ul></li>
	<li role="presentation" class="navbar-right"><a role="button"
		href="dologoutUserloginManageAction.action">Exit Login</a></li>
</ul>