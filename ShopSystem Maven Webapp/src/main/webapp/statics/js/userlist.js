$(function(){
	//通过jQuery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	$(".viewUser").on("click",function(){
		//将被绑定的当前元素转换为jQuery对象
		var obj=$(this);
		/*window.location.href=path+"/user/userview.html?uid="+obj.attr("userid");*/
		$.ajax({
			type:"POST",
			url:path+"/user/userview.html",
			data:{uid:obj.attr("userid")},
			dataType:"json",
			success:function(data){
				if("failed"==data){
					alert("请求超时");
				}else if("nodata"==data){
					alert("无数据");
				}else{
					$("#v_userCode").val(data.userCode);
					$("#v_birthday").val(data.birthday);
				}
			}
		});
	});
	
	$(".modifyUser").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/user.do?method=modify&uid="+obj.attr("userid");
	});
	
	$(".deleteUser").on("click",function(){
		var obj=$(this);
		if(confirm("你确定要删除用户【"+obj.attr("username")+"】吗？")){
			$.ajax({
				type:"get",
				url:path+"/user.do",
				data:{method:"deluser",uid:obj.attr("userid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "noexist"){
						alert("需要删除的用户【"+obj.attr("username")+"】不存在");
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除用户【"+obj.attr("username")+"】失败");
					}
				},
				error:function(data){
					alert("删除失败");
				}
			});
		}
	});
});