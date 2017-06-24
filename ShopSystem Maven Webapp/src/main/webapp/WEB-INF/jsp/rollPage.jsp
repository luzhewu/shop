<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head lang="en">
	  <title></title>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/style.css"/>
</head>
<script type="text/javaScript">
	function page_nav(frm,num){
		//alert(num);
		frm.pageIndex.value=num;
		frm.submit();
	}
	function jump_to(frm,num){
		var regexp=/^[1-9]\d*$/;
		var totalPageCount=document.getElementById("totalPageCount").value;
		if(!regexp.test(num)){
			alert("请输入正确的数字！");
			return false;
		}else if((num-totalPageCount) > 0){
			alert("总页码一共"+totalPageCount+"，请输入正确的页码！");
			return false;
		}else{
			page_nav(frm, num);
		}
	}
</script>
<body>
<table class="providerTable" cellpadding="0" cellspacing="0">
	     <tr>
                <td>共<c:out value="${param.totalCount}" />条记录&nbsp;&nbsp; <c:out
					value="${param.currentPageNo}" />/<c:out
					value="${param.totalPageCount}" />页</td>
                <c:if test="${param.currentPageNo>1 }">
				<td><a href="javaScript:page_nav(document.forms[0],1)">首页</a>
				</td>
				<td><a
					href="javaScript:page_nav(document.forms[0],<c:out value="${param.currentPageNo-1}"/>)">上一页</a>
				</td>
			</c:if>
			<c:if test="${param.currentPageNo<=1 }">
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;&nbsp;</td>
			</c:if>
			<c:if test="${param.currentPageNo < param.totalPageCount }">
				<td><a
					href="javaScript:page_nav(document.forms[0],<c:out value="${param.currentPageNo+1}"/>)">下一页</a>
				</td>
				<td><a
					href="javaScript:page_nav(document.forms[0],<c:out value="${param.totalPageCount}" />)">最后一页</a>
				</td>
			</c:if>
			<c:if test="${currentPageNo >= totalPageCount }">
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;&nbsp;</td>
			</c:if>
			<td>&nbsp;&nbsp;&nbsp;</td>
                <td><span class="page-go-form"><label>跳转至</label> <input
			type="text" name="inputPage" id="inputPage" class="page-key" value="${currentPageNo}" size="4"/>页
			<button type="button" class="page-btn"
				onClick="jump_to(document.forms[0],document.getElementById('inputPage').value)">GO</button></span></td>
          </tr>
          </table>
</body>
</html>