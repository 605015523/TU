<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>��̨����-������</title>
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
//���ƶ�̬�˵�
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
          <td valign="top" align="center"><font color="red"><b><%="�û�:"+session.getAttribute("userName")%></b></font></td>
        </tr>
        <tr>
          <td valign="top">
            <table width="199" border="0" align="center" cellpadding="0" cellspacing="0">

<tr>
                <td width="199" height="43" valign="middle" background="images/n-1.png">
                  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%"><img src="images/n-4.png" width="14" height="16" /></td>
                      <td width="75%" class="font4" onClick="displayTable('hr')">����Ա����</td>
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
                            <td width="75%" class="font3"><a href="../hr/hrAdd.jsp" target="content">��������Ա</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../hr/doShowAllHrInfoFirstPageHrAction.action" target="content">�������Ա</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../hr/hrQuery.jsp" target="content">��ѯ����Ա</a></td>
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
                      <td width="75%" class="font4" onClick="displayTable('person')">Ա������</td>
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
                            <td width="75%" class="font3"><a href="../person/goAddPersonInfoPersonAction.action" target="content">����Ա��</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../person/doShowAllPersonInfoFirstPagePersonAction.action" target="content">���Ա��</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../person/personQuery.jsp" target="content">��ѯԱ��</a></td>
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
                      <td width="75%" class="font4" onClick="displayTable('dept')">���Ź���</td>
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
                            <td width="75%" class="font3"><a href="../dept/deptAdd.jsp" target="content">��������</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../dept/doShowAllDeptInfoFirstPageDeptAction.action" target="content">�������</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../dept/deptQuery.jsp" target="content">��ѯ����</a></td>
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
                      <td width="75%" class="font4" onClick="displayTable('position')">ְλ����</td>
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
                            <td width="75%" class="font3"><a href="../position/positionAdd.jsp" target="content">����ְλ</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../position/doShowAllPositionInfoFirstPagePositionAction.action" target="content">���ְλ</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../position/positionQuery.jsp" target="content">��ѯְλ</a></td>
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
                      <td width="75%" class="font4" onClick="displayTable('pay')">���ʵ�����</td>
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
                            <td width="75%" class="font3"><a href="../pay/goAddPayInfoPayAction.action" target="content">�������ʵ�</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../pay/doShowAllPayInfoFirstPagePayAction.action" target="content">������ʵ�</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../pay/payQuery.jsp" target="content">��ѯ���ʵ�</a></td>
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
                      <td width="75%" class="font4" onClick="displayTable('paylist')">���ʵ���ϸ����</td>
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
                            <td width="75%" class="font3"><a href="../paylist/paylistAdd.jsp" target="content">�������ʵ���ϸ</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../paylist/doShowAllPaylistInfoFirstPagePaylistAction.action" target="content">������ʵ���ϸ</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../paylist/paylistQuery.jsp" target="content">��ѯ���ʵ���ϸ</a></td>
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
                      <td width="75%" class="font4" onClick="displayTable('team')">С�����</td>
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
                            <td width="75%" class="font3"><a href="../team/teamAdd.jsp" target="content">����С��</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                 <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../team/doShowAllTeamInfoFirstPageTeamAction.action" target="content">���С��</a></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                   <tr>
                      <td height="34" valign="middle" background="images/n-2.png" >
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="25%"><img src="images/n-3.png" width="14" height="11" /></td>
                            <td width="75%" class="font3"><a href="../team/teamQuery.jsp" target="content">��ѯС��</a></td>
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
