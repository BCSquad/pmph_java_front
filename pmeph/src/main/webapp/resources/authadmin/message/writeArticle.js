/*window.onload = function(){
	if($("#UEContent").val()!=null&&$("#UEContent").val()!='undefined'&&$("#UEContent").val()!=undefined&&$("#UEContent").val().length!=0){
		var ue = UE.getEditor('mText');
		ue.setContent($("#UEContent").val());
	}
}
	*/
	


//写文章表单验证
function writeArticleValidate(){
//	debugger;
	var UEContent = UE.getEditor('mText').getContent();
	$("#UEContent").val(UEContent);
	
	if($("#TitleValue").val().length==0){
		window.message.warning("请输入标题");
		//$('input[type="submit"]').prop('disabled', true);
		return false;
	}
	if(UEContent.length==0){
		window.message.warning("请输入内容");
		//$('input[type="submit"]').prop('disabled', true);
		return false;
	}
	
	return true;
}


/*var options = {   
        type: 'POST',  
        url: '提交路径',  
        success:function(data){
        	
        },    
        dataType: 'json',  
        error : function(xhr, status, err) {              
            alert("操作失败");  
        }  
    };  */
//确定 点击的按钮类型
function btntype(btn_this){
//	debugger;
//	contxtpath
	$("#btn_type").val(btn_this);
	if($("#submitTypeCode").val()=='0'){ //submitTypeCode 状态码为0表示未提交 未保存 为1 表示已保存 为
		if(writeArticleValidate()){
			
			$.ajax({
				url:contextpath+"/authSendMessage/writeArticle.action",
				type:"post",
		        data:$("#form1").serialize(),
		        success:function(data){
		        	if(data != '1'){
		        		if(btn_this== '0'){
			        		$("#submitTypeCode").val("1");
			        		$("#msg_id").val(data);
			        	}else if(btn_this== '1') {
			        		document.getElementById("form1").reset(); 
			        		$("#TitleValue").val("");
			        		UE.getEditor('mText').setContent("");
			        		$("#submitTypeCode").val("0");
			        	}
		        		window.message.success("成功");
		        	}else{
		        		$("#submitTypeCode").val("0");
		        		window.message.error("失败");
		        	}
		        
		        }
			});
		}
	}else if($("#submitTypeCode").val()=='1'){
		/*if(btn_this=='0'){
			//window.message.info("您已保存过了，请不要重复保存");
			return false;
		}else if(btn_this=='1'){*/
			if(writeArticleValidate()){
				$.ajax({
					url:contextpath+"/authSendMessage/updateIsStaging.action",
					type:"post",
			        data:$("#form1").serialize(),
			        success:function(data){
			        	if(data != '1'){
			        		if(btn_this== '0'){
				        		$("#submitTypeCode").val("1");
				        		$("#msg_id").val(data);
				        	}else if(btn_this== '1') {
				        		document.getElementById("form1").reset(); 
				        		$("#TitleValue").val("");
				        		UE.getEditor('mText').setContent("");
				        		$("#msg_id").val("");
				        		$("#submitTypeCode").val("0");
				        	}
			        	/*	document.getElementById("form1").reset(); 
			        		UE.getEditor('mText').setContent("");
			        		$("#submitTypeCode").val("0");*/
			        		window.message.success("成功");
			        	} else {
			        		$("#submitTypeCode").val("1");
			        		window.message.error("失败");
			        	}
			        }
				});
			}
//		}
	}
	
	
	
	//$("#form1").submit();
}