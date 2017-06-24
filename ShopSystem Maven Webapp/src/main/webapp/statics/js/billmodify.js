var billCode = null;
var productName = null;
var productUnit = null;
var productCount = null;
var totalPrice = null;
var providerId = null;

var saveBtn = null;

$(function() {
	billCode = $("#billCode");
	productName = $("#productName");
	productUnit = $("#productUnit");
	productCount = $("#productCount");
	totalPrice = $("#totalPrice");
	providerId = $("#providerId");
	saveBtn = $("#save");

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
				var pid=$("#pid").val(); 
				var options="<option value=\"0\">--请选择--</option>";
				for(var i=0;i<data.length;i++){
					if( pid!=null && pid!=undefined && data[i].id==pid){
						options +="<option selected=\"selected\" value=\""+data[i].id+"\">"+data[i].proName+"</option>";
					}else{
						options +="<option  value=\""+data[i].id+"\">"+data[i].proName+"</option>";
					}
				}
				providerId.html(options);
			},
			error : function(data) {// 当访问时发生404,500等非200的错误状态码
				validateTip(providerId.next(), {
					"color" : "red"
				}, imgNo + " 供应商列表加载失败", "false");
			}
	});
	
	billCode.on("focus",function(){
		validateTip(billCode.next(),{"color":"#666666"},"* 商品编码必须是大于3小于20的字符","false");
	}).on("blur",function(){
		if(billCode.val()!=null && billCode.val().length>3 && billCode.val().length<20){
			validateTip(billCode.next(),{"color":"green"},imgYes,"true");
		}else{
			validateTip(billCode.next(),{"color":"red"},imgNo+" 您输入的商品编码不符合规范，请重新输入","false");
		}
	});
	
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
	saveBtn.on("click", function() {
		if (productName.attr("validateStatus") != "true") {
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