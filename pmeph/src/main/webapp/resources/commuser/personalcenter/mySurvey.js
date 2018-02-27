function submit1(){
	var params = $('#contentForm').serializeArray();  
	$.ajax({
		type: "POST",
		url:contextpath+'survey/addSurveyAnswers.action',
		data:params,
		async: false,
		dataType:"json",
	    success: function(code) {
		    if(code=='OK'){
		      
		    	message.success("提交成功");
		    	setTimeout("toList()",1000);
		    }else{
		    	
            	message.error("提交失败");
            	setTimeout("toList()",1000);
            }
	    }
	});
}

function toList(){
	window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wdwj";
}