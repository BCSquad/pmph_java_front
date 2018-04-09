$(function () {
	var id = $("#material_id").val();
	queryMaterialMap(id);  //执行查询方法
});

//页面组合方法
function queryMaterialMap(id){
	$.ajax({
		type: "POST",
		url:contextpath+'material/queryMaterialMap.action',
		data:{material_id:id},// 您的formid
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
}

//提交   通过3 表示驳回  2 
function toAudit(id,type){
		$.ajax({
			type: "POST",
			url:contextpath+'material/doMaterialAudit.action',
			data:{declaration_id:id,type:type},// 您的formid
			async: false,
			dataType:"json",
		    success: function(msg) {
			    if(msg=='OK'){
			    	window.location.href=contextpath+"applyDocAudit/toPage.action";
			    }
		    }
		});
}

