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
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<title>Password update</title>

		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">

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