var billCode = null;
var productName = null;
var productUnit = null;
var productCount = null;
var totalPrice = null;
var providerId = null;

var addBtn = null;

$(function() {
	billCode = $("#billCode");
	productName = $("#productName");
	productUnit = $("#productUnit");
	productCount = $("#productCount");
	totalPrice = $("#totalPrice");
	providerId = $("#providerId");
	addBtn = $("#add");

	// 初始化的时候，要把所有提示信息变为： * 灰色的提示语，以提示必填项
	billCode.next().html("*");
	productName.next().html("*");
	productUnit.next().html("*");
	productCount.next().html("*");
	totalPrice.next().html("*");
	providerId.next().html("*");
	
	//异步加载供应商列表
	$.ajax({
			type : "get",// 请求类型
			url : path + "/bill.do",
			data : {method : "getProviderlist"},// 请求参数
			dataType : "json",// ajax接口，（请求URL）返回的数据类型
			success : function(data) {// data：返回的数据（json对象）
				$("select").html("");
				var options="<option value=\"0\">--请选择--</option>";
				for(var i=0;i<data.length;i++){
					options +="<option value=\""+data[i].id+"\">"+data[i].proName+"</option>";
				}
				providerId.html(options);
			},
			error : function(data) {// 当访问时发生404,500等非200的错误状态码
				validateTip(providerId.next(), {
					"color" : "red"
				}, imgNo + " 供应商列表加载失败", "false");
			}
	});
	/**
	 * 验证billCode
	 */
	billCode.on("blur", function() {
		// ajax后台异步验证----billCode是否存在
		$.ajax({
			type : "get",// 请求类型
			url : path + "/bill.do",
			data : {
				method : "ucexist",
				billCode : billCode.val()
			},// 请求参数
			dataType : "json",// ajax接口，（请求URL）返回的数据类型
			success : function(data) {// data：返回的数据（json对象）
				if (data.billCode == "exist") {// 账号已存在
					validateTip(billCode.next(), {
						"color" : "red"
					}, imgNo + " 该商品编码已存在", "false");
				} else if (data.billCode == "notexist") {// 账号可用
					validateTip(billCode.next(), {
						"color" : "green"
					}, imgYes + " 该商品编码可用", "true");
				}
			},
			error : function(data) {// 当访问时发生404,500等非200的错误状态码
				validateTip(billCode.next(), {
					"color" : "red"
				}, imgNo + " 您访问的页面不存在", "false");
			}
		});
	}).on("focus", function() {
		// 显示友情提示
		validateTip(billCode.next(), {
			"color" : "#666666"
		}, "* 商品编码对应商品，必填", "false");
	}).focus();
	/**
	 * 验证productName
	 */
	productName.on(
			"blur",
			function() {
				if (productName.val() != null && productName.val() != '') {
					validateTip(productName.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(productName.next(), {
						"color" : "red"
					}, imgNo + " 商品名称不能为空，请输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(productName.next(), {
			"color" : "#666666"
		}, "* 请输入商品名称", "false");
	});
	/**
	 * 验证productUnit
	 */
	productUnit.on(
			"blur",
			function() {
				if (productUnit.val() != null &&  productUnit.val() != '') {
					validateTip(productUnit.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(productUnit.next(), {
						"color" : "red"
					}, imgNo + " 商品单位不能为空，请输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(productUnit.next(), {
			"color" : "#666666"
		}, "* 请输入商品单位", "false");
	});
	/**
	 * 验证productCount
	 */
	productCount.on(
			"blur",
			function() {
				var patrn= /^\d{1,18}(\.\d{1,2})?$/;
				if (productCount.val() != null && productCount.val() != '' 
					&& productCount.val().match(patrn)) {
					validateTip(productCount.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(productCount.next(), {
						"color" : "red"
					}, imgNo + " 商品数量请符合数字小数点最多2位的数字", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(productCount.next(), {
			"color" : "#666666"
		}, "* 请输入最多小数点后2位的商品数量", "false");
	});
	/**
	 * 验证totalPrice
	 */
	totalPrice.on("blur",function() {
				var patrn = /^\d{1,18}(\.\d{1,2})?$/;
				if (totalPrice.val() != null && totalPrice.val() != ''
						&& totalPrice.val().match(patrn)) {
					validateTip(totalPrice.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(totalPrice.next(), {
						"color" : "red"
					}, imgNo + " 商品总额请符合数字小数点最多2位的数字，请重新输入", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(totalPrice.next(), {
			"color" : "#666666"
		}, "* 请输入商品总额符合数字小数点最多2位的数字", "false");
	});
	
	/**
	 * 验证providerId
	 */
	providerId.on("blur",function() {
				if (providerId.val() != null && providerId.val() != 0) {
					validateTip(providerId.next(), {
						"color" : "green"
					}, imgYes, "true");
				} else {
					validateTip(providerId.next(), {
						"color" : "red"
					}, imgNo + " 没有选择供应商，请重新选择", "false");
				}
			}).on("focus", function() {
		// 显示友情提示
		validateTip(providerId.next(), {
			"color" : "#666666"
		}, "* 请选择供应商", "false");
	});
	
	/**
	 * 提交操作
	 */
	addBtn.on("click", function() {
		if (billCode.attr("validateStatus") != "true") {
			billCode.blur();
		} else if (productName.attr("validateStatus") != "true") {
			productName.blur();
		} else if (productUnit.attr("validateStatus") != "true") {
			productUnit.blur();
		} else if (productCount.attr("validateStatus") != "true") {
			productCount.blur();
		} else if (totalPrice.attr("validateStatus") != "true") {
			totalPrice.blur();
		}  else if (providerId.attr("validateStatus") != "true") {
			providerId.blur();
		}  else{
			if (confirm("是否要提交数据？")) {
				$("#form1").submit();
			}
		}
	});
});