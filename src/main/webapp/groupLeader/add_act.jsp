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
		<title>Add a new activity</title>

		<link href="css/signin.css" rel="stylesheet">
		<link href="css/bootstrap.min.css" rel="stylesheet">
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
          startDate: moment()
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
                input.value=value;
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


			<form action="doAddActLeaderviewAction.action" method="post"  role="form" name="form" id="form" onsubmit="return checkinput();">
				<div class="row">

					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label for="groupName">
								Group
							</label>
							<input type="text" class="form-control" id="groupName" name="groupName" value=""
								placeholder="${group.groupName}" disabled>
						</div>

						<div class="form-group">
							<label for="actDate">
								Act Date
							</label>
							<input type="text" class="form-control" id="actDate" name="actDate" value=""
								placeholder="Act Date">
							
						</div>


						<div class="form-group demo">
							<label for="daterange">
								Registration Date
							</label>
							<input type="text" id="daterange" name="daterange" class="form-control">
							<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
						</div>


						<div class="form-group">
							<label for="actMoney">
								Price / person
							</label>
							<div class="input-group">
								<span class="input-group-addon">￥</span>
								<input type="number" step="0.1" class="form-control" id="actMoney" name="actMoney"
									aria-label="...">
							</div>
						</div>
					</div>

					<div class="col-md-8 col-xs-8 ">
						<div class="form-group">
							<label for="actName">
								Act Name
							</label>
							<input type="text" class="form-control" id="actName" name="actName" value=""
								placeholder="act name">
						</div>

						<div class="form-group">
							<label for="description">
								Act Description
							</label>
						
							<textarea class="form-control" rows="6" id="description" name="description" 
								placeholder="less than 500 words" onkeyup="wordStatic(this);"></textarea>

						</div>
						<hr class="invisible" />

						<div class="form-group col-md-8 col-xs-8">
							<h5>
								Click
								<strong>submit</strong> then you will send it to approval group.
							</h5>
						</div>

						<div class="form-group col-md-3 col-xs-3  navbar-right">
							<input type="submit" class="btn btn-primary   btn-block"
								value="submit" />
						</div>

					</div>

				</div>
			</form>

		</div>

	</body>
</html>