<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/main/header.jsp" flush="true"/>


    <div class="right">
        <img class="wColck" src="${pageContext.request.contextPath }/statics/img/clock.jpg" alt=""/>
        <div class="wFont">
            <h2> ${userSession.userName }</h2>
            <p>欢迎来到超市账单管理系统!</p>
        </div>
    </div>
    
<jsp:include page="/main/footer.jsp" flush="true"/>