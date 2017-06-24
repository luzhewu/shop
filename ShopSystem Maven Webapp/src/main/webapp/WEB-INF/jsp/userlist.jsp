<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/main/header.jsp" flush="true"/>

<div class="right">
	<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath}" />
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
            	<form method="post" action="${pageContext.request.contextPath }/user/userlist.html">
                <input type="hidden" name="pageIndex" value="1" />
                <span>用户名：</span>
                <input type="text" placeholder="请输入用户名" name="userName" value="${userName }"/>
                <input type="button" onclick="submit();" value="查询"/>
                <a href="${pageContext.request.contextPath}/user/useradd.html">添加用户</a>
                </form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户类型</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach var="user" items="${userList }" varStatus="status">
                <input type="hidden" id="totalPageCount" value="<c:out value='${totalPageCount }'/>" />
                <tr>
                    <td>${user.userCode }</td>
                    <td>${user.userName}</td>
                    <td><c:if test="${user.gender==1 }">男</c:if>
                     <c:if test="${user.gender==2 }">女</c:if></td>
                    <td>${user.age }</td>
                    <td>${user.phone }</td>
                    <td><c:if test="${user.userType==1 }">系统管理员</c:if>
                     <c:if test="${user.userType==2 }">经理</c:if> 
                    <c:if test="${user.userType==3 }">员工</c:if></td>
                    <td>
                        <a class="viewUser" href="javascript:;" userid="${user.id}" username="${user.userName }">
                        	<img src="${pageContext.request.contextPath }/statics/img/read.png" alt="查看" title="查看"/></a>
                        <a class="modifyUser" href="javascript:;" userid="${user.id}" username="${user.userName }">
                        	<img src="${pageContext.request.contextPath }/statics/img/xiugai.png" alt="修改" title="修改"/></a>
                        <a class="deleteUser" href="javascript:;" userid="${user.id}" username="${user.userName }">
                        	<img src="${pageContext.request.contextPath }/statics/img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
             <c:import url="rollPage.jsp" >
           	<c:param name="totalCount" value="${ totalCount}"></c:param>
           	<c:param name="currentPageNo" value="${currentPageNo}"></c:param>
           	<c:param name="totalPageCount" value="${totalPageCount}"></c:param>
           </c:import>
    	用户编码：<input type="text" name="v_userCode" id="v_userCode" value=""/><br/>
    	出生日期：<input type="text" name="v_birthday" id="v_birthday" value=""/>
        </div>
    
    
 <jsp:include page="/main/footer.jsp" flush="true"/>  
 <script type="text/javascript"
		src="${pageContext.request.contextPath }/statics/js/userlist.js"></script>    