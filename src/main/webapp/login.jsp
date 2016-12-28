<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="common_header.inc.jsp">
	        <jsp:param name="subtitle" value="Sign in"/>
	    </jsp:include>
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
					LOG IN
				</label>
				<button type="submit" class="btn btn-primary form-control">
					Submit
				</button>

			</form>
	
		</div>
	</body>
</html>