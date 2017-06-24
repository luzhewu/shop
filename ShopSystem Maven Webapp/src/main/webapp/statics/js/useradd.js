var userCode = null;
var userName = null;
var userPassword = null;
var ruserPassword = null;
var birthday = null;
var phone = null;
var addBtn = null;

$(function() {
	userCode = $("#userCode");
	userName = $("#userName");
	userPassword = $("#userPassword");
	ruserPassword = $("#ruserPassword");
	birthday = $("#birthday");
	phone = $("#phone");
	addBtn = $("#add");

	// 初始化的时候，要把所有提示信息变为： * 灰色的提示语，以提示必填项
	userCode.next().html("*");
	userName.next().html("*");
	userPassword.next().html("*");
	ruserPassword.next().html("*");
	birthday.next().html("*");
	phone.next().html("*");
	addBtn.next().html("*");
	/**
	 * 验证userCode
	 */
	userCode.on("blur", function() {
		// ajax后台异步验证----userCode是否存在
		$.ajax({
			type : "get",// 请求类型
			url : path + "/user/ucisexist.html",
			data : {userCode : userCode.val()},// 请求参数
			dataType : "json",// ajax接口，（请求URL）返回的数据类型
			success : function(data) {// data：返回的数据（json对象）
				if (data.userCode == "exist") {// 账号已存在
					validateTip(userCode.next(), {
						"color" : "red"
					}, imgNo + " 该用户已存在", "false");
				} else if (data.userCode == "notexist") {// 账号可用
					validateTip(userCode.next(), {
						"color" : "green"
					}, imgYes + " 该账号可用", "true");
				}
			},
			error : function(data) {// 当访问时发生404,500等非200的错误状态码
				validateTip(userCode.next(), {
					"color" : "red"
				}, imgNo + " 您访问的页面不存在", "false");
			}
		});
	}).on("focus", function() {
		// 显示友情提示
		validateTip(userCode.next(), {
			"color" : "#666666"
		}, "* 用户编码是您登录系统的账号", "false");
	}).focus();
	/**
	 * 验证userName
	 */
	userName.on(
			"blur",
			function() {
				if (userName.val() != null && userName.val() != ''
						&& userName.val().length > 3
						&& userName.val().length < 10) {
					validateTip(userName.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(userName.next(), {
						"color" : "red"
					}, imgNo + " 用户名输入不符合规范，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(userName.next(), {
			"color" : "#666666"
		}, "* 请输入大于3位小于10位的用户名", "false");
	});
	/**
	 * 验证userPassword
	 */
	userPassword.on(
			"blur",
			function() {
				if (userPassword.val() != null && userPassword.val() != ''
						&& userPassword.val().length > 6
						&& userPassword.val().length < 20) {
					validateTip(userPassword.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(userPassword.next(), {
						"color" : "red"
					}, imgNo + " 密码输入不符合规范，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(userPassword.next(), {
			"color" : "#666666"
		}, "* 请输入大于6位小于20位的密码", "false");
	});
	/**
	 * 验证ruserPassword
	 */
	ruserPassword.on(
			"blur",
			function() {
				if (ruserPassword.val() != null && ruserPassword.val() != ''
						&& ruserPassword.val().length > 6
						&& ruserPassword.val().length < 20
						&& ruserPassword.val() == userPassword.val()) {
					validateTip(ruserPassword.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(ruserPassword.next(), {
						"color" : "red"
					}, imgNo + " 确认密码输入与设定密码不一致，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(ruserPassword.next(), {
			"color" : "#666666"
		}, "* 请输入与上面一致的密码", "false");
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
	/**
	 * 提交操作
	 */
	addBtn.on("click", function() {
		if (userCode.attr("validateStatus") != "true") {
			userCode.blur();
		} else if (userName.attr("validateStatus") != "true") {
			userName.blur();
		} else if (userPassword.attr("validateStatus") != "true") {
			userPassword.blur();
		} else if (ruserPassword.attr("validateStatus") != "true") {
			ruserPassword.blur();
		} else if (birthday.attr("validateStatus") != "true") {
			birthday.blur();
		} else if (phone.attr("validateStatus") != "true") {
			phone.blur();
		}else{
			if (confirm("是否要提交数据？")) {
				$("#form1").submit();
			}
		}
	});
});