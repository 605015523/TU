<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="Create user"/>
	    </jsp:include>
	    
		<link rel="stylesheet" type="text/css"
			href="groupLeader/daterangepicker/daterangepicker.css" />
		
		<script src="groupLeader/daterangepicker/moment.min.js"></script>
		<script src="groupLeader/daterangepicker/daterangepicker.js"></script>
		
		<script type="text/javascript">
		$(document).ready(function() {     
       
	        $('#inDate').daterangepicker({
	          singleDatePicker: true,
	          startDate: moment()
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
						Create user
					</h3>
				</div>
			
			
				<form class="panel-body" action="createUserManageUsersAction.action" method="post" role="form" name="form" id="form" onsubmit="return checkinput();">
					<div class="row">
	
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label for="userName">
									Username
								</label>
								<input type="text" class="form-control" id="userName" name="userName" value=""
									placeholder="Username for login"/>
							</div>
	
							<div class="form-group">
								<label for="password">
									Password
								</label>
							
								<input type="password" class="form-control" id="password" name="password" value=""
									placeholder="Password"/>
							</div>
	
							<div class="form-group">
								<label for="repassword">
									Repeat password
								</label>
							
								<input type="password" class="form-control" id="repassword" name="repassword" value=""
									placeholder="Retype password"/>
							</div>
							
							<div class="form-group">
								<label for="role">
									Role
								</label>
							
								<select class="form-control" name="role">
									<s:iterator value = "roles" >
									    <option value="<s:property value="key"/>"><s:property value="value.name"/></option>
									</s:iterator>
								</select>
							</div>
						</div>
	
						<div class="col-md-8 col-xs-8 ">
						
							<div class="form-group">
								<label for="dept">
									Department
								</label>
								<input type="text" class="form-control" id="dept" name="dept" value=""
									placeholder="Department"/>
							</div>
						
							<div class="form-group">
								<label for="inDate">
									In date
								</label>
								<input type="text" class="form-control" id="inDate" name="inDate" value=""
									placeholder="In date"/>
								
							</div>
	
							<div class="form-group">
								<label for="quota">
									Quota
								</label>
								<div class="input-group">
									<span class="input-group-addon"><s:text name="money"/></span>
									<input type="number" step="0.1" class="form-control" id="quota" name="quota"
										aria-label="..." value="2000"/>
								</div>
							</div>
						
							
							<hr class="invisible" />
	
	
							<div class="form-group col-md-3 col-xs-3  navbar-right">
								<input type="submit" class="btn btn-primary   btn-block"
									value="Create" />
							</div>
	
						</div>
	
					</div>
				</form>
			</div>
		</div>
	</body>
</html>