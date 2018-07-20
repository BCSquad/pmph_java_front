function submit1(){
	var params = $('#contentForm').serializeArray();
	   validate();
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
}

function toList(){
	window.location.href=contextpath+"survey/surveyList.action";
}

function validate(){
	var flag ;

    $(".oneQuestion.q1").each(function () {
       var val= $(this).find('input:radio:checked');
       if(val.length<=0){
           flag = '请选择完单选题';
           window.message.info(flag);
           return flag;
       }
    });
    $(".oneQuestion.q2").each(function () {
        var val=$(this).find("input:checkbox:checked")
        if(val.length<=0){
            flag = '请选择完多选题';
            return flag;
        }
    })

       $(".inputStyle").each(function(i) {
			var val= $(this).val();
			val = $.trim(val);
			if(val.length<=0){
				flag = '请填写完单行文本题';
                return flag;
			}
	   })
       $(".textAreaStyle").each(function(i) {
           var val= $(this).val();
           val = $.trim(val);
           if(val.length<=0){
               flag = '请填写完多行文本题';
               return flag;
           }
       })

   return flag;

}

