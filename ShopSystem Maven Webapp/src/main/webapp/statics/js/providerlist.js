$(function(){
	//通过jQuery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	$(".viewProvider").on("click",function(){
		//将被绑定的当前元素转换为jQuery对象
		var obj=$(this);
		window.location.href=path+"/provider.do?method=view&proid="+obj.attr("proid");
	});
	
	$(".modifyProvider").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/provider.do?method=modify&proid="+obj.attr("proid");
	});
	
	$(".deleteProvider").on("click",function(){
		var obj=$(this);
		if(confirm("你确定要删除供应商【"+obj.attr("proname")+"】吗？")){
			$.ajax({
				type:"get",
				url:path+"/provider.do",
				data:{method:"delprovider",proid:obj.attr("proid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "exist"){
						alert("需要删除的供应商【"+obj.attr("proname")+"】下存在【"+data.result+"】订单");
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
					}
				},
				error:function(data){
					alert("删除失败");
				}
			});
		}
	});
});