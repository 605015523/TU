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

		<title>Change password</title>

		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">
		<script language="JavaScript" type="text/javascript">
  function checkpassword() {
    var orgPwd;
    var oldPwd;
    var newPwd;
    var conPwd;
    oldPwd = document.getElementById("oldPassword").value;
    newPwd = document.getElementById("newPassword").value;
    conPwd = document.getElementById("conPassword").value;

    if (oldPwd == ""|| newPwd == "" || conPwd == "") {
      alert("the input box can not empty!");
      return false;
    }
 
    if(newPwd!=conPwd){
          alert("The new passwords you entered must be the same");
          return false;
        }
           
    if(newPwd.length<6||newPwd.length>12) {
           alert("the new password should between 6-12 characters!");
           return false;
    }
        window.location.href="<c:url value='/doChangePwdUserviewAction.action?newpassword="+newPwd +"&oldpassword="+oldPwd+"' />";
  }
</script>
	</head>

	<body>
		<div class="container">
			<div class="signin-head">
				<img src="images/test/head.png" alt="">
			</div>
			<hr class="invisible" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Change Password
					</h3>

				</div>

				<hr class="invisible" />
				<div class="panel-body">
					<form method="post"	style="max-width: 330px; padding: 15px; margin: auto; background-color: #bdbdbd">
						<c:if test="${updateMessage!=null}">${updateMessage}</c:if>

						<div class="form-group">
							<label>
								Old Password:
							</label>
							<input type="password" class="form-control" id="oldPassword">
						</div>
						<div class="form-group">

							<label>
								New Password:
							</label>
							<input type="password" class="form-control" id="newPassword"
								placeholder="6-12 characters">
						</div>
						<div class="form-group">

							<label>
								Confirm Password:
							</label>
							<input type="password" class="form-control" id="conPassword"
								placeholder="6-12 characters">
						</div>

						<input type="button" class="btn btn-primary form-control"
							value="submit" onclick="checkpassword();" />

					</form>
					
					<div>
						<a href="<c:url value='/displayHomePageUserloginManageAction'/>" type="button"
							class="btn btn-default navbar-btn"><span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							back</a>
					</div>

				</div>
			</div>
		</div>
	</body>

</html>