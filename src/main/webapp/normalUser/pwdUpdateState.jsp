<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
			<jsp:param name="subtitle" value="Password update"/>
		</jsp:include>
	</head>

	<body>

		<div class="container">
			<div class="signin-head">
				<img src="images/test/head.png" alt="">
			</div>
			<hr class="invisible" />
			<div class="panel panel-primary" style="height: 400px">
				<div class="panel-heading">
					<h3 class="panel-title">
						Change Password
					</h3>
				</div>

				<hr class="invisible" />
				<div class="panel-body">
					<h4 class="text-center" style="height: 200px">
						${updateMessage}
					</h4>

					<div>
						<a href="normalUser/user_home.jsp" type="button"
							class="btn btn-default navbar-btn"><span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							back</a>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>