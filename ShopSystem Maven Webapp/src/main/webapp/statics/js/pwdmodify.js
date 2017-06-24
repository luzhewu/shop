var oldPassword=null;
var newPassword=null;
var rnewPassword=null;
var saveBtn=null;

$(function(){
	oldPassword=$("#oldPassword");
	newPassword=$("#newPassword");
	rnewPassword=$("#rnewPassword");
	saveBtn=$("#save");
	
	oldPassword.next().html("*");
	newPassword.next().html("*");
	rnewPassword.next().html("*");
	
	oldPassword.on("blur",function(){
		$.ajax({
			type:"get",
			url:path+"/user.do",
			data:{
				method:"pwdmodify",
				oldPassword:oldPassword.val()
				},
			dataType:"json",
			success:function(data){
				
				if(data.result == "true"){//旧密码正确
					validateTip(oldPassword.next(),{"color":"green"},imgYes,"true");
				}else if(data.result == "false"){//旧密码不正确
					validateTip(oldPassword.next(),{"color":"red"},imgNo+" 原密码输入不正确","false");
				}else if(data.result == "false"){//当前用户session过期，提示重新登录
					validateTip(oldPassword.next(),{"color":"red"},imgNo+" 当前用户session已过期，请重新登陆",false);
				}
			},
			error:function(data){
				//请求出错
				validateTip(oldPassword.next(),{"color":"red"},imgNo+" 请求错误","false");
			}
		});
	}).on("focus",function(){
		validateTip(oldPassword.next(),{"color":"#666666"},"* 请输入原密码","false");
	});
	
	newPassword.on(
			"blur",
			function() {
				if (newPassword.val() != null && newPassword.val() != ''
						&& newPassword.val().length > 6
						&& newPassword.val().length < 20) {
					validateTip(newPassword.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(newPassword.next(), {
						"color" : "red"
					}, imgNo + " 密码输入不符合规范，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(newPassword.next(), {
			"color" : "#666666"
		}, "* 请输入大于6位小于20位的密码", "false");
	});
	/**
	 * 验证ruserPassword
	 */
	rnewPassword.on("blur",function() {
				if (rnewPassword.val() != null && rnewPassword.val() != ''
						&& rnewPassword.val().length > 6
						&& rnewPassword.val().length < 20
						&& rnewPassword.val() == newPassword.val()) {
					validateTip(rnewPassword.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(rnewPassword.next(), {
						"color" : "red"
					}, imgNo + " 确认密码输入与设定密码不一致，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(rnewPassword.next(), {
			"color" : "#666666"
		}, "* 请输入与上面一致的密码", "false");
	});
	
	saveBtn.on("click",function(){
		oldPassword.blur();
		newPassword.blur();
		rnewPassword.blur();
		if(oldPassword.attr("validateStatus")=="true"
			&& newPassword.attr("validateStatus")=="true"
				&& rnewPassword.attr("validateStatus")=="true"){
			if(confirm("确定要修改密码？")){
				$("#form1").submit();
			}
		}
	});
});