<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>Edit activity details</title>

		<script src="js/jquery-2.2.0.min.js"></script>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">
		<script src="js/bootstrap.min.js"></script>
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
					<h3 class="panel-title">
						details
					</h3>

				</div>

				<div class="panel-body table-bordered">
					<form action="doUpdateActDetailsLeaderviewAction.action" method="post"
						role="form" name="form" id="form">
						<input type="hidden" name="actId" value="${actId}"/>
						<div class="row form-group form-group-lg">

							<div class="col-xs-3 col-md-3">
								<strong>ACTNAME:</strong>
							</div>
							<div class="col-xs-3  col-md-3" name="actName">
								${groupAct.actName}
							</div>
							<div class="col-xs-3  col-md-3">
								<strong> Registration Period:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${groupAct.daterange}
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3">
								<strong>DATE:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								${groupAct.actDate}
							</div>
							<div class="col-xs-3 col-md-3">
								<strong>PRICE per PERSON</strong>
							</div>
							<div class="col-xs-3 col-md-3">
							<input type="text" class="form-control" id="actMoney" name="actMoney" 
							value="${groupAct.actMoney}">
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3">
								<strong>NUMBER OF PARTICIPANTS:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								<input type="text" class="form-control" id="nbParticipants"
									name="nbParticipants" value='${groupAct.nbParticipants}'>
							</div>
							<div class="col-xs-3 col-md-3">
								<strong>CONSUMPTION:</strong>
							</div>
							<div class="col-xs-3 col-md-3">
								<input type="text" class="form-control" id="sum"
									name="sum" value="${groupAct.sum}">
							</div>
						</div>
						<div class="row form-group form-group-lg">
							<div class="col-xs-3 col-md-3 text-left">
								<strong>DESCRIPTION:</strong>
							</div>
							<div class="col-xs-8 col-md-8 display: block">
								<p class="desc">
									${groupAct.description}
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
								<strong>participants:</strong>
							</div>
							<div class="col-xs-10 col-md-10 display: block">
								<table class="table table-bordered" id="MyTable">

									<tr class="success">
										<td>
											Name
										</td>
										<td>
											Dept
										</td>
										<td>
											number
										</td>
										<td>
											consumption
										</td>
										<td>
											remark
										</td>
									</tr>
									<c:set var="count" value="0"/>
									<c:forEach var="member" items="${groupAct.memberInVO}">
									<c:set var="count" value="${count+1}"/>
										<tr>
											<td>
												${member.userName}
											</td>
											<td>
												${member.userDept}
											</td>
											<td>
												<input type="text" class="form-control" id="nbParticipants"
									name="perNbParticipants_${count}" value="${member.nbParticipants}">
											</td>
											<td>
											    <input type="text" class="form-control" id="perconsumption"
									name="perconsumption_${count}" value="${member.consumption}">
												
											</td>
											<td>
												${member.remark}
											</td>
										</tr>

									</c:forEach>
								</table>


							</div>

						</div>

						<div class="row form-group form-group-lg">
							<div
								class="col-xs-6  col-md-6 col-xs-offset-2 col-md-offset-2 text-right ">
								<h5>
									●Click <strong>submit</strong> to finish edit.
								</h5>
							</div>
							<div class="col-xs-2  col-md-2 navbar-right ">
								<c:if test="${groupAct.state=='pending'}">

									<input type="submit" class="btn btn-primary   btn-block"
										value="submit" />
								</c:if>
								<c:if test="${groupAct.state!='pending'}">

									<input type="submit" class="btn btn-primary   btn-block"
										value="submit" disabled />

								</c:if>

							</div>

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