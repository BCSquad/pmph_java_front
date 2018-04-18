
$(function(){
	$(".replytag").each(function(){
		var $t = $(this);
		$t.unbind().bind("click",function(){
			var tid = $t.attr("id");
			if (tid=="replytag_all") {
				$("#is_auth").val("");
			}else if(tid=="replytag_toreply"){
				$("#is_auth").val("0");
			}else if(tid=="replytag_replied"){
				$("#is_auth").val("1");
			}
			$(".replytag").removeClass("active");
			$t.addClass("active");
			$("#pageNum").val(1);
			queryMain();
		});
	});
});


//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){
	
	var va = obj.value;
	var vat = "";
	for ( var i = 1; i <= va.length; i++) {
		vat = va.substring(0,i);
		//把双字节的替换成两个单字节的然后再获得长度，与限制比较
		if (vat.replace(/[^\x00-\xff]/g,"a").length <= ml) {
			maxStrlength=i;
		}else{
			
			break;
		}
	}
	obj.maxlength=maxStrlength;
	//把双字节的替换成两个单字节的然后再获得长度，与限制比较
	if (va.replace(/[^\x00-\xff]/g,"a").length > ml) {
		obj.value=va.substring(0,maxStrlength);
		window.message.warning("不可超过输入最大长度"+ml+"字节！");
	}
}
