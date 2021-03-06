<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="Edit activity details"/>
	    </jsp:include>
	    
		<script type="text/javascript">

        function autoGenerate() {
            var sum = document.getElementById("sum").value;
            var nbParticipants = document.getElementById("nbParticipants").value;
            var actMoney = Number(sum)/Number(nbParticipants);
            document.getElementById("actMoney").value=actMoney;
       
            var tbodyObj = document.getElementById("MyTable"); 
       
            for(var i=1;i<tbodyObj.rows.length;i++){
                var perNbParticipants = Number(tbodyObj.rows[i].cells[2].getElementsByTagName('input')[0].value);
                var userconsumption = perNbParticipants*actMoney;
                tbodyObj.rows[i].cells[3].getElementsByTagName('input')[0].value=Number(userconsumption);
            }

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
					<form action="doUpdateActDetailsLeaderviewAction" method="post"
						role="form" name="form" id="form">
						<input type="hidden" name="actId" value="${actId}"/>
						<div class="row form-group form-group-lg">

							<div class="col-xs-3 col-md-3">
								<strong>Activity name:</strong>
							</div>
							<div class="col-xs-3  col-md-3" name="actName">
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
							<div class="col-xs-3 col-md-3 input-group">
								<span class="input-group-addon"><s:text name="money"/></span>
								<input type="number" step="0.1" class="form-control" id="actMoney" name="actMoney"
									aria-label="..." value="${groupAct.activity.actMoney}"/>
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3">
								<strong>Number of participants:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								<input type="text" class="form-control" id="nbParticipants"
									name="nbParticipants" value='${groupAct.nbParticipants}'>
							</div>
							<div class="col-xs-3 col-md-3">
								<strong>Consumption:</strong>
							</div>
							<div class="col-xs-3 col-md-3 input-group">
								<span class="input-group-addon"><s:text name="money"/></span>
								<input type="number" step="0.1" class="form-control" id="sum" name="sum"
									aria-label="..." value="${groupAct.sum}"/>
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
						<div class="row form-group form-group-lg">
							<div class="col-xs-2  col-md-2 navbar-right ">
								<input type="button" class="btn btn-primary btn-block"
									value="auto generate" onclick="autoGenerate();"/>
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-2 col-md-2 text-left">
								<strong>Participants:</strong>
							</div>
							<div class="col-xs-10 col-md-10 display: block">
								<table class="table table-bordered" id="MyTable">

									<tr class="success">
										<th>Name</th>
										<th>Dept</th>
										<th>Number</th>
										<th>Consumption (<s:text name="money"/>)</td>
										<th>Remark</th>
									</tr>
									<c:set var="count" value="0"/>
									<s:iterator var="userAct" value="groupAct.memberInVO">
									<c:set var="count" value="${count+1}"/>
										<tr>
											<td>
												${userAct.user.userName}
											</td>
											<td>
												${userAct.user.userDept}
											</td>
											<td>
												<input type="number" class="form-control" id="nbParticipants"
									name="perNbParticipants_${count}" value="${userAct.nbParticipants}">
											</td>
											<td>
											    <input type="text" class="form-control" id="perconsumption"
									name="perconsumption_${count}" value="${userAct.consumption}">
												
											</td>
											<td><s:property value="remark"/></td>
										</tr>

									</s:iterator>
								</table>


							</div>

						</div>

						<div class="row form-group form-group-lg">
							<div
								class="col-xs-6 col-md-6 col-xs-offset-2 col-md-offset-2 text-right ">
								<h5>
									●Click <strong>submit</strong> to finish edit.
								</h5>
							</div>
							
							<c:if test="${groupAct.activity.state=='pending'}">
								<div class="col-xs-2 col-md-2">
									<input type="submit" class="btn btn-primary btn-block"
											value="Save" />
								</div>
								<div class="col-xs-2 col-md-2">
									<s:submit action="doToValidateActLeaderviewAction" value="Save and submit" class="btn btn-primary btn-block"/>
								</div>
							</c:if>

						</div>
						<div>
							<button type="button" class="btn btn-default navbar-btn"
								onclick=window.history.back();>
								<span class="glyphicon glyphicon-chevron-left"
									aria-hidden="true"></span> back
							</button>
						</div>
					</form>
				</div>
			</div>
			</div>
	</body>
</html>