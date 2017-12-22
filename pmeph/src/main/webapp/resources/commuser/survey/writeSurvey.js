function submit(){
	$.ajax({
		type: "POST",
		url:contextpath+'survey/addSurveyAnswers.action?',
		data:$('#contentForm').serialize(),
		async: false,
		dataType:"json",
	    success: function(code) {
		    if(code=='OK'){
		    	window.location.href=contextpath+"survey/surveyList.action";
		    }
	    }
	});
}



