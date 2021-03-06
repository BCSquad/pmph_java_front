$(function () {
	var id = $("#material_id").val();
	queryMaterialMap(id);  //执行查询方法
	if ($("#return_cause_hidden").val().length>0) {
			$("#return_cause_div").fadeIn(800);
	}
    querySurvey();
});

function querySurvey() {
    var id = $("#material_id").val();
    var user_id=$("#user_id").val();
    $.ajax({
        type: "POST",
        url:contextpath+'orgSurvey/queryAnswer.action',
        data:{material_id:id,user_id:user_id},
        async : false,
        success: function(json) {
            var str='';
            $.each(json,function (i,n) {
                var c=i+1;
                str+='<div style="margin-top: 5px">\n' +
                    '<div style="float: left;">'+c+').'+n.title+'</div>\n' +
                    '<div class="wrt">' +
                    '<img src="'+contextpath+'statics/image/tobb.png" style="background-size: 20px;width: 20px" onclick="tolook('+n.id+')">' +
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
    var user_id=$("#user_id").val();
    window.location.href =contextpath+"orgSurvey/surveyDetailsById.action?surveyId=" + id+"&user_id="+user_id+"&user_type="+1;
}
//页面组合方法
function queryMaterialMap(id){
	$.ajax({
		type: "POST",
		url:contextpath+'dataaudit/queryMaterialMap.action',
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


//提交   通过3 
function toAudit(id,type){
	var user_id=$("#user_id").val();
		$.ajax({
			type: "POST",
			url:contextpath+'dataaudit/doMaterialAuditPass.action',
			data:{declaration_id:id,online_progress:type,user_id:user_id},// 您的formid
			async: false,
			dataType:"json",
		    success: function(msg) {
			    if(msg.msg=='OK'){
			    	 message.success("成功！");
			    	 if(type=='3'){
                    	var exportWordBaseUrl = "http://"+remoteUrl+"/pmpheep";
                    	$.ajax({
                            type: 'get',
                            url: exportWordBaseUrl + '/frontWxMsg/projectEditorPleaseAdit/'+id,
                            dataType: 'jsonp',
                            jsonp:"callback", //这里定义了callback在后台controller的的参数名
                			jsonpCallback:"getMessage", //这里定义了jsonp的回调函数名。 那么在后台controller的相应方法其参数“callback”的值就是getMessage
                            success:function(wxResult){
                            	if(wxResult=="1"){
                            		//window.message.success("微信消息发送成功");
                            		setTimeout(function(){
                            			toMain();
                            		},800);
                            	}
                            },
                            error:function(XMLHttpRequest, textStatus){
                            	toMain();
                            }
                            });
                         //toMain();
			    	 }
			    	 
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
	var user_id=$("#user_id").val();
	var declaration_id = $("#return_id").val();
	var online_progress = $("#return_type").val();//表示驳回  2 
	var return_cause = $("#return_cause").val();
            if (!Empty(return_cause)) {//非空判断
                var json = {
                		declaration_id: declaration_id,
                		online_progress: online_progress,
                		return_cause: return_cause,
                		user_id:user_id
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
                window.message.info("请输入退回原因！");
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

//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){

	var va = obj.value;
	var vat = "";
	for ( var i = 1; i <= va.length; i++) {
		vat = va.substring(0,i);
		//把双字节的替换成两个单字节的然后再获得长度，与限制比较
		if (vat.replace(/[^\x00-\xff]/g,"aaa").length <= ml) {
			maxStrlength=i;
		}else{

			break;
		}
	}
	obj.maxlength=maxStrlength;
	//把双字节的替换成两个单字节的然后再获得长度，与限制比较
	if (va.replace(/[^\x00-\xff]/g,"aaa").length > ml) {
		obj.value=va.substring(0,maxStrlength);
		//window.message.warning("不可超过输入最大长度"+ml+"字节！");
	}
}

//打印按钮
function toprint(did) {
    // window.print();
     $(".yijian").css("display","block");
    $("#ddd").jqprint();
    $(".yijian").css("display","none");

    //打印状态
    $.ajax({
        type: 'post',
        url: contextpath + 'dataaudit/updPrintStatus.action?t=' + new Date().getTime()+'&did=' + did ,
        async: false,
        dataType: 'json',
        success: function (json) {

        }
    });
}


