<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/main/header.jsp"/>

	<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>用户编号：</strong><span>${user.userCode }</span></p>
            <p><strong>用户名称：</strong><span>${user.userName }</span></p>
            <p><strong>用户性别：</strong><span>
            <c:if test="${user.gender==1 }">男</c:if>
			<c:if test="${user.gender == 2 }">女</c:if>
			</span></p>
            <p><strong>出生日期：</strong><span>
            ${user.birthday }
            </span></p>
            <p><strong>用户电话：</strong><span>${user.phone }</span></p>
            <p><strong>用户地址：</strong><span>${user.address }</span></p>
            <p><strong>用户类别：</strong><span>
				<c:if test="${user.userType==1 }">管理员</c:if>
				<c:if test="${user.userType==2 }">经理</c:if>
				<c:if test="${user.userType==3 }">普通用户</c:if>
			</span></p>

            <a href="javascript:;" onclick="history.back()">返回</a>
        </div>
    </div>
	
<jsp:include page="/main/footer.jsp"/>

