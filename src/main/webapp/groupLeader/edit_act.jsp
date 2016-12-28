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
		<title>Edit activity</title>

		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css"
			href="groupLeader/daterangepicker/daterangepicker.css" />

		<script src="js/jquery-2.2.0.min.js"></script>
		<script src="groupLeader/daterangepicker/moment.min.js"></script>
		<script src="groupLeader/daterangepicker/daterangepicker.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<style type="text/css">
.demo {
	position: relative;
}

.demo i {
	position: absolute;
	bottom: 10px;
	right: 24px;
	top: auto;
	cursor: pointer;
}
</style>
		<script type="text/javascript">
      $(document).ready(function() {     
       
         $('#actDate').daterangepicker({
          singleDatePicker: true,
        });
        
        $('.demo i').click(function() {
          $(this).parent().find('input').click();
        });

        updateConfig();

        function updateConfig() {
          var options = {};
        
          $('#daterange').daterangepicker(options, function(start, end, label) 
          { console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')'); });
          
        }

      });
      
       function wordStatic(input) {
        if (input) {
        
            // 获取输入内容长度并更新到界面
            var value = input.value;  
                     
            // 将换行符，前后空格不计算为单词数
            value = value.replace(/\n|\r|^\s+|\s+$/gi,"");
            
            // 多个空格替换成一个空格
            value = value.replace(/\s+/gi," ");
            
            // 更新计数
            var length = 0;
            var match = value.match(/\s/g);
            if (match) {
                length = match.length + 1;
            } else if (value) {
                length = 1;
            }
            if (length>=1000){
                input.value = value;
            }
        }
    }
    
    function checkinput(){
    var actname;
    var actmoney;
    var actdate;
    var actdaterange;
 
    actname=document.getElementById("actName").value;
    actmoney=document.getElementById("actMoney").value;
    actdate=document.getElementById("actDate").value;
    actdaterange=document.getElementById("daterange").value;
    
    if (actname== "") {
      alert("the act name can not empty!");
      return false;
    }
    
    if (actmoney== "") {
      alert("the Price / person cannot be empty!");
      return false;
    }
    
    if ( actdate== "") {
      alert("the activity date cannot be empty!");
      return false;
    }
    
    if (actdaterange== "") {
      alert("the registration date range can not empty!");
      return false;
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

			<div class="well configurator">

				<form action="doUpdateActLeaderviewAction" method="post"
					role="form" name="form" id="form" onsubmit="return checkinput();">
					<input type="hidden" name="actId" value="${actId}"/>
					<div class="row">
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label for="groupName">
									Group
								</label>
								<input type="text" class="form-control" id="groupName"
									name="groupName" value="" placeholder="${groupAct.activity.groupName}"
									disabled>
							</div>

							<div class="form-group">
								<label for="actDate">
									Activity date
								</label>
								<c:if test="${groupAct.activity.state=='approved'}">
									<input type="text" class="form-control" id="actDate"
										name="actDate" value="<s:date name='groupAct.activity.actDate' format='MM/dd/yyyy'/>"
										disabled>
								</c:if>
								<c:if test="${groupAct.activity.state=='draft'||groupAct.activity.state=='disapproved'}">
									<input type="text" class="form-control" id="actDate"
										name="actDate" value="<s:date name='groupAct.activity.actDate' format='MM/dd/yyyy'/>" placeholder="ActDate"/>
								</c:if>

							</div>


							<div class="form-group demo">
								<label for="daterange">
									Registration date
								</label>
								<c:if test="${groupAct.activity.state=='approved'}">
									<input type="text" id="daterange" name="daterange" value=""
										placeholder="<s:date name='groupAct.activity.enrollStartDate' format="MM/dd/yyyy"/> - <s:date name='groupAct.activity.enrollEndDate' format="MM/dd/yyyy"/>" class="form-control" disabled>
									<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
								</c:if>
								<c:if test="${groupAct.activity.state=='draft'||groupAct.activity.state=='disapproved'}">
									<input type="text" id="daterange" name="daterange"
										value="<s:date name='groupAct.activity.enrollStartDate' format="MM/dd/yyyy"/> - <s:date name='groupAct.activity.enrollEndDate' format="MM/dd/yyyy"/>" class="form-control">
									<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>

								</c:if>
							</div>


							<div class="form-group">
								<label for="actMoney">
									Price / person
								</label>
								<div class="input-group">
									<span class="input-group-addon"><s:text name="money"/></span>
									<c:if test="${groupAct.activity.state=='approved'}">
										<input type="number" step="0.1" class="form-control"
											id="actMoney" name="actMoney" value="${groupAct.activity.actMoney}"
											placeholder="${groupAct.activity.actMoney}" aria-label="..." disabled>
									</c:if>
									<c:if test="${groupAct.activity.state=='draft'||groupAct.activity.state=='disapproved'}">
										<input type="number" step="0.1" class="form-control"
											id="actMoney" name="actMoney" value="${groupAct.activity.actMoney}"
											aria-label="...">

									</c:if>
								</div>
							</div>
						</div>

						<div class="col-md-8 col-xs-8 ">
							<div class="form-group">
								<label for="actName">
									Activity name
								</label>
								<c:if test="${groupAct.activity.state=='approved'}">
									<input type="text" class="form-control" id="actName"
										name="actName" value="" placeholder="${groupAct.activity.actName}" disabled>
								</c:if>
								<c:if test="${groupAct.activity.state=='draft'||groupAct.activity.state=='disapproved'}">
									<input type="text" class="form-control" id="actName"
										name="actName" value="${groupAct.activity.actName}" placeholder="act name">
								</c:if>
							</div>

							<div class="form-group">
								<label for="description">
									Activity description
								</label>
								
								<c:if test="${groupAct.activity.state=='approved'}">
									<textarea class="form-control" rows="6" id="description"
										name="description" onkeyup="wordStatic(this);" disabled>${groupAct.activity.description}</textarea>

								</c:if>
								<c:if test="${groupAct.activity.state=='draft'||groupAct.activity.state=='disapproved'}">
									<textarea class="form-control" rows="6" id="description"
										name="description" onkeyup="wordStatic(this);">${groupAct.activity.description}</textarea>
								</c:if>

							</div>
							<hr class="invisible" />

						</div>

					</div>
					<div class="row form-group">
					<c:if test="${groupAct.activity.state=='disapproved'}">
						<div class="form-group col-md-12 col-xs-12">
							<label for="comment">
								Comment:
							</label>				
							<c:if test="${groupAct.activity.state=='draft'||groupAct.activity.state=='disapproved'}">
								<textarea class="form-control" rows="6" id="comment"
									name="comment" disabled>${groupAct.activity.comment}</textarea>
							</c:if>
						</div>
					</c:if>
						<div class="form-group col-md-6 col-xs-6">
							<h5>
								●Click
								<strong>submit</strong>,you will send an act to approval group
								to check.
								<br />
								●Click
								<strong>publish</strong>,you will send an act to all your group
								member.
								<br />
							</h5>
						</div>
						<c:if test="${groupAct.activity.state=='draft'||groupAct.activity.state=='disapproved'}">
							<div class="form-group col-md-2 col-xs-3">
								<input type="submit" class="btn btn-primary btn-block"
									value="Save as draft" />
							</div>
							<div class="form-group col-md-2 col-xs-3">
								<s:submit class="btn btn-primary btn-block" value="Save and submit" action="doUpdateAndSubmitActLeaderviewAction"/>
							</div>
						</c:if>
						
						<div class="form-group col-md-2 col-xs-3">
							<c:if test="${groupAct.activity.state=='approved' || groupAct.activity.state=='modified'}">
								<a role="button" class="btn btn-primary   btn-block"
									href='<c:url value="/doPublishActLeaderviewAction.action" />?actId=${groupAct.activity.actId}'>publish</a>
							</c:if>
							<c:if test="${groupAct.activity.state!='approved' && groupAct.activity.state!='modified'}">
								<a role="button" class="btn btn-primary   btn-block"
									href='<c:url value="/doPublishActLeaderviewAction.action" />?actId=${groupAct.activity.actId}'
									disabled>publish</a>
							</c:if>
						</div>


					</div>
				</form>

			</div>

			<div>
				<button type="button" class="btn btn-default navbar-btn"
					onclick=window.history.back();>
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					back
				</button>
			</div>
		</div>
	</body>
</html>