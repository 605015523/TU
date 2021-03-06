<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
	        <jsp:param name="subtitle" value="Edit user"/>
	    </jsp:include>
	    
		<link rel="stylesheet" type="text/css"
			href="groupLeader/daterangepicker/daterangepicker.css" />
		
		<script src="groupLeader/daterangepicker/moment.min.js"></script>
		<script src="groupLeader/daterangepicker/daterangepicker.js"></script>
		
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
			
			
				<form class="panel-body" action="updateUserManageUsersAction" method="post" role="form" name="form" id="form" onsubmit="return checkinput();">
					<input type="hidden" name="userId" value="${userId}"/>
					
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
									<s:iterator value = "roles" >
									    <option value="<s:property value="key"/>" <c:if test="${user.userRole==key}">selected</c:if>>
									    	<s:property value="value.name"/>
									    </option>
									</s:iterator>
								</select>
							</div>
							
						</div>
	
						<div class="col-md-8 col-xs-8">
						
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
	
							
							<div class="form-group col-md-3 col-xs-3 navbar-right">
								<input type="submit" class="btn btn-primary btn-block"
									value="Update" />
							</div>
	
						</div>

					</div>
				</form>
				
				<div>
					<a href="<c:url value='/listAllManageUsersAction'/>" type="button"
									class="btn btn-default navbar-btn"><span
									class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
									back</a>
				</div>
			</div>
		</div>
	</body>
</html>