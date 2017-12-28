
$(function(){
	$(".replytag").each(function(){
		var $t = $(this);
		$t.unbind().bind("click",function(){
			var tid = $t.attr("id");
			if (tid=="replytag_all") {
				$("#is_author_replied").val("");
			}else if(tid=="replytag_toreply"){
				$("#is_author_replied").val("0");
			}else if(tid=="replytag_replied"){
				$("#is_author_replied").val("1");
			}
			$(".replytag").removeClass("active");
			$t.addClass("active");
			$("#pageNum").val(1);
			queryMain();
		});
	});
});


//下载
function downLoadProxy(fileId){
	window.location.href=contextpath+'file/download/'+fileId+'.action';
}

//提交回复 id是纠错表的主键id
function submitReply(id){
	
	
	$("#btn_"+id).attr("disabled",true);
	
	var data={
			author_reply:$("#textarea_"+id).val(),
			id:id
	};
	
	$.ajax({
		type:'post',
		url:contextpath+'personalhomepage/authorReply.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			window.message.success(json.msg);
			setTimeout(function(){
				queryMain();
			}, 800);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			$("#btn_"+id).attr("disabled",false);
		}
	});
}
