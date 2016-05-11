<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台管理-功能树</title>
<style type="text/css">
<!--
body {
	background-color: #BCC4D1;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="../css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
//控制动态菜单
function displayTable(name){
   var names=new Array("hr","person","dept","position","pay","paylist","team");
   for(var i=0;i<names.length;i++){
      if(document.all[names[i]]!=null){
        if(name==names[i]){
          document.all[names[i]].style.display="";
        }else{
          document.all[names[i]].style.display="none";
        }
      }
   }
}
</script>
</head>

<body>
<table width="208" border="0" align="left" cellpadding="0" cellspacing="0"  >
  <tr>
    <td width="208" valign="top" bgcolor="BCC4D1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top" align="center"><font color="red"><b><%="用户:"+session.getAttribute("userName")%></b></font></td>
        </tr>
        <tr>
          <td valign="top">
            <table width="199" border="0" align="center" cellpadding="0" cellspacing="0">

<tr>
                <td width="199" height="43" valign="middle" background="images/n-1.png">
                  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%"><img src="images/n-4.png" width="14" height="16" /></td>
                      <td width="75%" class="font4" onClick="displayTable('hr')">管理员管理</td>
                    </tr>                  
                  </table>
                </td>
              </tr>
              
              <tr>
                <td id="hr" style="display:none">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    
                    <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../hr/hrAdd.jsp" target="content">新增管理员</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../hr/doShowAllHrInfoFirstPageHrAction.action" target="content">浏览管理员</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../hr/hrQuery.jsp" target="content">查询管理员</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  
                  </table>
                </td>
              </tr> 
              
               <tr>
                <td width="199" height="43" valign="middle" background="images/n-1.png">
                  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%"><img src="images/n-4.png" width="14" height="16" /></td>
                      <td width="75%" class="font4" onClick="displayTable('person')">员工管理</td>
                    </tr>                  
                  </table>
                </td>
              </tr>
              
              <tr>
                <td id="person" style="display:none">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    
                    <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../person/goAddPersonInfoPersonAction.action" target="content">新增员工</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../person/doShowAllPersonInfoFirstPagePersonAction.action" target="content">浏览员工</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../person/personQuery.jsp" target="content">查询员工</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  
                  </table>
                </td>
              </tr> 
 
 
 
 
               <tr>
                <td width="199" height="43" valign="middle" background="images/n-1.png">
                  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%"><img src="images/n-4.png" width="14" height="16" /></td>
                      <td width="75%" class="font4" onClick="displayTable('dept')">部门管理</td>
                    </tr>                  
                  </table>
                </td>
              </tr>
              
              <tr>
                <td id="dept" style="display:none">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    
                    <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../dept/deptAdd.jsp" target="content">新增部门</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../dept/doShowAllDeptInfoFirstPageDeptAction.action" target="content">浏览部门</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../dept/deptQuery.jsp" target="content">查询部门</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  
                  </table>
                </td>
              </tr> 
 
              <tr>
                <td width="199" height="43" valign="middle" background="images/n-1.png">
                  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%"><img src="images/n-4.png" width="14" height="16" /></td>
                      <td width="75%" class="font4" onClick="displayTable('position')">职位管理</td>
                    </tr>                  
                  </table>
                </td>
              </tr>
              
              <tr>
                <td id="position" style="display:none">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    
                    <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../position/positionAdd.jsp" target="content">新增职位</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../position/doShowAllPositionInfoFirstPagePositionAction.action" target="content">浏览职位</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../position/positionQuery.jsp" target="content">查询职位</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  
                  </table>
                </td>
              </tr> 
              
              
              
              <tr>
                <td width="199" height="43" valign="middle" background="images/n-1.png">
                  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%"><img src="images/n-4.png" width="14" height="16" /></td>
                      <td width="75%" class="font4" onClick="displayTable('pay')">工资单管理</td>
                    </tr>                  
                  </table>
                </td>
              </tr>
              
              <tr>
                <td id="pay" style="display:none">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    
                    <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../pay/goAddPayInfoPayAction.action" target="content">新增工资单</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../pay/doShowAllPayInfoFirstPagePayAction.action" target="content">浏览工资单</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../pay/payQuery.jsp" target="content">查询工资单</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  
                  </table>
                </td>
              </tr> 

<tr>
                <td width="199" height="43" valign="middle" background="images/n-1.png">
                  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%"><img src="images/n-4.png" width="14" height="16" /></td>
                      <td width="75%" class="font4" onClick="displayTable('paylist')">工资单明细管理</td>
                    </tr>                  
                  </table>
                </td>
              </tr>
              
              <tr>
                <td id="paylist" style="display:none">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    
                    <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../paylist/paylistAdd.jsp" target="content">新增工资单明细</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../paylist/doShowAllPaylistInfoFirstPagePaylistAction.action" target="content">浏览工资单明细</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../paylist/paylistQuery.jsp" target="content">查询工资单明细</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  
                  </table>
                </td>
              </tr> 


<tr>
                <td width="199" height="43" valign="middle" background="images/n-1.png">
                  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%"><img src="images/n-4.png" width="14" height="16" /></td>
                      <td width="75%" class="font4" onClick="displayTable('team')">小组管理</td>
                    </tr>                  
                  </table>
                </td>
              </tr>
              
              <tr>
                <td id="team" style="display:none">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    
                    <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../team/teamAdd.jsp" target="content">新增小组</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../team/doShowAllTeamInfoFirstPageTeamAction.action" target="content">浏览小组</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../team/teamQuery.jsp" target="content">查询小组</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>

            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
