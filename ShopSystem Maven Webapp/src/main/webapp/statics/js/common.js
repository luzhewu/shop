//获取当前页面路径
var path = $("#path").val();

var imgYes = "<img src='"+path+"/statics/img/y.png' width='15px'/>";
var imgNo = "<img src='"+path+"/statics/img/n.png' width='15px'/>";

/*
 * 提示信息显示
 * element:显示提示信息的元素
 * css:提示信息的样式
 * tipString:提示语
 * status:表示是否可以提交的状态
 */

function validateTip(element,css,tipString,status){
	element.css(css);
	element.html(tipString);
	
	element.prev().attr("validateStatus",status);
}

