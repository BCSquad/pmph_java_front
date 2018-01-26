$(document).ready(function () {
	  
	
	
		//页签点击事件初始化
		$(".pagetag").each(function(){
			  var $t = $(this);
			  $t.unbind().bind("click",function(){
				  $(".xz").removeClass("xz");
				  $t.addClass("xz");
				  $("#pagetag").val($t.attr("id"));
				  $("#pageNum").val(1);
				  queryMain();
			  });
		});
		//进入页面 回显选中页签
	    $("#"+$("#pagetag").val()).addClass("xz");
	    
	    //分页
		//刷新分页栏
		 Page({
           num: $("#maxPageNum").val(),					//页码数
           startnum: $("#pageNum").val(),				//指定页码
           elem: $('#page1'),
           callback: function (n){     //点击页码后触发的回调函数
           	$("#pageNum").val(n);
           	queryMain();
           }
           });
	    
	    $('#page-size-select').selectlist({
	        zIndex: 10,
	        width: 110,
	        height: 30,
	        optionHeight: 30
	    });     
	  //切换分页每页数据数量时 刷新
		$("#page-size-select").find("li").bind("click",function(){
			$("#pageSize").val($(this).attr("data-value"));
			$("#pageNum").val(1);
			queryMain();
		});
            
});
  
//重定向并重新查询数据的方法
function queryMain(){
	//将不为空的查询条件拼接成字符串
	var queryConditionStr = queryConditionStrFun();

	window.location.href= contextpath + "personalhomepage/tohomepage.action?pagetag="
		+$("#pagetag").val()
		+"&pageNum="+$("#pageNum").val()
		+"&pageSize="+$("#pageSize").val()
		
		+queryConditionStr;

}

//将不为空的查询条件拼接成字符串
function queryConditionStrFun(){
	var Str = "";
	$(".queryCondition").each(function(){
		var $t=$(this);
		if ($t.val() != null && $t.val() != "") {
			Str+="&"+$t.attr("id")+"="+encodeURI(encodeURI($t.val()));//编码 到controller层再解码 避免乱码
		}
	});
	if ($("#selfLog").val()=='false') {
		Str = Str+"&userId="+$("#logUserId").val();
	}
	return Str;
}

//删除随笔文章
  function DelMyWriter(id){
	  
	  var msg = "您真的确定要删除这条随笔文章吗？\n\n请确认！";
	  if (confirm(msg)==true){
			var json={
					 id : id,
			  	};
			  	 $.ajax({
			  			type:'post',
			  			url:contextpath+'writerArticle/updateDelWriter.action',
			  			async:false,
			  			dataType:'json',
			  			data:json,
			  			success:function(json){
			  				if(json.flag=="0"){
			  					  $("#content").val(null);
			  					  window.message.success("删除成功!");
			  				}else{
			  					window.message.error("删除失败!");
			  				}
			  			}
			  		});
		  return location.reload;
	  
	  }else{
	  return false;
	  }
  }
  
//删除书评内容
  function DelMyBookWriter(id){
	  
	  var msg = "您真的确定要删除这条书评吗？\n\n请确认！";
	  if (confirm(msg)==true){
			var json={
			  		 id : id, 
			  	};
			  	 $.ajax({
			  			type:'post',
			  			url:contextpath+'readdetail/updateDelBookWriter.action',
			  			async:false,
			  			dataType:'json',
			  			data:json,
			  			success:function(json){
			  				if(json.flag=="0"){
			  					  $("#content").val(null);
			  					  window.message.success("删除成功!");
			  				}else{
			  					window.message.error("删除失败!");
			  				}
			  			}
			  		});
		  return location.reload;
	  
	  }else{
	  return false;
	  }
  }
  
  /**
   * 删除文章
   */
  function deleteArticle(id,title){
	  window.message.confirm(
				'确定要删除《'+title+'》吗？'
				,{icon: 3, title:'提示',btn:["确定","取消"]}
				,function(index){
					layer.close(index);
					$.ajax({
				  		url:contextpath+"writerArticle/updateDelWriter.action?t="+new Date()+"&id="+id,
				  		type:"post",
				  		success:function(json){
				  			$("#pageNum").val(1);
				  			if (json.flag=="0") {
				  				window.message.success("删除成功！");
				  				setTimeout(queryMain(), 800);
							}else{
								window.message.error("删除失败！");
							}
				  		}
				  		
				  	});
				}
				,function(index){
					layer.close(index);
				}
				);
  }
  
  /**
   * 删除我的纠错
   * @param id
   */
  function deleteMyCorrect(id){
	  window.message.confirm(
				'确定要删除该条纠错吗？'
				,{icon: 3, title:'提示',btn:["确定","取消"]}
				,function(index){
					layer.close(index);
					$.ajax({
				  		url:contextpath+"personalhomepage/deleteMyCorrection.action?t="+new Date()+"&id="+id,
				  		type:"post",
				  		success:function(json){
				  			$("#pageNum").val(1);
				  			
				  			if (json.code=="OK") {
				  				window.message.success(json.msg);
				  				setTimeout(queryMain(), 800);
							}else{
								window.message.warning(json.msg);
							}
				  		}
				  		
				  	});
				}
				,function(index){
					layer.close(index);
				}
				);
  }
  
  
    /**
   * 删除我的书评
   * @param id
   */
  function deleteMyBookComment(id){
	  window.message.confirm(
				'确定要删除该条书评吗？'
				,{icon: 3, title:'提示',btn:["确定","取消"]}
				,function(index){
					layer.close(index);
					$.ajax({
				  		url:contextpath+"personalhomepage/deleteMyBookComment.action?t="+new Date()+"&id="+id,
				  		type:"post",
				  		success:function(json){
				  			$("#pageNum").val(1);
				  			
				  			if (json.code=="OK") {
				  				window.message.success(json.msg);
				  				setTimeout(queryMain(), 800);
							}else{
								window.message.warning(json.msg);
							}
				  		}
				  		
				  	});
				}
				,function(index){
					layer.close(index);
				}
				);
  }
  
  
  
  //tjx所写 不知有何用 未被调用过
  /*function abc() {
  var itemMax=4;
  var n=1;
  for(var i=n;i<=itemMax;i++)document.getElementById('tabone'+i).style.display='none';
  function taba(){
      var n=1;
      var original=new Array;
      for (var i=0;i<itemMax;i++){
      original=i+1;
      }
      for(var i=1;i<=itemMax;i++){document.getElementById('tabone'+i).style.display='none';}
      for (i=0;i<n;i++){
      var index=Math.floor(Math.random() * original.length);
      document.getElementById('tabone'+original).style.display='block';
      original.splice(index,1);
      }
  }
  taba();
  }*/
  
  