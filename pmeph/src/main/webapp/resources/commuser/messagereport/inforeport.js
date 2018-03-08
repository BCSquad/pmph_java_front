
//信息快报列表换一批
function trychange(){
	var x;
	var materialId=$("#materialId").val();
	var mvalue=$("#count").val();
	if(mvalue==""||isNaN(mvalue)){
		x=1;
	}else{
		x=parseInt(mvalue)+1;
	}
	$.ajax({
		type:'post',
		url:contextpath+'inforeport/trychange.action',
		data:{count:x,materialId:materialId},
		async:false,
		dataType:'json',
		success:function(json){
		   var str="";
		   $.each(json.list,function(i,n){
			   str+='<tr class="sxy-tr"><td><div onclick=lookDetail(\''+n.id+'\') style="width:275px">'+
				    n.title+
			       '</div></div></td></tr><tr class="sxy-tr"><td><img alt="" src="'+
			       contextpath+
			       'statics/image/cupline.jpg" /></td></tr>';
		   });
		   
		   $("#count").val(json.count);
		   $("#trows").html(str);
		}
	});
}

//查看信息快报的详情
function lookDetail(id){
	var x=1;
	var mvalue=$("#count").val();
	if(!(mvalue==""||isNaN(mvalue))){
		x=parseInt(mvalue);
	}
	location.href=contextpath+"inforeport/toinforeport.action?count="+x+"&&id="+id+"&&materialId="+$("#materialId").val();
}
//添加或取消收藏
function addlike(id){
	$.ajax({
		type:'post',
		url:contextpath+'inforeport/addlike.action',
		data:{id:id},
		async:false,
		dataType:'json',
		success:function(json){
		   if(json.returncode=="NO"){
			   $("#like").attr("src",contextpath+"statics/image/dz02.png");
		   }else{
			   $("#like").attr("src",contextpath+"statics/image/dz01.png");
		   }
		}
	});
}
//添加或取消收藏
function addmark(id){
	$.ajax({
		type:'post',
		url:contextpath+'inforeport/addmark.action',
		data:{id:id},
		async:false,
		dataType:'json',
		success:function(json){
		   if(json.returncode=="NO"){
			   $("#mark").attr("src",contextpath+"statics/image/s102(1).png");
		   }else{
			   $("#mark").attr("src",contextpath+"statics/image/sc101(1).png");
		   }
		}
	});
}