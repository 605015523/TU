<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%	
	String contextName = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextName;
	String currentPath=basePath+"/authorManage/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/Struts2SystemManage.dwt" codeOutsideHTMLIsLocked="false" --> 
<head> 
<base href="<%=currentPath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Struts2框架技术的后台管理</title>
<script language="JavaScript" type="text/javascript">	
	function fastGoTOTargetPage(){	
		var someOnePage;
		var totalPages;
		totalPages=${onePageStateVO_Prototype.lastPageNumber};
		someOnePage=document.getElementById("someOnePage").value;
		if((someOnePage=="")||(someOnePage.length==0)){
			 alert("请输入要转向的目标页码！");
			 return false;
		}
		if(someOnePage=="0"){
			 alert("要转向的目标页码不应该为0！");
			 return false;
		}
		var indexOne,indexTwo,strTemp;
  		strTemp = "1234567890";
		for(indexOne = 0; indexOne<someOnePage.length; indexOne++){
   			indexTwo = strTemp.indexOf(someOnePage.charAt(indexOne));
			if(indexTwo == -1){
				alert("要转向的目标页码应该为数字！"); //输入中含有非法字符
				break;
				return false;
			}
		}
		if(parseInt(someOnePage,10) >parseInt(totalPages,10) )
		{
			alert("要转向的目标页码应该小于最大页数"+totalPages+"！"); 
			return false;
		}
	    window.location.href="<c:url value='/doShowAuthorQueryTargetPageItemManageAction.action?targetPage="+someOnePage+"' />";
	}
	
function goAuthorUpdate(){
    var str=document.getElementsByName("checkBox");
    var l=str.length
    var i=0;
    var authorSelectedId;
    for(j=0;j<l;j++){
       if(str[j].checked==true){
           authorSelectedId=str[j].value;
           i++;
       }
    }
    if(i>1){
    alert("选中个数不能多于一个");
    return false;
    }
    else if(i==0){
      alert("请选择复选框");
    return false;
    }
    window.location.href="<c:url value='/goAuthorUpdateItemManageAction.action?authorSelectedId="+ authorSelectedId +"&currentPageNumber="+${currentPageNumber}+"' />";
  }	
  function goAuthorRelatedInfo(){
    var str=document.getElementsByName("checkBox");
    var l=str.length
    var i=0;
    var authorSelectedID;
    for(j=0;j<l;j++){
      if(str[j].checked==true){
        authorSelectedID=str[j].value;
        i++;
      }
    }
    if(i>1){
      alert("选中个数不能多于一个");
      return false;
    }
    else if(i==0){
      alert("请选择复选框");
      return false;
    }
    window.open("<c:url value='doShowAuthorRelatedInfoItemQueryAction.action?authorId="+authorSelectedID+"'/>",'_blank');
  }
</script>
</head>
<body onkeydown="if(event.keyCode==27) return false;">
<% 
  //request.setAttribute("authorQueriedIDs",request.getAttribute("authorQueriedIDs"));
  session.setAttribute("authorQueriedIDs",request.getAttribute("authorQueriedIDs"));
  session.setAttribute("currentPageNumber",request.getAttribute("currentPageNumber"));
%>  
<s:form action="doAuthorDeleteItemManageAction.action" method="post">
 <table width="768" border="1">    
  <tr> 
	<td colspan="10" align="center">查询到的作者信息 </td>
  </tr>
	<tr>
	<td colspan="10"><div align="center">
	 查询结果共有[${authorQueriedSets}]条记录&nbsp;
	 共[${onePageStateVO_Prototype.lastPageNumber}]页&nbsp;
	当前是第[${currentPageNumber}]页&nbsp;
	<input type="button" value="跳转到" onclick="fastGoTOTargetPage();"/>
	<input type="text" name="someOnePage" value='${onePageStateVO_Prototype.thisPageNumber}' size="2"/>页&nbsp;
	<c:if test="${onePageStateVO_Prototype.firstPage==false}">
	<a href='<c:url value="/doShowAuthorQueryTargetPageItemManageAction.action" />?targetPage=1'>首页</a>&nbsp;
	</c:if>
	<c:if test="${onePageStateVO_Prototype.hasPreviousPage==true}">
	<a href='<c:url value="/doShowAuthorQueryTargetPageItemManageAction.action" />?targetPage=${onePageStateVO_Prototype.previousPageNumber}'>前一页</a>&nbsp;
	</c:if>
	<c:if test="${onePageStateVO_Prototype.hasNextPage==true}">
	<a href='<c:url value="/doShowAuthorQueryTargetPageItemManageAction.action" />?targetPage=${onePageStateVO_Prototype.nextPageNumber}'>下一页</a>&nbsp;
	</c:if>
	<c:if test="${onePageStateVO_Prototype.lastPage==false}">
	<a href='<c:url value="/doShowAuthorQueryTargetPageItemManageAction.action" />?targetPage=${onePageStateVO_Prototype.lastPageNumber}'>尾页</a>
	</c:if>
	</div>	
	</td>
  </tr>    
  <tr> 
   <td width="20">&nbsp;</td>
   <td width="20">选项编号</td>
   <td width="60">选项名称</td>
   <td width="20">选项内容</td>
   <td width="20">题目编号</td>
   <td width="150">选项分数</td>
  </tr>
  <c:forEach items="${authorQueryPageVOArrayList}" var="item">
  <tr>
   <td><input type="checkbox" name="checkBox" value="${item.itemId}"/></td>
   <td>${item.itemId}</td>
   <td><input type="text" value="${item.name}" size="12"/></td>
   <td><input type="text" value="${item.content}" size="12"/></td>
   <td><input type="text" value="${item.questionId}" size="12"/></td>
   <td><input type="text" value="${item.score}" size="12"/></td>
  </tr>
  </c:forEach>
  <tr align="center">
   <td colspan="10"><input type="button" value="修改单条被选中记录" onclick="goAuthorUpdate()"/>
    <c:if test="${updateMessage!=null}">
     ${updateMessage}
    </c:if>
    <input type="submit" value="删除选中记录" onclick="return confirm('选中复选框，确定要删除吗?')"/>
    <input type="button" value="与该作者相关的信息" onclick="goAuthorRelatedInfo()"/>
    <c:if test="${actionReturnMessage!=null}">
     ${actionReturnMessage}
    </c:if>
   </td>
  </tr>
 </table>
</s:form>
</body>
</html>
			
				