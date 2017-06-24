var userName=null;
var birthday=null;
var phone=null;
var saveBtn=null;

$(function(){
	userName=$("#userName");
	birthday=$("#birthday");
	phone=$("#phone");
	saveBtn=$("#save");
	
	userName.next().html("*");
	birthday.next().html("*");
	phone.next().html("*");
	
	userName.on("focus",function(){
		validateTip(userName.next(),{"color":"#666666"},"* 用户名必须是大于3 小于10的字符","false");
	}).on("blur",function(){
		if(userName.val()!=null && userName.val().length>3 && userName.val().length<10){
			validateTip(userName.next(),{"color":"green"},imgYes,"true");
		}else{
			validateTip(userName.next(),{"color":"red"},imgNo+" 您输入的用户名不符合规范，请重新输入","false");
		}
	});
	/**
	 * 验证birthday
	 */
	birthday.on("blur", function() {
		if (birthday.val() != '' && birthday.val() != null) {
			validateTip(birthday.next(), {
				"color" : "green"
			}, imgYes, "true");
		} else {
			validateTip(birthday.next(), {
				"color" : "red"
			}, imgNo + " 您没有选择出生日期，请重新选择", "false");
		}
	}).on("focus", function() {
		// 显示友情提示
		validateTip(birthday.next(), {
			"color" : "#666666"
		}, "* 请选择您的出生日期", "false");
	});
	/**
	 * 验证phone
	 */
	phone.on("blur",function() {
				var patrn = /^[1][358][0-9]{9}$/;
				if (phone.val() != null && phone.val() != ''
						&& phone.val().match(patrn)) {
					validateTip(phone.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(phone.next(), {
						"color" : "red"
					}, imgNo + " 您输入的电话号码有误，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(phone.next(), {
			"color" : "#666666"
		}, "* 请输入您的电话号码", "false");
	});
	
	saveBtn.on("click",function(){
		userName.blur();
		birthday.blur();
		phone.blur();
		if(userName.attr("validateStatus") == "true" &&
				birthday.attr("validateStatus") == "true" &&
				phone.attr("validateStatus") == "true"){
			if(confirm("是否确认要提交数据")){
				$("#form1").submit();
			}
		}
	});
});