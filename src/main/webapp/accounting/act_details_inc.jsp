<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row form-group form-group-lg">

						<div class="col-xs-3 col-md-3">
							<strong>ACTNAME:</strong>
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
							<strong>DATE:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:date name="groupAct.activity.actDate" format="%{getText('format.date')}"/>
						</div>
						<div class="col-xs-3 col-md-3">
							<strong>PRICE per PERSON</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:text name="format.money"><s:param value="groupAct.activity.actMoney"/></s:text>
						</div>
					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3">
							<strong>NUMBER OF PARTICIPANTS:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							${groupAct.nbParticipants}
						</div>
						<div class="col-xs-3 col-md-3">
							<strong>CONSUMPTION:</strong>
						</div>
						<div class="col-xs-3 col-md-3">
							<s:text name="format.money"><s:param value="groupAct.sum"/></s:text>
						</div>
					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-3 col-md-3 text-left">
							<strong>DESCRIPTION:</strong>
						</div>
						<div class="col-xs-8 col-md-8 display: block">
							<p class="desc">
								${groupAct.activity.description}
							</p>
						</div>
					</div>
					<div class="row form-group form-group-lg">
						<div class="col-xs-2 col-md-2 text-left">
							<strong>participants:</strong>
						</div>
						<div class="col-xs-10 col-md-10 display: block">
							<table class="table table-bordered">

								<tr class="success">
									<td>Name</td>
									<td>Dept</td>
									<td>number</td>
									<td>consumption</td>
									<td>remark</td>
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