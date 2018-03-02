$(function () {
	var id = $("#material_id").val();
	queryMaterialMap(id);  //执行查询方法
	if ($("#return_cause_hidden").val().length>0) {
		
			$("#return_cause_div").fadeIn(800);
		
	}
});

//页面组合方法
function queryMaterialMap(id){
	$.ajax({
		type: "POST",
		url:contextpath+'dataaudit/queryMaterialMap.action',
		data:{material_id:id},// 你的formid
		dataType:"json",
	    success: function(json) {
	    	chooseModel(json);
	    }
	});
}

//模块显示与隐藏判断
function chooseModel(data){
	//学习经历
	if(data.is_edu_exp_used == "1"){
		$("#zyxxjl").css("display","block");
	}
	//工作经历
	if(data.is_work_exp_used == "1"){
		$("#gzjl").css("display","block");
	}
	//教学经历
	if(data.is_teach_exp_used == "1"){
		$("#jxjl").css("display","block");
	}
	//主要学术兼职
	if(data.is_acade_used == "1"){
		$("#xsjz").css("display","block");
	}
	//上版教材参编情况
	if(data.is_last_position_used == "1"){
		$("#sbjccb").css("display","block");
	}
	//国家级课程建设情况
	if(data.is_national_course_used == "1"){
		$("#gjjpkcjs").css("display","block");
	}
	//省部级课程建设情况
	if(data.is_provincial_course_used == "1"){
		$("#sbkcjs").css("display","block");
	}
	//学校课程建设情况
	if(data.is_school_course_used == "1"){
		$("#xxkcjs").css("display","block");
	}
	//主编国家规划教材情况 
	if(data.is_national_plan_used == "1"){
		$("#zbgjjgh").css("display","block");
	}
	//教材编写情况
	if(data.is_textbook_used == "1"){
		$("#jcbxqk").css("display","block");
	}
	//其他教材编写情况
	if(data.is_pmph_textbook_used == "1"){
		$("#qtjcbx").css("display","block");
	}
	//科研情况
	if(data.is_research_used == "1"){
		$("#zjkyqk").css("display","block");
	}
	//个人成就情况
	if(data.is_achievement_used == "1"){
		$("#grcjqk").css("display","block");
	}
	//主编学术专著情况
	if(data.is_monograph_used == "1"){
		$("#zbxszz").css("display","block");
	}
	//出版行业获奖情况
	if(data.is_publish_reward_used == "1"){
		$("#publish").css("display","block");
	}
	//SCI论文投稿及影响因子情况
	if(data.is_sci_used == "1"){
		$("#sci").css("display","block");
	}
	//临床医学获奖情况
	if(data.is_clinical_reward_used == "1"){
		$("#clinical").css("display","block");
	}
	//学术荣誉授予情况
	if(data.is_acade_reward_used == "1"){
		$("#acade").css("display","block");
	}
}

//提交   通过3 
function toAudit(id,type){
		$.ajax({
			type: "POST",
			url:contextpath+'dataaudit/doMaterialAuditPass.action',
			data:{declaration_id:id,online_progress:type},// 你的formid
			async: false,
			dataType:"json",
		    success: function(msg) {
			    if(msg.msg=='OK'){
			    	 message.success("成功！");
			    	 toMain();
			    }else{
			    	message.success("失败了！");
			    }
		    }
		});
}


//点击显示纠错弹窗
function showup(id,type) {
	$("#return_id").val(id);
	$("#return_type").val(type);
    $.ajax({
        type: 'post',
        url: contextpath + 'dataaudit/tologin.action',
        async: false,
        dataType: 'json',
        success: function (json) {
            if (json == "OK") {
                $("#bookmistake").show();
            }
        }
    });
}

//点击弹窗隐藏
function hideup() {
    $("#bookmistake").hide();
}

//退回原因确认 
function correction() {
	declaration_id = $("#return_id").val();
	online_progress = $("#return_type").val();//表示驳回  2 
	return_cause = $("#return_cause").val();
            if (!Empty(return_cause)) {//非空判断
                var json = {
                		declaration_id: declaration_id,
                		online_progress: online_progress,
                		return_cause: return_cause
                };
                $.ajax({
                    type: 'post',
                    url: contextpath + 'dataaudit/doMaterialAudit.action',
                    data: json,
                    async: false,
                    dataType: 'json',
	                success: function(msg) {
	    			    if(msg.msg=='OK'){
	    			    	 message.success("成功！");
	    			    	 $("#return_cause").val(null);
	    			    	 $("#bookmistake").hide();
	    			    	 toMain();
	    			    }else{
	    			    	message.success("失败！");
	    			    }
	    		    }
                });
            } else {
                window.message.info("错误，请填写完所有内容！");
            }
        
   

}


function toMain(){
	var material_id=$("#material_id").val();
	var view_audit=$("#view_audit").val();
	window.location.href=contextpath+"dataaudit/toPage.action?material_id="+material_id+"&view_audit="+view_audit;
}

//文件下载
function downLoadProxy(fileId){
	window.location.href=contextpath+'file/download/'+fileId+'.action';
}


