

$(function(){
    $(".icon").hide();
    $(".file-tip").hide();
    $("#file_id").change(function(){  // 当 id 为 file 的对象发生变化时
        if(($("#file_id").val()).length==0){
            $("#file_name").text();  //将 #file 的值赋给 #a
            $("#up_txt").text('重新上传');
        }else{
            $("#file_name").text($("#file_id").val());  //将 #file 的值赋给 #a
            $(".icon").show();
            $("#up_txt").text('重新上传');

            if(((this.files[0].size).toFixed(2))>=(100*1024*1024)){
                			$(".file-tip").show();
                                return false;
                          }
        }


    })
    
    // 单选框 change时间
    $('input:radio[name="sendObject"]').change(function(){
    	if($('input:radio[name="sendObject"]:checked').val()!='0'){
    		//location.href = "http://localhost:8080/pmeph/info/toPage.action";
    		//$("#send").val("下一步")
    		var index =layer.open({
    			  type: 2,
    			  content: contextpath+'info/toPage.action',
    			  area: ['320px', '195px'],
    			  maxmin: true
    			});
    		layer.full(index);
    		
    	}
    	});
});


//$("input[name='submit']").onclick =
	
/*	function message(){
	var titleValue = $("#TitleValue").val();
	var radioValue ; //获取单选按钮的值
	if($('#one').attr("checked")){
		//所有人
		radioValue = $('#one').val();
		}else{
			//教材所有者
		}
	var UEContent = UE.getEditor('mText').getContent();
	
	//文件对象  this.files[0] 文件名 file.name 文件类型  file.type  文件大小  file.size  文件内容
	
	//var file = $("#file_id").files[0];
	var file = $('input[name="fileTrans"]').prop('files')[0];//获取到文件列表  IE 下未传文件是null chorme 下未传文件是undifine
	
	//定义一个参数对象  不能这么做 理由 File 是一个对象 无法通过ajax 请求 formData 对象用于html5 不能兼容多浏览器
	var  params ={
			"titleValue":titleValue,
			"radioValue":radioValue,
			"UEContent":UEContent,
			"file":file
	}
	
	//发送AJAx请求
	$.ajax({
	      type: "POST",
	      url: "authSendMessage/sendMessage.action",
	      data: params,
	      dataType : "json",
	      success: function(respMsg){
	      }
	   });
	
	
}*/

//表单提交前 给表单域中赋值
function getValue(){
	
	var UEContent = UE.getEditor('mText').getContent();
	 var radioval=$('input:radio[name="sendObject"]:checked').val();
	 if(radioval=='0'){
		 $("#radioValue").val(radioval);
	 }
	if($("#radioValue").val().length==0){
		window.message.error("请选择教材报名者");
		return false;
	}
	$("#UEContent").val(UEContent);
	
	if($("#TitleValue").val().length==0){
		alert("请输入标题");
		//$('input[type="submit"]').prop('disabled', true);
		return false;
	}
	if(UEContent.length==0){
		alert("请输入内容");
		//$('input[type="submit"]').prop('disabled', true);
		return false;
	}
	
	
}