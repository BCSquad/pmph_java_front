
$(function(){
	$(".replytag").each(function(){
		var $t = $(this);
		$t.unbind().bind("click",function(){
			var tid = $t.attr("id");
			if (tid=="replytag_all") {
				$("#is_replied").val("");
			}else if(tid=="replytag_toreply"){
				$("#is_replied").val("0");
			}else if(tid=="replytag_replied"){
				$("#is_replied").val("1");
			}
			$(".replytag").removeClass("active");
			$t.addClass("active");
			$("#pageNum").val(1);
			queryMain();
		});
	});
	
	//第一主编和读者切换
	$(".whoTag").each(function(){
		var $t = $(this);
		$t.unbind().bind("click",function(){
			var tag = $t.attr("tag");
			
			$("#pageNum").val(1);
			$("#pagetag").val(tag);
			queryMain();
		});
	});
	
	$(".whoTag").removeClass("active");
	$(".whoTag[tag='"+$("#pagetag").val()+"']").addClass("active");
	$("#wdjc").addClass("xz");
	
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
			if (json.code=="OK") {
				window.message.success(json.msg);
				setTimeout(function(){
					queryMain();
				}, 800);
			}else if(json.code=="WARNING"){
				window.message.warning(json.msg);
				$("#btn_"+id).attr("disabled",false);
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			$("#btn_"+id).attr("disabled",false);
		}
	});
}

//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){
	
	var va = obj.value;
	var vat = "";
	for ( var i = 1; i <= va.length; i++) {
		vat = va.substring(0,i);
		//把双字节的替换成两个单字节的然后再获得长度，与限制比较
		if (vat.replace(/[^\x00-\xff]/g,"aa").length <= ml) {
			maxStrlength=i;
		}else{
			
			break;
		}
	}
	obj.maxlength=maxStrlength;
	//把双字节的替换成两个单字节的然后再获得长度，与限制比较
	if (va.replace(/[^\x00-\xff]/g,"aa").length > ml) {
		obj.value=va.substring(0,maxStrlength);
		window.message.warning("不可超过输入最大长度"+ml+"字节！");
	}
}
