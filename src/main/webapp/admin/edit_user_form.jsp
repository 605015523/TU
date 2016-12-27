<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<title>Edit user</title>


		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css"
			href="groupLeader/daterangepicker/daterangepicker.css" />

		
		<script src="js/jquery-2.2.0.min.js"></script>
		<script src="groupLeader/daterangepicker/moment.min.js"></script>
		<script src="groupLeader/daterangepicker/daterangepicker.js"></script>
		<script src="js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
		$(document).ready(function() {     
       
	        $('#inDate').daterangepicker({
	          singleDatePicker: true
	        });
		});
		
		$(document).ready(function() {     
		       
	        $('#outDate').daterangepicker({
	          singleDatePicker: true,
	          autoUpdateInput: false,
	          locale: {
		          cancelLabel: 'Clear'
			  }
	        });
		});
         </script>
	</head>
	<body>

		<div class="container">
			<div class="signin-head">
				<img src="images/test/head.png" alt="">
			</div>
			<jsp:include page="../menu.jsp">
		        <jsp:param name="active" value="admin"/>
		    </jsp:include>
			
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Edit user
					</h3>
				</div>
			
			
				<form class="panel-body" action="updateUserManageUsersAction.action" method="post" role="form" name="form" id="form" onsubmit="return checkinput();">
					<div class="row">
	
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label for="userName">
									Username
								</label>
								<input type="text" class="form-control" id="userName" name="userName" value="${user.userName}"
									placeholder="Username for login" disabled/>
							</div>
	
							<div class="form-group">
								<label for="role">
									Role
								</label>
							
								<select class="form-control" name="role">
									<option value="1">Group leader</option>
									<option value="2">Accounting</option>
									<option value="3" selected="selected">Normal member</option>
									<option value="4">IT admin</option>
								</select>
							</div>
						</div>
	
						<div class="col-md-8 col-xs-8 ">
						
							<div class="form-group">
								<label for="dept">
									Department
								</label>
								<input type="text" class="form-control" id="dept" name="dept" value="${user.userDept}"
									placeholder="Department"/>
							</div>
						
							<div class="form-group">
								<label for="inDate">
									In date
								</label>
								<input type="text" class="form-control" id="inDate" name="inDate" value="<s:date name='user.inDate' format="MM/dd/yyyy"/>"/>
							</div>
							
							<div class="form-group">
								<label for="outDate">
									Out date
								</label>
								<input type="text" class="form-control" id="outDate" name="outDate" value="${user.outDate}"/>
							</div>
	
							<div class="form-group">
								<label for="quota">
									Quota
								</label>
								<div class="input-group">
									<span class="input-group-addon"><s:text name="money"/></span>
									<input type="number" step="0.1" class="form-control" id="quota" name="quota"
										aria-label="..." value="${user.quota}"/>
								</div>
							</div>
						
							
							<hr class="invisible" />
	
	
							<div class="form-group col-md-3 col-xs-3  navbar-right">
								<input type="submit" class="btn btn-primary   btn-block"
									value="Update" />
							</div>
	
						</div>
	
					</div>
				</form>
			</div>
		</div>
	</body>
</html>