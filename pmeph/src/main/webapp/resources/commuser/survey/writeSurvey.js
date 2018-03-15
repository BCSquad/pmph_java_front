function submit1(){
	var params = $('#contentForm').serializeArray();
	if(validate()){
		$(".buttonDiv").attr("onclick","");
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
                    $(".buttonDiv").attr("onclick","submit1()");
                    message.error("提交失败");
                    setTimeout("toList()",1000);
                }
            },
			error:function(){
                $(".buttonDiv").attr("onclick","submit1()");
			}
        });
        }else{
        message.warning("您有问题没有填!");
	}

}

function toList(){
	window.location.href=contextpath+"survey/surveyList.action";
}

function validate(){
	var flag = false;
    var radioval=$('input:radio:checked').val();
    var str =[];
    $("input:checkbox[name^='checkbox_']").each(function(i) {

        var val = $(this).val();
        str.push(val);
    });
   if(radioval&&str.length>0){
       flag = true;
       $(".inputStyle").each(function(i) {
			var val= $(this).val();
			if(val.length<=0){
				flag = false;
				//alert(flag);
			}
	   })
       $(".textAreaStyle").each(function(i) {
           var val= $(this).val();
           if(val.length<=0){
               flag = false;
               //alert(flag);
           }
       })
   }
   return flag;

}

