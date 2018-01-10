
$(function(){
	$(".replytag").each(function(){
		var $t = $(this);
		$t.unbind().bind("click",function(){
			var tid = $t.attr("id");
			if (tid=="replytag_all") {
				$("#is_long").val("");
			}else if(tid=="replytag_toreply"){
				$("#is_long").val("1");
			}else if(tid=="replytag_replied"){
				$("#is_long").val("0");
			}
			$(".replytag").removeClass("active");
			$t.addClass("active");
			$("#pageNum").val(1);
			queryMain();
		});
	});
	
});


$(function() {  
    var slideHeight = 50; // px 定义折叠的最小高度  
    var defHeight = $('#wrap').height();  
    if(defHeight >= slideHeight) {  
        $('#wrap').css('height', slideHeight + 'px');  
        $('#read-more').append('<a href="#">...(展开)</a>');  
        $('#read-more a').click(function() {  
            var curHeight = $('#wrap').height();  
            if(curHeight == slideHeight) {  
                $('#wrap').animate({  
                    height: defHeight  
                }, "normal");  
                $('#read-more a').html('收起');  
            } else {  
                $('#wrap').animate({  
                    height: slideHeight  
                }, "normal");  
                $('#read-more a').html('...(展开)');  
            }  
            return false;  
        });  
    }  
});  




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
