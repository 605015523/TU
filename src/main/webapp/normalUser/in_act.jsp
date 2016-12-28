<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common_header.inc.jsp">
			<jsp:param name="subtitle" value="Registration"/>
		</jsp:include>

		<link rel="stylesheet" type="text/css"
			href="groupLeader/daterangepicker/daterangepicker.css" />

		<script src="groupLeader/daterangepicker/moment.min.js"></script>
		<script src="groupLeader/daterangepicker/daterangepicker.js"></script>
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
		   
    function wordStatic(input) {
        var el = document.getElementById('description');
        if (el && input) {
        
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
            el.innerText = length;
            
            if (el.innerText>=49){
            	input.value=value;
            }
        }
    }
    
    function checkinput() {
        var nbParticipants = document.getElementById("nbParticipants").value;
        //var actmoney = <s:property value="msgDetails.message.activity.actMoney"/>
        //var remaining = <s:property value="userview.remaining"/>;

        if (nbParticipants=="") {
            alert("the number of participants cannot be empty!");
            return false;
        }
 
        var truthBeTold = window.confirm("Be careful, the consumption may be over the remaining!");
	    if (truthBeTold){
            return true;
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
		        <jsp:param name="active" value="activities"/>
		    </jsp:include>
			<hr class="invisible" />


			<form action="doActRequestUserviewAction.action" method="post"
				role="form" name="form" id="form" onsubmit="return checkinput();">
				<input type="hidden" name="actId" value="${activity.actId}"/>
				<div class="row">

					<div class="col-md-4 col-xs-4 well">
						<div class="form-group">
							<label>
								Act Name:
							</label>
							${activity.actName}
						</div>
						<div class="form-group">
							<label>
								Group:
							</label>
							${activity.groupName}
						</div>

						<div class="form-group">
							<label>
								Act Date:
							</label>
							<s:date name="activity.actDate" format="%{getText('format.date')}"/>
						</div>
						<div class="form-group">
							<label>
								Registration Date:
							</label>
							<s:date name="activity.enrollStartDate" format="%{getText('format.date')}"/> - <s:date name="activity.enrollEndDate" format="%{getText('format.date')}"/>
						</div>

						<div class="form-group">
							<label>
								Price / person:
							</label>
							<h5 id="actmoney">
								<s:text name="format.money">
									<s:param value="activity.actMoney"/>
								</s:text>
							</h5>
						</div>
						<div class="form-group">
							<label>
								Act Description:
							</label>
							${activity.description}

						</div>
					</div>

					<div class="col-md-8 col-xs-8 ">
						<div class="form-group col-md-6 col-xs-6 ">
							Quota:
							<div class="text-success">
								<s:text name="format.money">
									<strong><s:param value="userview.user.quota"/></strong>
								</s:text>
							</div>
						</div>
						<div class="form-group col-md-6 col-xs-6 ">
							Remaining:
							<div class="text-danger">
								<s:text name="format.money">
									<span id="remaining"><s:param value="userview.remaining"/></span>
								</s:text>
							</div>
						</div>
						<div class="form-group col-md-6 col-xs-6 ">
							<label for="nbParticipants">
								number of participants:
							</label>
							<input type="number" step="1" class="form-control"
								id="nbParticipants" name="nbParticipants">
						</div>

						<div class="form-group ">
							<h5>
								<strong>Warning:</strong>the consumption should not be over the
								Remaining.
							</h5>
						</div>


						<div class="form-group">
							<label for="remark">
								Remark:
							</label>
							<textarea class="form-control" rows="6" id="remark" name="remark"
								placeholder="less than 50 words" onkeyup="wordStatic(this);"></textarea>

						</div>
	               

						<hr class="invisible" />

						<div class="form-group col-md-8 col-xs-8">
							<h5>
								●Click
								<strong>submit</strong> you will send it to the group leader.
							</h5>
							<h5>
								●You can change or delete it at any time of
								<strong> Registration Date</strong>.
							</h5>
						</div>

						<div class="form-group col-md-3 col-xs-4 navbar-right">
							<input type="submit" class="btn btn-primary btn-block"
								value="submit" />
						</div>

				    </div>

				</div>
			</form>

		</div>

	</body>
</html>