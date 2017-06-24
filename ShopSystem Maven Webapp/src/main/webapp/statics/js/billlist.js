$(function(){
	//通过jQuery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	$(".viewBill").on("click",function(){
		//将被绑定的当前元素转换为jQuery对象
		var obj=$(this);
		window.location.href=path+"/bill.do?method=view&bid="+obj.attr("billid");
	});
	
	$(".modifyBill").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/bill.do?method=modify&bid="+obj.attr("billid");
	});
	
	$(".deleteBill").on("click",function(){
		var obj=$(this);
		if(confirm("你确定要删除商品【"+obj.attr("bname")+"】吗？")){
			$.ajax({
				type:"get",
				url:path+"/bill.do",
				data:{method:"delbill",bid:obj.attr("billid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "noexist"){
						alert("需要删除的商品【"+obj.attr("bname")+"】不存在");
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除商品【"+obj.attr("bname")+"】失败");
					}
				},
				error:function(data){
					alert("删除失败");
				}
			});
		}
	});
});