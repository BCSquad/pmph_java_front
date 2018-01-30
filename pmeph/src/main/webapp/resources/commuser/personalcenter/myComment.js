//评论切换
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

//评论展开收起样式
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

//评论弹出框
var score="";
$(document).ready(function () {
    //为所有的class为scorestar1绑定mouseout和mouseover事件。bind({事件名：function(){},事件名：function(){}})的方法绑定多个事件
    $(".scorestar1").bind({
        mouseover: function () {
            $(this).css("background-position", "-183px -174px").prevAll().css("background-position", "-183px -174px");
            $(this).nextAll().css({"background-position": "-183px -153px"});
             score = parseInt($(this).attr("id").substring(5)) * 2 + '.0';
             $("#last_score").html(score);
            
        }
    });
});


//点击显示评论弹窗
function showup(id) {
	 $.ajax({
	        type: 'post',
	        url: contextpath + 'personalhomepage/tologin.action',
	        async: false,
	        dataType: 'json',
	        success: function (json) {
	        	 $("#bookmistake").show();
	        	 $("#comm_id").val(id);
	        }
	    });
}

//点击弹窗隐藏
function hideup() {
    $("#bookmistake").hide();
}
//弹出框修改提交
function upd_comment() {
	     if(!Empty($("#content").val())){//非空判断
	    	 var json = {
	    			 	comm_id: $("#comm_id").val(),
	    			 	score: score,
	    		        content:$("#content").val()
	    		    };
	    		    $.ajax({
	    		        type: 'post',
	    		        url: contextpath + 'personalhomepage/updateComment.action',
	    		        data: json,
	    		        async: false,
	    		        dataType: 'json',
	    		        success: function (json) {
	    		            if (json.returncode == "OK") {
	    		            	window.message.info("数据已提交！");
	    		            	$("#bookmistake").hide();
	    		                $("#content").val(null);
	    		                queryMain();
	    		            } else {
	    		            	window.message.info("请填写内容和评分！！");
	    		            }
	    		        }
	    		    });
	     }else{
	    	 window.message.info("请填写内容和评分！");
	     }
 
}

//删除评论
/*function DelMyComm(id) {
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if (confirm(msg) == true) {
		var json = {
				comm_id : id,
		};
		$.ajax({
			type : 'post',
			url : contextpath + 'personalhomepage/deleteComment.action',
			async : false,
			dataType : 'json',
			data : json,
			success : function(json) {
				if (json.flag == "0") {
					$("#content").val(null);
					window.message.success("删除成功!");
				} else {
					window.message.error("删除失败!");
				}
			}
		});
		return location.reload;
	} else {
		return false;
	}
}*/
//删除评论
function DelMyComm(id) {
	var json = {
			comm_id : id,
	};
	window.message.confirm('您真的确定要删除吗？请确认！', {
		icon : 3,
		title : '提示',
		btn : [ "确定", "取消" ]
	}, function(index) {
		layer.close(index);
		$.ajax({
			url : contextpath + 'personalhomepage/deleteComment.action',
			type : "post",
			async : false,
			dataType : 'json',
			data : json,
			success : function(json) {
				if (json.flag == "0") {
					$("#content").val(null);
					window.message.success("删除成功！");
					setTimeout(queryMain(), 800);
				} else {
					window.message.error("删除失败！");
				}
			}

		});
	}, function(index) {
		layer.close(index);
	});
}


//点击图书标题进入详情页
function cbook(id) { 
    window.location.href = contextpath + 'readdetail/todetail.action?id=' + id;
}




// 输入长度限制校验，ml为最大字节长度
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
