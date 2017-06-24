var proCode = null;
var proName = null;
var proContact = null;
var proPhone = null;
var addBtn = null;

$(function() {
	proCode = $("#proCode");
	proName = $("#proName");
	proContact = $("#proContact");
	proPhone = $("#proPhone");
	proAddress = $("#proAddress");
	proFax = $("#proFax");
	proDesc = $("#proDesc");
	addBtn = $("#add");

	// 初始化的时候，要把所有提示信息变为： * 灰色的提示语，以提示必填项
	proCode.next().html("*");
	proName.next().html("*");
	proContact.next().html("*");
	proPhone.next().html("*");
	proAddress.next().html("*");
	proFax.next().html("*");
	proDesc.next().html("*");
	/**
	 * 验证proCode
	 */
	proCode.on("blur", function() {
		// ajax后台异步验证----userCode是否存在
		$.ajax({
			type : "get",// 请求类型
			url : path + "/provider.do",
			data : {
				method : "ucexist",
				proCode : proCode.val()
			},// 请求参数
			dataType : "json",// ajax接口，（请求URL）返回的数据类型
			success : function(data) {// data：返回的数据（json对象）
				if (data.proCode == "exist") {// 账号已存在
					validateTip(proCode.next(), {
						"color" : "red"
					}, imgNo + " 该供应商已存在", "false");
				} else if (data.proCode == "notexist") {// 账号可用
					validateTip(proCode.next(), {
						"color" : "green"
					}, imgYes + " 该供应商编码可用", "true");
				}
			},
			error : function(data) {// 当访问时发生404,500等非200的错误状态码
				validateTip(proCode.next(), {
					"color" : "red"
				}, imgNo + " 您访问的页面不存在", "false");
			}
		});
	}).on("focus", function() {
		// 显示友情提示
		validateTip(proCode.next(), {
			"color" : "#666666"
		}, "* 供应商编码对应供应商，必填", "false");
	}).focus();
	/**
	 * 验证proName
	 */
	proName.on(
			"blur",
			function() {
				if (proName.val() != null && proName.val() != ''
						&& proName.val().length > 3
						&& proName.val().length < 10) {
					validateTip(proName.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(proName.next(), {
						"color" : "red"
					}, imgNo + " 供应商名称输入不符合规范，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(proName.next(), {
			"color" : "#666666"
		}, "* 请输入大于3位小于10位的供应商名", "false");
	});
	/**
	 * 验证proContact
	 */
	proContact.on(
			"blur",
			function() {
				if (proContact.val() != null && proContact.val() != ''
						&& proContact.val().length > 1
						&& proContact.val().length < 5) {
					validateTip(proContact.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(proContact.next(), {
						"color" : "red"
					}, imgNo + " 联系人输入不符合规范，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(proContact.next(), {
			"color" : "#666666"
		}, "* 请输入大于1位小于5位的联系人", "false");
	});
	/**
	 * 验证proPhone
	 */
	proPhone.on("blur",function() {
				var patrn = /^[1][358][0-9]{9}$/;
				if (proPhone.val() != null && proPhone.val() != ''
						&& proPhone.val().match(patrn)) {
					validateTip(proPhone.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(proPhone.next(), {
						"color" : "red"
					}, imgNo + " 您输入的电话号码有误，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(proPhone.next(), {
			"color" : "#666666"
		}, "* 请输入您的电话号码", "false");
	});
	
	/**
	 * 提交操作
	 */
	addBtn.on("click", function() {
		if (proCode.attr("validateStatus") != "true") {
			userCode.blur();
		} else if (proName.attr("validateStatus") != "true") {
			proName.blur();
		} else if (proContact.attr("validateStatus") != "true") {
			proContact.blur();
		} else if (proPhone.attr("validateStatus") != "true") {
			proPhone.blur();
		}else{
			if (confirm("是否要提交数据？")) {
				$("#form1").submit();
			}
		}
	});
});