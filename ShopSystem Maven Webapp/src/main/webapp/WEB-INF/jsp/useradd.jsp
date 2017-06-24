<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/main/header.jsp" flush="true"/>

	<div class="right">
	<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form id="form1" name="form1" method="post" action="${pageContext.request.contextPath }/user/useraddsave.html">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="userCode">用户编码：</label>
                    <input type="text" name="userCode" id="userCode" value=""/>
                    <span></span>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" value=""/>
                    <span ></span>
                </div>
                <div>
                    <label for="userPassword">用户密码：</label>
                    <input type="text" name="userPassword" id="userPassword" value=""/>
                    <span></span>

                </div>
                <div>
                    <label for="ruserPassword">确认密码：</label>
                    <input type="text" name="ruserPassword" id="ruserPassword" value=""/>
                    <span></span>
                </div>
                <div>
                    <label >用户性别：</label>

                    <select name="gender" id="gender">
                        <option value="1" selected>男</option>
                        <option value="2">女</option>
                    </select>
                    <span></span>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" name="birthday" id="birthday" class="Wdate"
								id="birthday" value="" readonly="readonly"
								onclick="WdatePicker();"/>
                    <span ></span>
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <input type="text" name="phone" id="phone" value=""/>
                    <span ></span>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                    <input type="text" name="address" id="address" value=""/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <input type="radio" name="userType" value="1"/>管理员
                    <input type="radio" name="userType" value="2"/>经理
                    <input type="radio" name="userType" value="3"/>普通用户

                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="button"  name="add" id="add" value="保存"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>
    </div>

<jsp:include page="/main/footer.jsp" flush="true"/>    
    
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/useradd.js"></script>
