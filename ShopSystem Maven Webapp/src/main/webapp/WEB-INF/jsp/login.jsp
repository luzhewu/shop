<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统登录 - 超市账单管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/style.css"/>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>超市账单管理系统</h1>
        </header>
        <section class="loginCont">
            <form class="loginForm" action="${pageContext.request.contextPath }/dologin.html" name="actionForm" id="actionForm" method="post">
                <div style="color:red">${error}</div>
                <div class="inputbox">
                    <label for="user">账号：</label>
                    <input id="userCode" type="text" name="userCode" placeholder="请输入用户名" required/>
                </div>
                <div class="inputbox">
                    <label for="mima">密码：</label>
                    <input id="password" type="password" name="password" placeholder="请输入密码" required/>
                </div>
                <div class="subBtn">
                    <input type="submit" value="登录" onclick="validate();" />
                    <input type="reset" value="重置"/>
                </div>
            </form>
        </section>
    </section>
	<script type="text/javascript">
		function validate(){
			var username=document.getElementById("username").value;
			var password=document.getElementById("password").value;
			var flag=true;
			if(username == null || username == ''){
				flag=false;
			}
			if(password == null || password == ''){
				flag=false;
			}
			
			var actionForm = document.getElementById("actionForm");
			if(flag){
				actionForm.submit();
			}
		};
	</script>
</body>
</html>