<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
		<strong>Price per person</strong>
	</div>
	<div class="col-xs-3 col-md-3">
		<s:text name="format.money"><s:param value="groupAct.activity.actMoney"/></s:text>
	</div>
</div>
<div class="row form-group form-group-lg">
	<div class="col-xs-3 col-md-3">
		<strong>Number of participants:</strong>
	</div>
	<div class="col-xs-3 col-md-3">
		${groupAct.nbParticipants}
	</div>
	
</div>

<div class="row form-group form-group-lg">
	<div class="col-xs-3 col-md-3 text-left">
		<strong>Description:</strong>
	</div>
	<div class="col-xs-8 col-md-8 display: block">
		<p class="desc">
			${groupAct.activity.description}
		</p>
	</div>
</div>


<c:if test="${userRole==1 || userRole==2}">
	<!-- For group leader and accounting -->
	<div class="row form-group form-group-lg">
		<div class="col-xs-3 col-md-3">
			<strong>Status:</strong>
		</div>
		<div class="col-xs-3 col-md-3">
			${groupAct.activity.state}
		</div>
		<div class="col-xs-3 col-md-3">
			<strong>Consumption:</strong>
		</div>
		<div class="col-xs-3 col-md-3">
			<s:text name="format.money"><s:param value="groupAct.sum"/></s:text>
		</div>
	</div>
	<div class="row form-group form-group-lg">
		<div class="col-xs-2 col-md-2 text-left">
			<strong>Participants:</strong>
		</div>
		<div class="col-xs-10 col-md-10 display: block">
			<table class="table table-bordered">
				<tr class="success">
					<th>Name</th>
					<th>Dept</th>
					<th>Number</th>
					<th>Consumption</th>
					<th>Remark</th>
				</tr>
				<s:iterator var="useract" value="groupAct.memberInVO">
					<tr>
						<td>${useract.user.userName}</td>
						<td>${useract.user.userDept}</td>
						<td>${useract.nbParticipants}</td>
						<td><s:text name="format.money"><s:param value="consumption"/></s:text></td>
						<td><s:property value="remark"/></td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
</c:if>