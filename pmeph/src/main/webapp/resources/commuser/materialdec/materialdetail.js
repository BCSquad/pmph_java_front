$(function () {
	var id = $("#material_id").val();
	queryMaterialMap(id);  //执行查询方法
	
	//查询登录用户已经填写过的调研表
	querySurvey();
});

function querySurvey() {
    var id = $("#material_id").val();
    $.ajax({
        type: "POST",
        url:contextpath+'research/queryAnswer.action',
        data:{material_id:id},
        async : false,
        success: function(json) {
            var str='';
            $.each(json,function (i,n) {
                var c=i+1;
                str+='<div style="margin-top: 5px">\n' +
                    '<div style="float: left;">'+c+').'+n.title+'</div>\n' +
					'<div class="wrt">' +
					'<img src="'+contextpath+'statics/image/tobb.png" style="background-size: 100%;width: 100%" onclick="tolook('+n.id+')">' +
					'</div>\n' +
					'</div>';
                str+='<div style="clear: both"></div>';
            });
            $("#dyb").append(str);
        }
    });
}

//跳转到调研表查看页面
function tolook(id) {
    window.location.href = contextpath+"/orgSurvey/surveyDetailsById.action?surveyId=" + id+"&state="+'fromwrtlist';
}

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
	//精品课程建设情况
	if(data.is_course_used == "1"){
		$("#gjjpkcjs").css("display","block");
	}
	//主编国家规划教材情况
	if(data.is_national_plan_used == "1"){
		$("#zbgjjgh").css("display","block");
	}
	//其他教材编写情况
	if(data.is_textbook_used == "1"){
        $("#qtjcbxqk").css("display","block");
	}
    //人卫教材编写情况
    if(data.is_pmph_textbook_used == "1"){
        $("#rwsjcbx").css("display","block");
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
    //参加人卫慕课、数字教材编写情况
    if(data.is_mooc_digital_used == "1"){
        $("#digital").css("display","block");
    }
    //编写内容意向表
    if(data.is_intention_used == "1"){
        $("#intention").css("display","block");
    }
}

//文件下载
function downLoadProxy(fileId){
	window.location.href=contextpath+'file/download/'+fileId+'.action';
}

//放弃
function buttGive(){
	window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=jcsb";
}

//打印按钮
function toprint() {
    $(".yijian").css("display","block");
    $("#tnone").css("display","block");
    $("#ddd").jqprint();
    $(".yijian").css("display","none");
    $("#tnone").css("display","none");
}