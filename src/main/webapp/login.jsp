<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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

		<title>SIGN IN</title>

		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">
	</head>

	<body background="images/login_bg_0.jpg">


		<div class="signin">
			<div class="signin-head">
				<div class="signin-head-logo">
					<img src="images/test/head.png" alt="">
				</div>
				<h1 class="signing-head-title">DGC Trade Union</h1>
			</div>
			
			<hr />
			<form class="form-signin" role="form" name="form" method="post"
				action="<c:url value='j_spring_security_check' />">
				<c:if test="${loginMessage!=null}">${loginMessage}</c:if>
				<!-- <s:textfield  name="userName"  cssClass="form-control1"   ></s:textfield>
        <s:password   name="userPassword" cssClass="form-control2" ></s:password>
        <s:submit cssClass="btn btn-lg btn-warning btn-block">µÇÂ¼</s:submit>  -->

				<div class="form-group">
					<label for="exampleInputName2">
						USERNAME
					</label>
					<input name="userName" type="text" class="form-control"
						id="exampleInputName2" placeholder="Username">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">
						PASSWORD
					</label>
					<input name="userPassword" type="password" class="form-control"
						id="exampleInputPassword1" placeholder="Password">
				</div>
				<label>
					LOGIN IN
				</label>
				<button type="submit" class="btn btn-primary form-control">
					Submit
				</button>

			</form>
	
		</div>
	</body>
</html>