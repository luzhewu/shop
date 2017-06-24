var proCode=null;
var proName=null;
var proPhone=null;
var proContact=null;

var saveBtn=null;

$(function(){
	
	proCode=$("#proCode");
	proName=$("#proName");
	proPhone=$("#proPhone");
	proContact=$("#proContact");
	saveBtn=$("#save");
	
	proPhone.next().html("*");
	proContact.next().html("*");
	
	proCode.on("focus",function(){
		validateTip(proCode.next(),{"color":"#666666"},"* 供应商编码必须是大于3小于20的字符","false");
	}).on("blur",function(){
		if(proCode.val()!=null && proCode.val().length>3 && proCode.val().length<20){
			validateTip(proCode.next(),{"color":"green"},imgYes,"true");
		}else{
			validateTip(proCode.next(),{"color":"red"},imgNo+" 您输入的供应商编码不符合规范，请重新输入","false");
		}
	});
	
	proName.on("focus",function(){
		validateTip(proName.next(),{"color":"#666666"},"* 供应商名称必须是大于3小于20的字符","false");
	}).on("blur",function(){
		if(proName.val()!=null && proName.val().length>3 && proName.val().length<20){
			validateTip(proName.next(),{"color":"green"},imgYes,"true");
		}else{
			validateTip(proName.next(),{"color":"red"},imgNo+" 您输入的供应商名称不符合规范，请重新输入","false");
		}
	});
	
	proContact.on("focus",function(){
		validateTip(proContact.next(),{"color":"#666666"},"* 用户名必须是大于1小于5的字符","false");
	}).on("blur",function(){
		if(proContact.val()!=null && proContact.val().length>1 && proContact.val().length<5){
			validateTip(proContact.next(),{"color":"green"},imgYes,"true");
		}else{
			validateTip(proContact.next(),{"color":"red"},imgNo+" 您输入的供应商联系人不符合规范，请重新输入","false");
		}
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
	
	saveBtn.on("click",function(){
		proCode.blur();
		proName.blur();
		proContact.blur();
		proPhone.blur();
		if(proCode.attr("validateStatus") == "true"
			&&proName.attr("validateStatus") == "true"
			&&proContact.attr("validateStatus") == "true"
			&& proPhone.attr("validateStatus") == "true"){
			if(confirm("是否确认要提交数据")){
				$("#form1").submit();
			}
		}
	});
});